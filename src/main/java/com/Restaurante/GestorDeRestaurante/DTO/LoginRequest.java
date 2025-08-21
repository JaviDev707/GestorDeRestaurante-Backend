package com.Restaurante.GestorDeRestaurante.DTO;
/**
 * 	Recibe los datos del usuario al hacer login
 */
public record LoginRequest(

    String email,
    String password

) {}
