package com.orm.ORMapper.DbQueries.queryHelpers;

import com.orm.ORMapper.annotations.Column;
import com.orm.ORMapper.annotations.Id;
import com.orm.ORMapper.exceptions.InvalidSqlTypeException;
import com.orm.ORMapper.exceptions.MultiplePrimaryKeyDeclaredException;
import com.orm.ORMapper.exceptions.PrimaryKeyNotFoundException;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.time.LocalDate;

@Component
public class DBQueryHelper {
    public String sqlType(Field field){
        Class<?> fieldType=field.getType();
        if(fieldType== int.class||fieldType==Integer.class){
            return "INT";
        }else if(fieldType==double.class||fieldType==Double.class){
            return "DOUBLE";
        }else if(fieldType==long.class||fieldType==Long.class){
            return "LONG";
        }else if(fieldType==boolean.class|| fieldType==Boolean.class){
            return "BOOLEAN";
        }else if(fieldType== LocalDate.class){
            return "DATE";
        }else if(fieldType==String.class){
            return handleStringFields(field);
        }else{
            throw new InvalidSqlTypeException("SQL Data Type is Invalid.");
        }
    }
    private String handleStringFields(Field field){
        StringBuilder stringDatatype=new StringBuilder("VARCHAR(");
        if(field.isAnnotationPresent(Column.class)){
            Column column=field.getAnnotation(Column.class);
            int stringSize=column.size();
            if(stringSize==0){
                return stringDatatype.append("30)").toString();
            }
            return stringDatatype.append(stringSize+")").toString();
        }
        return "VARCHAR(30)";
    }
    public Field checkForPrimaryKey(Class<?> cls){
        Field[] declaredFields=cls.getDeclaredFields();
        int declaredPrimaryKeyColumnCount=0;
        Field primaryKeyField=null;
        for(Field field:declaredFields){
            if(field.isAnnotationPresent(Id.class)){
                primaryKeyField=field;
                declaredPrimaryKeyColumnCount++;
                if(declaredPrimaryKeyColumnCount>1){
                    throw new MultiplePrimaryKeyDeclaredException("Please Declare Only One Primary Key Column For Class : "+cls.getTypeName()+". Found Multiple @Id Annotated Fields For this Entity.");
                }
            }
        }
        if(primaryKeyField!=null){
            return  primaryKeyField;
        }
        throw new PrimaryKeyNotFoundException("Primary Key Not Found. @Id Annotated Field Not Found For Class: "+cls.getTypeName());
    }
    public String fieldName(Field field){
        if(field.isAnnotationPresent(Column.class)){
            Column column=field.getAnnotation(Column.class);
            if(!column.columnName().isEmpty()){
               return column.columnName();
            }
        }
        return field.getName();
    }
}
