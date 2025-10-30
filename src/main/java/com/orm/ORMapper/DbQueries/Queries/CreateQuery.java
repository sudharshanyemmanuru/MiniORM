package com.orm.ORMapper.DbQueries.Queries;

import com.orm.ORMapper.DbQueries.Query;

public class CreateQuery implements Query {
    private final String createQuery;

    public CreateQuery(String createQuery){
        this.createQuery=createQuery;
    }
    @Override
    public String getQuery() {
        return this.createQuery;
    }
    @Override
    public String toString(){
        return this.createQuery;
    }

}
