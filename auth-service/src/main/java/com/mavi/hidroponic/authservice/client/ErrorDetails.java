package com.mavi.hidroponic.authservice.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ErrorDetails {
    private String code;
    private String message;
    private LocalDateTime timestamp;

    public ErrorDetails(String code, String message) {
        this.code = code;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
