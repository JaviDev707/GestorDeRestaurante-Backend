package com.Restaurante.GestorDeRestaurante.AuthService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Restaurante.GestorDeRestaurante.DTO.AuthResponse;
import com.Restaurante.GestorDeRestaurante.DTO.LoginRequest;
import com.Restaurante.GestorDeRestaurante.DTO.RegisterRequest;
import com.Restaurante.GestorDeRestaurante.Entity.Role;
import com.Restaurante.GestorDeRestaurante.Entity.Usuario;
import com.Restaurante.GestorDeRestaurante.Repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        // Valido si el usuario y el email ya existen
        if (usuarioRepository.existsByNombre(request.username())) {
            throw new RuntimeException("El nombre de usuario ya esta en uso.");
        }
        if (usuarioRepository.existsByEmail(request.email())) {
            throw new RuntimeException("El email ya esta en uso.");
        }
        // Creo el usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(request.username());
        usuario.setEmail(request.email());
        usuario.setPassword(passwordEncoder.encode(request.password()));
        usuario.setRole(Role.CLIENTE);
        // Guardo el usuario en la base de datos
        usuarioRepository.save(usuario);
        // Genero un JWT
        String token = jwtUtil.generateToken(usuario);
        // Devuelvo AuthResponse
        System.out.println("Usuario registrado con éxito " + usuario);
        return new AuthResponse(token);
    }

    public AuthResponse login (LoginRequest request){

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        Usuario usuario = usuarioRepository.findByEmail(request.email());
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado.");
        }

        String token = jwtUtil.generateToken(usuario);

        System.out.println("Usuario logueado con éxito " + usuario.getUsername() + " - " + usuario.getRole());
        return new AuthResponse(token);
    }
}
