package com.rudykart.karyawan.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rudykart.karyawan.dto.AuthenticationResponse;
import com.rudykart.karyawan.dto.LoginDto;
import com.rudykart.karyawan.dto.RegisterDto;
import com.rudykart.karyawan.dto.TokenDto;
import com.rudykart.karyawan.services.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint untuk Register
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        return userService.register(registerDto);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginDto loginDto) {
        AuthenticationResponse response = userService.login(loginDto);
        return ResponseEntity.ok(response);
    }

    // Endpoint untuk Refresh Token
    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshToken(@RequestBody TokenDto tokenDto) {
        AuthenticationResponse response = userService.refreshToken(tokenDto);
        return ResponseEntity.ok(response);
    }
}
