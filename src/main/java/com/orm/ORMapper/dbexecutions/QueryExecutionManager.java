package com.orm.ORMapper.dbexecutions;

import com.orm.ORMapper.DbQueries.Query;
import com.orm.ORMapper.DbQueries.enums.QueryType;
import com.orm.ORMapper.exceptions.NoExecutorFound;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

@Component
public class QueryExecutionManager {
    private JdbcTemplate jdbcTemplate;
    private Logger logger= LoggerFactory.getLogger(QueryExecutionManager.class);
    @Value("${db.queries.print:false}")
    private boolean toPrint;
    public QueryExecutionManager(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    public void executeQuery(List<Query> queries, QueryType queryType){
        // Before Executing Queries It needs to Identify The Correct Supported QueryExecutor based QueryType
        QueryExecutor desiredExecutor;
        try{
            desiredExecutor=findExecutor(queryType);
            executeQueries(queries,desiredExecutor);
            printQueries(queries);
        }catch(Exception ex){
            logger.error("Error Occurred "+ex.getMessage());
        }
    }
    private QueryExecutor findExecutor(QueryType supportingQueryType) throws Exception {
        Set<Class<? extends QueryExecutor>> queryExecutors=getExecutors();
        for(Class<? extends QueryExecutor> queryExecutor:queryExecutors){
            QueryExecutor executor=queryExecutor.getDeclaredConstructor(JdbcTemplate.class).newInstance(this.jdbcTemplate);
            if(executor.querySupportForExecution(supportingQueryType))
                return executor;
        }
        throw new NoExecutorFound("No Supported QueryExecutor Found");
    }
    private Set<Class<? extends QueryExecutor>> getExecutors(){
        Reflections reflections=new Reflections("com.orm.ORMapper.dbexecutions");
        return reflections.getSubTypesOf(QueryExecutor.class);
    }
    private void executeQueries(List<Query> queries,QueryExecutor queryExecutor){
        queries.forEach(queryExecutor::executeQuery);
    }
    private void printQueries(List<Query> queries){
        if(toPrint){
            queries.forEach(System.out::println);
        }
    }
}
