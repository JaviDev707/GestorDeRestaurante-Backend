package com.Restaurante.GestorDeRestaurante.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Restaurante.GestorDeRestaurante.Entity.Carta;
import com.Restaurante.GestorDeRestaurante.Repository.CartaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartaService {
    
    private final CartaRepository cartaRepository;

    public List<Carta> listCarta(){
        return cartaRepository.findAll();
    }

    public Carta nuevoPlato(Carta plato){

        if (cartaRepository.existsCartaByPlatoIgnoreCase(plato.getPlato())) {
            throw new IllegalStateException("El plato ya existe en la Carta.");
        }

        System.out.println("Creando nuevo plato en la carta..." + plato);
        return cartaRepository.save(plato);
    }

}
