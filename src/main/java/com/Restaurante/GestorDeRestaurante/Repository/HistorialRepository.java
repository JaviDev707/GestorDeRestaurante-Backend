package com.Restaurante.GestorDeRestaurante.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Restaurante.GestorDeRestaurante.Entity.Historial_Reservas;

@Repository
public interface HistorialRepository extends JpaRepository <Historial_Reservas, Long>{
    
}