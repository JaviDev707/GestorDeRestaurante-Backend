package com.Restaurante.GestorDeRestaurante.DTO;
/**
 * Recibe los datos del usuario al registrarse
 */
public record RegisterRequest(

    String username,
    String email,
    String password

) {}
