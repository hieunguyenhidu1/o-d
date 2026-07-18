package com.example.demo.controller;

import com.example.demo.config.JwtUtils;
import com.example.demo.dto.*;
import com.example.demo.service.UserDetailsImpl;
import com.example.demo.service.UserDetailsServiceImpl;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final UserDetailsServiceImpl userDetailsService;

    public AuthController(AuthenticationManager authManager, JwtUtils jwtUtils, UserService userService, UserDetailsServiceImpl userDetailsService) {
        this.authManager = authManager;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            userService.register(request);
            return ResponseEntity.ok("✅ Đăng ký thành công!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("❌ " + e.getMessage());
        }
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // gui thong tin cho authenticationManager de ktra thong tin nguoi dung
        // Mac dinh gọi đến UserDetailsService.loadUserByUsername(...)
        // Lay password tu UserDetailsImpl
        // so sanh mk passwordEncoder.matches
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        String token = jwtUtils.generateToken((UserDetailsImpl) auth.getPrincipal());
        String refreshToken = jwtUtils.generateRefreshToken((UserDetailsImpl) auth.getPrincipal());

        return ResponseEntity.ok(new TokenResponse(token, refreshToken));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();

        if (!jwtUtils.validateToken(refreshToken)) {
            return ResponseEntity.badRequest().body("Invalid or expired refresh token");
        }

        String username = jwtUtils.getUsernameFromToken(refreshToken);
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);

        String newAccessToken = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new TokenResponse(newAccessToken, refreshToken));
    }
}
