package com.Restaurante.GestorDeRestaurante.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Restaurante.GestorDeRestaurante.DTO.ReservaDTO;
import com.Restaurante.GestorDeRestaurante.Entity.Reserva;
import com.Restaurante.GestorDeRestaurante.Entity.Reserva.Turno;
import com.Restaurante.GestorDeRestaurante.Service.ReservaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaController { 
    
    private final ReservaService reservaService;

    @GetMapping("/todas")
    public ResponseEntity<List<Reserva>> verReservas(){

        List<Reserva> reservas = reservaService.listarReservas();
        if (reservas.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No content
        }

        return ResponseEntity.ok(reservas); //200 Ok
    }

    @GetMapping("/reservasturno/{fecha}/{turno}")
    public ResponseEntity<List<Reserva>> verReservasFechaTurno(@PathVariable LocalDate fecha, @PathVariable Turno turno){

        List<Reserva> reservas = reservaService.filtroFechaTurno(fecha, turno);
        if (reservas.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No content
        }
        
        return ResponseEntity.ok(reservas); // 200 Ok
    }

    @PostMapping("/nueva")
    public ResponseEntity<Reserva> nuevaReserva(@RequestBody ReservaDTO reservaDTO){
        Reserva nueva = reservaService.reservarMesa(reservaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @DeleteMapping("/cancelar/{id}")
    public ResponseEntity<Reserva> cancelarReserva(@PathVariable Long id){

        boolean eliminado = reservaService.borrarReserva(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build(); //404 Not found
        }
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    // Prueba de acceso con el token
    @GetMapping("/pruebita")
    public void pruebita(){
        System.out.println("Acceso concedido con el Token");
    }
}
