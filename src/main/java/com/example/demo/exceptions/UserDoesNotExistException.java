package com.example.demo.exceptions;

import com.example.demo.models.User;

public class UserDoesNotExistException extends Exception{

    public UserDoesNotExistException(String message){
        super(message);
    }
}
