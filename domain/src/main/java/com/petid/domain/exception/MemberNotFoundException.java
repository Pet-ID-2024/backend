package com.petid.domain.exception;

public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException(String uid) {
        super("Member UID : " + uid + " not found");
    }

    public MemberNotFoundException(long id) {
        super("Member ID : " + id + " not found");
    }
}
