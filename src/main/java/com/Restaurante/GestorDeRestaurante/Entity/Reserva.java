package com.Restaurante.GestorDeRestaurante.Entity;

import java.time.LocalDate;

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
public class Reserva {
    
    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Usuario cliente;
    @ManyToOne
    private Mesa mesa;
    private int numComensales;

    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    private Turno turno;

    @Enumerated(EnumType.STRING)
    private EstadoReserva estado;


    public enum EstadoReserva {
        PENDIENTE,
        CONFIRMADA,
        CANCELADA
    }

    // Turnos de comida del restaurante
    public enum Turno{
        ALMUERZO1, // 14:00 - 14:59
        ALMUERZO2, //15:00 - 15:59

        CENA1, //21:00 - 21:59
        CENA2, //22:00 - 22:59
        CENA3 //23:00 - 23:59
    }
}
