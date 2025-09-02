package com.Restaurante.GestorDeRestaurante.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Restaurante.GestorDeRestaurante.Entity.Carta;

@Repository
public interface CartaRepository extends JpaRepository<Carta, Long>{
    
    public boolean existsCartaByPlatoIgnoreCase(String plato);

}
