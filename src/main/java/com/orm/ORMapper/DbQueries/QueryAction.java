package com.orm.ORMapper.DbQueries;

import com.orm.ORMapper.DbQueries.enums.QueryType;

public interface QueryAction {
    Query getQuery(QueryType queryType, Class<?> cls);
}
