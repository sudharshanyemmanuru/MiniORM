package com.orm.ORMapper.exceptions;

public class MultiplePrimaryKeyDeclaredException extends RuntimeException{
    public MultiplePrimaryKeyDeclaredException(String message){
        super(message);
    }
}
