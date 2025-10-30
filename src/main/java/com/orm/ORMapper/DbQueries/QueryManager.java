package com.orm.ORMapper.DbQueries;

import com.orm.ORMapper.DbQueries.enums.QueryType;

public interface QueryManager {
   Query generateQuery(QueryType queryType,Class<?> cls);
   default void setInstance(Object o){
      // set the Instance to Insert or Replace placeholders with actual values for DML Queries.
   }
}
