package com.example.demo.exceptions;

import jakarta.persistence.criteria.CriteriaBuilder;

public class InvalidPasswordException extends Exception{

    public InvalidPasswordException(String message){
        super(message);
    }
}
