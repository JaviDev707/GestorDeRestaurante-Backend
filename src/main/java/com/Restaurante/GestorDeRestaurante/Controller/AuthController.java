package com.Restaurante.GestorDeRestaurante.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Restaurante.GestorDeRestaurante.AuthService.AuthService;
import com.Restaurante.GestorDeRestaurante.AuthService.CustomUserDetailsService;
import com.Restaurante.GestorDeRestaurante.AuthService.JwtUtil;
import com.Restaurante.GestorDeRestaurante.DTO.AuthResponse;
import com.Restaurante.GestorDeRestaurante.DTO.LoginRequest;
import com.Restaurante.GestorDeRestaurante.DTO.RegisterRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    
    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    @PostMapping("/registro")
    public ResponseEntity<AuthResponse> registro(@RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/validar")
    public ResponseEntity<AuthResponse> validarToken(@RequestHeader("Authorization") String authHeader) {
        // Quito el prefijo "Bearer "
        String token = authHeader.substring(7);
        
        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String username = jwtUtil.extractUsername(token);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        // Nuevo token
        String newToken = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(newToken));
    }

}
