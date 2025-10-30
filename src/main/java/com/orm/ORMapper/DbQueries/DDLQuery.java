package com.orm.ORMapper.DbQueries;


import com.orm.ORMapper.DbQueries.enums.QueryType;
import com.orm.ORMapper.DbQueries.generators.DDLQueryGenerator;
import org.springframework.stereotype.Component;

@Component
public class DDLQuery{
    // CREATE, ALTER, DROP
    private final DDLQueryGenerator queryGenerator;
    public DDLQuery(DDLQueryGenerator queryGenerator){
        this.queryGenerator=queryGenerator;
    }
    public Query getQuery(QueryType queryType,Class<?> cls){
        if(queryType==QueryType.CREATE){
            return queryGenerator.generateCreateQuery(cls);
        }
        return null;
    }
}
