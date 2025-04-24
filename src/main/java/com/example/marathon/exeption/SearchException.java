package com.example.marathon.exeption;

public class SearchException extends Exception{


    public SearchException(){
        super();
    }


    public SearchException(String msg){
        super(msg);
    }
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }


}
