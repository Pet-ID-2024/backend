package com.petid.domain.exception;

public class MemberAuthNotFoundException extends MemberDataNotFoundException {

    public MemberAuthNotFoundException(long id) {
        super("Member ID : " + id + " Auth Info not found");
    }
}
