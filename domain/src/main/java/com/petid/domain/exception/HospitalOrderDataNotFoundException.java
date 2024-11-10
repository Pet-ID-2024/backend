package com.petid.domain.exception;

public class HospitalOrderDataNotFoundException extends HospitalDataNotFoundException {

    public HospitalOrderDataNotFoundException(long id, long memberId) {
        super("Hospital Order ID : " + id + " & member ID : " + memberId + " not found");
    }
}
