package com.Restaurante.GestorDeRestaurante.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Restaurante.GestorDeRestaurante.Entity.Comanda;

@Repository
public interface ComandaRepository extends JpaRepository<Comanda, Long>{
    
}
