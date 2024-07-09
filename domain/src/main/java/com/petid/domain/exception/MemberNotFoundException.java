package com.petid.domain.exception;

public class MemberNotFoundException extends MemberDataNotFoundException {

    public MemberNotFoundException(String uid) {
        super("Member UID : " + uid + " not found");
    }

    public MemberNotFoundException(long id) {
        super("Member ID : " + id + " not found");
    }
}
