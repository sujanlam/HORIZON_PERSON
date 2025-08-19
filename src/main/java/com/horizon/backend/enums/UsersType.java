package com.horizon.backend.enums;

import lombok.Getter;

@Getter
public enum UsersType {
    ADMIN("Administrator", 1),
    EMPLOYEE("Employee", 2),
    OTHER("Other User", 3);

    private final String description;
    private final Integer code;

    UsersType(String description, Integer code) {
        this.description = description;
        this.code = code;
    }

}
