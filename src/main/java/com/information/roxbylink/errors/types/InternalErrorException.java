package com.information.roxbylink.errors.types;

public class InternalErrorException extends RuntimeException {
    public InternalErrorException(String error) {
        super(error);
    }
}
