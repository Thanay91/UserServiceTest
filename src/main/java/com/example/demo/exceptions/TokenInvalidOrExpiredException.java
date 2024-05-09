package com.example.demo.exceptions;

public class TokenInvalidOrExpiredException extends Exception{
    public TokenInvalidOrExpiredException(String message){
        super(message);
    }
}
