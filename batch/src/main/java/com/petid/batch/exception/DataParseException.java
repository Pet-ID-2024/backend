package com.petid.batch.exception;

public class DataParseException extends RuntimeException {
    public DataParseException(Exception e) {
        super(e);
    }
}
