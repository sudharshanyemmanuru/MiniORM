package com.orm.ORMapper.DbQueries.impl;

import com.orm.ORMapper.DbQueries.DDLQuery;
import com.orm.ORMapper.DbQueries.DMLQuery;
import com.orm.ORMapper.DbQueries.Query;
import com.orm.ORMapper.DbQueries.QueryManager;
import com.orm.ORMapper.DbQueries.enums.QueryType;
import com.orm.ORMapper.exceptions.InvalidQueryTypeException;
import org.springframework.stereotype.Component;

@Component
public class QueryManagerImpl implements QueryManager {
    private final DDLQuery ddlQuery;
    private final DMLQuery dmlQuery;
    private Object instance;
    public QueryManagerImpl(DDLQuery ddlQuery,DMLQuery dmlQuery){
        this.ddlQuery=ddlQuery;
        this.dmlQuery=dmlQuery;
    }
    @Override
    public Query generateQuery(QueryType queryType, Class<?> cls) {
        if(queryType==QueryType.CREATE || queryType==QueryType.ALTER)
            return this.ddlQuery.getQuery(queryType,cls);
        else if(queryType==QueryType.INSERT||queryType==QueryType.UPDATE||queryType==QueryType.DELETE){
            if(instance!=null)
                dmlQuery.setInstance(instance);
            return this.dmlQuery.getQuery(queryType,cls);
        }
        throw new InvalidQueryTypeException("Query Type is Invalid");
    }
    @Override
    public void setInstance(Object instance) {
        this.instance = instance;
    }
}
