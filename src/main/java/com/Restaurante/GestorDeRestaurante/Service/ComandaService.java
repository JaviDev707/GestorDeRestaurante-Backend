package com.Restaurante.GestorDeRestaurante.Service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.Restaurante.GestorDeRestaurante.DTO.ComandaDTO;
import com.Restaurante.GestorDeRestaurante.DTO.DetalleComandaDTO;
import com.Restaurante.GestorDeRestaurante.Entity.Carta;
import com.Restaurante.GestorDeRestaurante.Entity.Comanda;
import com.Restaurante.GestorDeRestaurante.Entity.DetallesComanda;
import com.Restaurante.GestorDeRestaurante.Entity.Mesa;
import com.Restaurante.GestorDeRestaurante.Repository.CartaRepository;
import com.Restaurante.GestorDeRestaurante.Repository.ComandaRepository;
import com.Restaurante.GestorDeRestaurante.Repository.MesaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComandaService {
    
    private final ComandaRepository comandaRepository;
    private final CartaRepository cartaRepository;
    private final MesaRepository mesaRepository;
    /**
     * Metodo que crea una nueva comanda y guarda los detalles en una lista.
     */
    public Comanda nuevaComanda(ComandaDTO comandaDTO){ 
        
        Mesa mesa = mesaRepository.findById(comandaDTO.mesaID()).orElseThrow(() -> new IllegalArgumentException("La mesa no existe"));

        Comanda comanda = new Comanda();
        comanda.setMesa(mesa);
        comanda.setCuenta(new ArrayList<>());
        double total = 0.0;

        for (DetalleComandaDTO dto : comandaDTO.pedido()) {
            Carta plato = cartaRepository.findById(dto.idPlato())
                .orElseThrow(() -> new IllegalArgumentException("El plato no existe"));
            
            DetallesComanda detallesComanda = new DetallesComanda();
            detallesComanda.setPlato(plato);
            detallesComanda.setCantidad(dto.cantidad());
            detallesComanda.setComanda(comanda);
            detallesComanda.setPrecioUnitario(plato.getPrecio());

            detallesComanda.setSubtotal(plato.getPrecio() * dto.cantidad());
            total += detallesComanda.getSubtotal();

            comanda.getCuenta().add(detallesComanda);
        }
        comanda.setPrecioTotal(total);

        return comandaRepository.save(comanda); //Cascada
    }
    /**
     * Metodo que añade detalles a una comanda ya existente
     */
    public Comanda agregarComanda(Long idComanda, ComandaDTO comandaDTO){ 

        Comanda comanda = comandaRepository.findById(idComanda).orElseThrow(() -> new IllegalArgumentException("La comanda no existe"));
        //comanda.setCuenta(new ArrayList<>());
        double total = comanda.getPrecioTotal();

        for (DetalleComandaDTO dto : comandaDTO.pedido()) {
            Carta plato = cartaRepository.findById(dto.idPlato())
                .orElseThrow(() -> new IllegalArgumentException("El plato no existe"));
            
            DetallesComanda detallesComanda = new DetallesComanda();
            detallesComanda.setPlato(plato);
            detallesComanda.setCantidad(dto.cantidad());
            detallesComanda.setComanda(comanda);
            detallesComanda.setPrecioUnitario(plato.getPrecio());

            detallesComanda.setSubtotal(plato.getPrecio() * dto.cantidad());
            total += detallesComanda.getSubtotal();

            comanda.getCuenta().add(detallesComanda);
        }
        comanda.setPrecioTotal(total);

        return comandaRepository.save(comanda); //Cascada
    }
    /**
     * Metodo que busca la comanda y si existe la borra
     */
    public void eliminarComanda(Long id){
        Comanda comanda = comandaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("La comanda no existe"));
        comandaRepository.delete(comanda);
    }
}
