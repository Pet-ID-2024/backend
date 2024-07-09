package com.petid.domain.exception;

public class LocationNotFoundException extends LocationDataNotFoundException {

    public LocationNotFoundException(long id) {
        super("ID : " + id + " not found");
    }
}
