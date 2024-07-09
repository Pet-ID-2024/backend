package com.petid.domain.exception;

public class HospitalNotFoundException extends RuntimeException {

    public HospitalNotFoundException(Long id) {
        super("Hospital ID : " + id + " not found");
    }
}
