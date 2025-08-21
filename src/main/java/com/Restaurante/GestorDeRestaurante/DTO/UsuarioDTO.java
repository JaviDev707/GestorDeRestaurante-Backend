package com.Restaurante.GestorDeRestaurante.DTO;
/**
 *  Muestra información del perfil sin exponer la entidad Usuario
 */
public record UsuarioDTO(

    String username,
    String email,
    String role
    
) {}
