package com.utilities;

public class MyException extends Exception{

    String exception;

    public MyException(String exception) {
        this.exception = exception;
    }


    public String getException() {
        return "your exception is: " + exception;
    }
}
