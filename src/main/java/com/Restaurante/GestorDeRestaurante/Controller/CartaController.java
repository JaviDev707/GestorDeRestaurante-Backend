package com.Restaurante.GestorDeRestaurante.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Restaurante.GestorDeRestaurante.Entity.Carta;
import com.Restaurante.GestorDeRestaurante.Service.CartaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/carta")
@RequiredArgsConstructor
public class CartaController {
    
    private final CartaService cartaService;

    @GetMapping("/vercarta")
    public List<Carta> verCarta(){
        return cartaService.listCarta();
    }

}
