package com.orm.ORMapper.repo;

import com.orm.ORMapper.DbQueries.Query;
import com.orm.ORMapper.DbQueries.QueryManager;
import com.orm.ORMapper.DbQueries.enums.QueryType;
import com.orm.ORMapper.dbexecutions.QueryExecutionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DbRepository {
    @Autowired
    private QueryManager queryManager;
    @Value("${db.queries.print:false}")
    private boolean toPrint;

    private Logger logger= LoggerFactory.getLogger(DbRepository.class);
    @Autowired
    private QueryExecutionManager queryExecutionManager;
    public void save(Object o){
        queryManager.setInstance(o);
        Query query = queryManager.generateQuery(QueryType.INSERT, o.getClass());
        queryExecutionManager.executeQuery(List.of(query),QueryType.INSERT);
        if(toPrint){
            logger.info(query.getQuery());
        }
    }
}
