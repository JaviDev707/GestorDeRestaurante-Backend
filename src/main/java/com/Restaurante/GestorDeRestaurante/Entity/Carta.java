package com.Restaurante.GestorDeRestaurante.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Carta {
    
    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private Long id;

    private String plato;
    private double precio;

    @Column(columnDefinition="TEXT")
    private String descripcion;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;


    public enum Categoria{
        ENTRANTE,
        PRINCIPAL,
        POSTRE,
        BEBIDA
    }
}


