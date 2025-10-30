package com.orm.ORMapper.exceptions;

public class InvalidSqlTypeException extends RuntimeException{
    public InvalidSqlTypeException(String message){
        super(message);
    }
}
