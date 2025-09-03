package com.Restaurante.GestorDeRestaurante.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Restaurante.GestorDeRestaurante.DTO.ComandaDTO;
import com.Restaurante.GestorDeRestaurante.Entity.Comanda;
import com.Restaurante.GestorDeRestaurante.Service.ComandaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/comandas")
@RequiredArgsConstructor
public class ComandaController {
    
    private final ComandaService comandaService;

    @PostMapping("/nueva")
    public ResponseEntity<Comanda> crearComanda(@RequestBody ComandaDTO dto){
        Comanda comanda = comandaService.nuevaComanda(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(comanda);
    }   

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Comanda> borrarComanda(@PathVariable Long id){
        comandaService.eliminarComanda(id);
        return ResponseEntity.noContent().build(); // 204 No content
    }

    @PostMapping("/{idComanda}/actualizarcomanda")
    public ResponseEntity<Comanda> actualizarComanda(@PathVariable Long idComanda, @RequestBody ComandaDTO dto){
        comandaService.agregarComanda(idComanda, dto);
        return ResponseEntity.ok().build();
    }

}
