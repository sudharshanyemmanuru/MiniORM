package com.orm.ORMapper.appscanner;

import com.orm.ORMapper.DbQueries.Query;
import com.orm.ORMapper.DbQueries.QueryManager;
import com.orm.ORMapper.DbQueries.enums.QueryType;
import com.orm.ORMapper.annotations.Entity;
import com.orm.ORMapper.dbexecutions.QueryExecutionManager;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ApplicationScanner {
    @Autowired
    private QueryManager queryManager;
    @Autowired
    private QueryExecutionManager queryExecutionManager;
    @Value("${app.base-package}")
    private String basePackage;
    public void scan(){
        //here we will do scanning packages to filter @Entity Annotated classes
        // Then Start Generating DDL Query and Hit DB using JDBC APi This will create the table for the class on start up of application
        Set<Class<?>> entityClasses=getEntities();
        List<Query>queries=generateQueryFromClasses((entityClasses));
        queryExecutionManager.executeQuery(queries,QueryType.CREATE);
    }
    private  Set<Class<?>> getEntities(){
        // have the logic of scanning packages
        Reflections reflections=new Reflections(
                new ConfigurationBuilder().forPackages(basePackage)
                        .addScanners(Scanners.TypesAnnotated)
        );
        return reflections.getTypesAnnotatedWith(Entity.class);
    }
    private List<Query> generateQueryFromClasses(Set<Class<?>> entityClasses){
        // here we will generate the Queries and Create the table on Database
        List<Query> queries;
        queries=entityClasses.stream()
                .map(cls->queryManager.generateQuery(QueryType.CREATE,cls))
                .collect(Collectors.toList());
        return queries;
    }
}
