package com.example.myproject.entity;

public enum ClientStatus {

    ACTIVE("active"),
    NOT_ACTIVE("not active"),
    CLOSED("closed"),
    NOT_REGISTERED("not registered"),
    NOT_CLIENT("not client"),
    DELETED("deleted");

    private String value;

    ClientStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}


