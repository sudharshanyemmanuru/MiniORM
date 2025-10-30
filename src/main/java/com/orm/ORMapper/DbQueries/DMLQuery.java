package com.orm.ORMapper.DbQueries;

import com.orm.ORMapper.DbQueries.enums.QueryType;
import com.orm.ORMapper.DbQueries.generators.DMLQueryGenerator;
import org.springframework.stereotype.Component;

@Component
public class DMLQuery{
    //INSERT, UPDATE, DELETE
    private final DMLQueryGenerator dmlQueryGenerator;
    private Object instance;
    public DMLQuery(DMLQueryGenerator dmlQueryGenerator){
        this.dmlQueryGenerator=dmlQueryGenerator;
    }
    public Query getQuery(QueryType queryType, Class<?> cls){
        if(queryType==QueryType.INSERT){
            if(instance!=null)//null check
                dmlQueryGenerator.setInstance(instance);//parameter drilling....
            return dmlQueryGenerator.generateInsertQuery(cls);
        }else if(queryType==QueryType.UPDATE){
            dmlQueryGenerator.setInstance(instance);
            return dmlQueryGenerator.generateUpdateQuery(cls,"");
        }
        return null;
    }
    public void setInstance(Object instance){
        this.instance=instance;
    }
}
