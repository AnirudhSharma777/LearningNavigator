package com.example.learningNavigator.Exceptions;

public class NotAIntegerException  extends RuntimeException{

    public NotAIntegerException(){
        super();
    }
    public NotAIntegerException(String message) {
        super(message);
    }
}
