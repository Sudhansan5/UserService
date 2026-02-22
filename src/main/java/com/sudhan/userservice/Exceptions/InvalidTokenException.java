package com.sudhan.userservice.Exceptions;

public class InvalidTokenException extends Exception{
    public InvalidTokenException(String message){
        super(message);
    }
}
