package com.mavi.hidroponic.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    String token;
    String message;
}
