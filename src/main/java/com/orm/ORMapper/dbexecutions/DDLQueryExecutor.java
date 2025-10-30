package com.orm.ORMapper.dbexecutions;

import com.orm.ORMapper.DbQueries.Query;
import com.orm.ORMapper.DbQueries.enums.QueryType;
import org.springframework.jdbc.core.JdbcTemplate;


public class DDLQueryExecutor implements QueryExecutor{
    private JdbcTemplate jdbcTemplate;
    public DDLQueryExecutor(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    public DDLQueryExecutor(){};
    @Override
    public void executeQuery(Query query) {
        jdbcTemplate.execute(query.getQuery());
    }
    @Override
    public boolean querySupportForExecution(QueryType queryType) {
        return queryType==QueryType.CREATE || queryType==QueryType.ALTER;
    }
}
