package com.mavi.hidroponic.authservice.service;

import com.mavi.hidroponic.authservice.enums.Role;
import com.mavi.hidroponic.authservice.model.AuthResponse;
import com.mavi.hidroponic.authservice.model.LoginRequest;
import com.mavi.hidroponic.authservice.model.RegisterRequest;
import com.mavi.hidroponic.authservice.model.User;
import com.mavi.hidroponic.authservice.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        System.out.println(request);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .message("Usuario autorizado de forma correcta")
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        if (!userRepository.findByUsername(request.getUsername()).isEmpty()) {
            throw new IllegalArgumentException("El usuario ya existe");
        }
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
