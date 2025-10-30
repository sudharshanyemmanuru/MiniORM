package com.orm.ORMapper.exceptions;

public class PrimaryKeyNotFoundException extends RuntimeException{
    public PrimaryKeyNotFoundException(String message){
        super(message);
    }
}
