package com.petid.domain.exception;

public class MemberPolicyNotFoundException extends MemberDataNotFoundException {

    public MemberPolicyNotFoundException(long id) {
        super("Member ID : " + id + " not found");
    }
}
