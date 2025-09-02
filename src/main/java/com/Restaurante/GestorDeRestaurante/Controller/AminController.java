package com.Restaurante.GestorDeRestaurante.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Restaurante.GestorDeRestaurante.Entity.Carta;
import com.Restaurante.GestorDeRestaurante.Entity.Usuario;
import com.Restaurante.GestorDeRestaurante.Repository.UsuarioRepository;
import com.Restaurante.GestorDeRestaurante.Service.CartaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AminController {
    
    private final UsuarioRepository usuarioRepository;
    private final CartaService cartaService;

    @GetMapping("/verusuarios")
    public List<Usuario> veUsuarios(){
        System.out.println("Acceso ADMIN concedido.");
        return usuarioRepository.findAll();
    }

    @PostMapping("/crearplato")
    public ResponseEntity<Carta> crearPlato(@RequestBody Carta plato){
        cartaService.nuevoPlato(plato);
        return ResponseEntity.status(HttpStatus.CREATED).body(plato);
    }

}
