package com.Restaurante.GestorDeRestaurante.Entity;

import java.time.LocalDate;

import com.Restaurante.GestorDeRestaurante.Entity.Reserva.EstadoReserva;
import com.Restaurante.GestorDeRestaurante.Entity.Reserva.Turno;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Historial_Reservas {
    
    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Usuario cliente;
    @ManyToOne
    private Mesa mesa;
    private int numComensales;

    private LocalDate fecha;
    private Turno turno;

    @Enumerated(EnumType.STRING)
    private EstadoReserva estado;

}
