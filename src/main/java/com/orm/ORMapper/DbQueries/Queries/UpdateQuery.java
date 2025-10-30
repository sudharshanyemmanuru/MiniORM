package com.orm.ORMapper.DbQueries.Queries;

import com.orm.ORMapper.DbQueries.Query;

public class UpdateQuery implements Query {
    private String updateQuery;
    public UpdateQuery(String updateQuery){this.updateQuery=updateQuery;}
    @Override
    public String getQuery() {
        return this.updateQuery;
    }
}
