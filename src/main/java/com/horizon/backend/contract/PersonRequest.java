package com.horizon.backend.contract;

import com.horizon.backend.enums.UsersType;

public record PersonRequest (
        String createdBy,
        UsersType userType
){
}
