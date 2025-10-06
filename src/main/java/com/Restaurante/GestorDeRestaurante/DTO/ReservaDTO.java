package com.Restaurante.GestorDeRestaurante.DTO;

import java.time.LocalDate;

import com.Restaurante.GestorDeRestaurante.Entity.Mesa;
import com.Restaurante.GestorDeRestaurante.Entity.Reserva.EstadoReserva;
import com.Restaurante.GestorDeRestaurante.Entity.Reserva.Turno;
import com.Restaurante.GestorDeRestaurante.Entity.Usuario;

public record ReservaDTO(

    Long id,
    Usuario Cliente,
    Mesa mesa,
    int numComensales,
    LocalDate fecha,
    Turno turno,
    EstadoReserva estado

) {}
