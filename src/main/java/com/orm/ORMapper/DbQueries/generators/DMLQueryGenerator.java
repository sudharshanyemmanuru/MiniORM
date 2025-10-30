package com.orm.ORMapper.DbQueries.generators;

import com.orm.ORMapper.DbQueries.Queries.InsertQuery;
import com.orm.ORMapper.DbQueries.Queries.UpdateQuery;
import com.orm.ORMapper.DbQueries.Query;
import com.orm.ORMapper.DbQueries.queryHelpers.DBQueryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
@Component
public class DMLQueryGenerator {
    private Object instance;
    private Logger logger= LoggerFactory.getLogger(DMLQueryGenerator.class);
    @Autowired
    private DBQueryHelper queryHelper;
    public Query generateInsertQuery(Class<?> cls)  {
        StringBuilder columns=new StringBuilder();
        Field[] fields=cls.getDeclaredFields();
        List<Object> values=new ArrayList<>();
        for(Field f:fields){
            try{
                f.setAccessible(true);
                columns.append(queryHelper.fieldName(f)).append(",");
                values.add(f.get(instance));
            }catch (Exception ex){
                logger.error("Error In DMLQueryGenerator : "+ex.getMessage());
            }
        }
        columns.setLength(columns.length()-1);//2
        StringBuilder placeholders=new StringBuilder("?,".repeat(fields.length));// ?,?,
        placeholders.setLength(placeholders.length()-1);
        String query="INSERT INTO "+cls.getSimpleName().toLowerCase()+" ("+columns+") VALUES("+placeholders+")";
        return new InsertQuery(query,values.toArray());
    }
    public Query generateUpdateQuery(Class<?> cls,String updateColumn){
        // UPDATE book SET price=200 where id=1
        UpdateQuery updateQuery=null;
        try{
            String tableName=cls.getSimpleName();
            Field fieldTobeUpdated=cls.getDeclaredField(updateColumn);
            fieldTobeUpdated.setAccessible(true);
            Field idField= cls.getDeclaredFields()[0];
            idField.setAccessible(true);
            String query="UPDATE "+tableName+" SET "+fieldTobeUpdated.getName()+"="+fieldTobeUpdated.get(instance)+" WHERE "+idField.getName()+"="+idField.get(instance);
            updateQuery=new UpdateQuery(query);
        }catch (Exception ex){
            logger.error("Error while Generating UpdateQuery : "+ex.getMessage());
        }
        return updateQuery;//prompts to Null Pointer Exception. Need Fix for this Bug
    }
    public void setInstance(Object instance){
        this.instance=instance;
    }
}
