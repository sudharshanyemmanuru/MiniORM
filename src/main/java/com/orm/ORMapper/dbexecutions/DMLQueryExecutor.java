package com.orm.ORMapper.dbexecutions;

import com.orm.ORMapper.DbQueries.Query;
import com.orm.ORMapper.DbQueries.enums.QueryType;
import org.springframework.jdbc.core.JdbcTemplate;

public class DMLQueryExecutor implements QueryExecutor{
    private final JdbcTemplate jdbcTemplate;
    public DMLQueryExecutor(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    @Override
    public void executeQuery(Query query) {
        jdbcTemplate.update(query.getQuery(),query.getValues());
    }

    @Override
    public boolean querySupportForExecution(QueryType queryType) {
        return queryType==QueryType.INSERT||queryType==QueryType.UPDATE||queryType==QueryType.DELETE;
    }
}
