package com.codewithproject.springsecurity.enums;

public enum StatusPayment {

    NEW(1),
    PROCESSING(2),
    CANCELLED(3),
    SUCCESS(4);

    private final int status;

    private StatusPayment(int status){
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }
}
