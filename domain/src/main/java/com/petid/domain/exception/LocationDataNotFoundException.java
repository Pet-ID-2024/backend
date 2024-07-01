package com.petid.domain.exception;

public class LocationDataNotFoundException extends RuntimeException {

    public LocationDataNotFoundException(long id) {
        super("ID : " + id + " not found");
    }
}
