package com.Restaurante.GestorDeRestaurante.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Mesa {
    
    @Id
    private Long id;
    
    private int capacidad;
    
    @Enumerated(EnumType.STRING)
    private EstadoMesa estadoMesa;


    public enum EstadoMesa{
        LIBRE,
        OCUPADA,
        RESERVADA
    }
}
