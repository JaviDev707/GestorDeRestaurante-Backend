package com.Restaurante.GestorDeRestaurante.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Entity
@Data
public class Comanda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Mesa mesa;

    @OneToMany(mappedBy = "comanda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallesComanda> platos;

    private double precioTotal;

    @PrePersist
    @PreUpdate
    public void calcularPrecioTotal() {
        this.precioTotal = platos.stream().mapToDouble(DetallesComanda::getSubtotal).sum();
    }

}
