package com.Restaurante.GestorDeRestaurante.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class DetallesComanda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "comanda_id")
    @JsonIgnore // Evita enviar el campo comanda en cada detalle al cliente
    private Comanda comanda;

    @ManyToOne
    private Carta plato;

    private int cantidad;
    private double precioUnitario;
    private double subtotal;
    
}
