package com.rudykart.karyawan.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rudykart.karyawan.dto.AuthenticationResponse;
import com.rudykart.karyawan.dto.LoginDto;
import com.rudykart.karyawan.dto.RegisterDto;
import com.rudykart.karyawan.dto.TokenDto;
import com.rudykart.karyawan.entities.Token;
import com.rudykart.karyawan.entities.User;
import com.rudykart.karyawan.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService,
            JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public ResponseEntity<?> register(RegisterDto registerDto) {
        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        return ResponseEntity.ok(userRepository.save(user));
    }

    public AuthenticationResponse login(LoginDto loginDto) {

        System.out.println("Mulai");
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(),
                            loginDto.getPassword()));
        } catch (AuthenticationException e) {
            System.out.println("Login gagal");
        }
        System.out.println("Selesai");
        return new AuthenticationResponse(jwtService.generateToken(loginDto.getEmail()),
                tokenService.createToken(loginDto.getEmail()).getToken());
    }

    public AuthenticationResponse refreshToken(TokenDto tokenDto) {
        return tokenService.findByToken(tokenDto.getToken())
                .map(tokenService::verifyExpiration)
                .map(Token::getUser)
                .map(user -> {
                    String accessToken = jwtService.generateToken(user.getEmail());
                    return new AuthenticationResponse(accessToken,
                            tokenService.createToken(user.getEmail()).getToken());
                }).orElseThrow(() -> new RuntimeException(
                        "Refresh token is not in database!"));

    }
}
