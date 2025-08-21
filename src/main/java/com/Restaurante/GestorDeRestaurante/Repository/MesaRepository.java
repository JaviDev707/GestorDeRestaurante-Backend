package com.Restaurante.GestorDeRestaurante.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Restaurante.GestorDeRestaurante.Entity.Mesa;
import com.Restaurante.GestorDeRestaurante.Entity.Reserva.Turno;

public interface MesaRepository extends JpaRepository<Mesa, Long>{
    
    @Query("SELECT m FROM Mesa m WHERE m.estadoMesa = 'LIBRE' " +
        "AND m.capacidad >= :comensales " +
        "AND m.id NOT IN (" +
        "  SELECT r.mesa.id FROM Reserva r " +
        "  WHERE r.fecha = :fecha AND r.turno = :turno" +
        ")")
    List <Mesa> findMesasLibres(@Param("fecha") LocalDate fecha, @Param("turno") Turno turno, @Param("comensales") int comensales);

}
