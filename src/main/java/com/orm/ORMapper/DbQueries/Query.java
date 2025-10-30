package com.orm.ORMapper.DbQueries;

public interface Query {
    String getQuery();
    default Object[] getValues(){
        return new Object[0];
    }
}
