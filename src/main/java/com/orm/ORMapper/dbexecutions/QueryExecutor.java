package com.orm.ORMapper.dbexecutions;

import com.orm.ORMapper.DbQueries.Query;
import com.orm.ORMapper.DbQueries.enums.QueryType;

public interface QueryExecutor {
    void executeQuery(Query query);
    boolean  querySupportForExecution(QueryType queryType);

}
