package com.mavi.hidroponic.authservice.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {
    BAD_REQUEST("ERR001", "Bad request"),
    INTERNAL_SERVER_ERROR("ERR002", "Internal server error"),;


    private final String code;
    private final String message;
    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
