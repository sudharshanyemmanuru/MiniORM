package com.orm.ORMapper.DbQueries.Queries;

import com.orm.ORMapper.DbQueries.Query;

public class InsertQuery implements Query {
    private String query;
    private Object[] values;
    public InsertQuery(String query,Object[] values){
        this.query=query;
        this.values=values;
    }
    @Override
    public String getQuery() {
        return this.query;
    }
    @Override
    public Object[] getValues(){
        return values;
    }
}
