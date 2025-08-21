package com.Restaurante.GestorDeRestaurante.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

}
