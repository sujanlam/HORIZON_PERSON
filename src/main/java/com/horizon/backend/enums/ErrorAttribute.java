package com.horizon.backend.enums;

import lombok.Getter;

@Getter
public enum ErrorAttribute {
    TIMESTAMP("timestamp"),
    STATUS("status"),
    ERROR("error"),
    MESSAGE("message");

    private final String key;

    ErrorAttribute(String key){
        this.key = key;
    }
}
