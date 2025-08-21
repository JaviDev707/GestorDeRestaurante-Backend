package com.Restaurante.GestorDeRestaurante.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Entity
@Data
public class DetallesComanda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Comanda comanda;

    @ManyToOne
    private Carta plato;

    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    @PrePersist
    @PreUpdate
    public void calcularSubTotal() {
        this.precioUnitario = plato.getPrecio();   
        this.subtotal = this.precioUnitario * this.cantidad;
    }
}
