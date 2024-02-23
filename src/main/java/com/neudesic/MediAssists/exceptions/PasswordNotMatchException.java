package com.neudesic.MediAssists.exceptions;

public class PasswordNotMatchException extends RuntimeException{
    public PasswordNotMatchException(String msg) {
        super(msg);
    }
}
