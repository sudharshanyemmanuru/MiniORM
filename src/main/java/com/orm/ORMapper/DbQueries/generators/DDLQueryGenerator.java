package com.orm.ORMapper.DbQueries.generators;

import com.orm.ORMapper.DbQueries.Queries.CreateQuery;
import com.orm.ORMapper.DbQueries.Query;
import com.orm.ORMapper.DbQueries.queryHelpers.DBQueryHelper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class DDLQueryGenerator {
    private final DBQueryHelper queryHelper;
    public DDLQueryGenerator(DBQueryHelper queryHelper){
        this.queryHelper=queryHelper;
    }

    public Query generateCreateQuery(Class<?> cls){
        StringBuilder query=new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        String tableName=cls.getSimpleName();
        query.append(tableName+" ");
        query.append("(");
        Field primaryKeyField=queryHelper.checkForPrimaryKey(cls);
        Field[] fields=cls.getDeclaredFields();
        for(int i=0;i<fields.length;i++){
            String sqlType= queryHelper.sqlType(fields[i]);
            String filedName=queryHelper.fieldName(fields[i]);
            query.append(filedName).append(" ").append(sqlType);
            if (i<fields.length-1){
                query.append((","));
            }
        }
        query.append(", PRIMARY KEY( ")
                .append(primaryKeyField.getName())
                .append(")")
                .append(")");
        return new CreateQuery(query.toString());
    }
}
