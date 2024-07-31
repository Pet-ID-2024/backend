package com.petid.domain.exception;

public class HospitalNotFoundException extends HospitalDataNotFoundException {

    public HospitalNotFoundException(Long id) {
        super("Hospital ID : " + id + " not found");
    }
}
