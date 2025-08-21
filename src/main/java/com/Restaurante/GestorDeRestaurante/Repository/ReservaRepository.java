package com.Restaurante.GestorDeRestaurante.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Restaurante.GestorDeRestaurante.Entity.Reserva;
import com.Restaurante.GestorDeRestaurante.Entity.Reserva.Turno;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{
    
    @Query("SELECT r FROM Reserva r WHERE r.fecha = :fecha AND r.turno = :turno")
    public List<Reserva> findByFechaTurno(@Param("fecha") LocalDate fecha, @Param("turno") Turno turno);

    public List<Reserva> findByFecha(LocalDate fecha);

}
