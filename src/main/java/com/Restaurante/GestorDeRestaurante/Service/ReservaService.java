package com.Restaurante.GestorDeRestaurante.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.Restaurante.GestorDeRestaurante.Entity.Historial_Reservas;
import com.Restaurante.GestorDeRestaurante.Entity.Mesa;
import com.Restaurante.GestorDeRestaurante.Entity.Reserva.EstadoReserva;
import com.Restaurante.GestorDeRestaurante.Entity.Reserva.Turno;
import com.Restaurante.GestorDeRestaurante.Entity.Usuario;
import com.Restaurante.GestorDeRestaurante.Entity.Reserva;
import com.Restaurante.GestorDeRestaurante.Repository.HistorialRepository;
import com.Restaurante.GestorDeRestaurante.Repository.MesaRepository;
import com.Restaurante.GestorDeRestaurante.Repository.ReservaRepository;
import com.Restaurante.GestorDeRestaurante.Repository.UsuarioRepository;

import jakarta.transaction.Transactional;

import org.springframework.security.core.Authentication;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservaService {
    
    private final ReservaRepository reservaRepository;
    private final MesaRepository mesaRepository;
    private final UsuarioRepository usuarioRepository;
    private final HistorialRepository historialRepository;
    /**
     * Metodo que muestra todas las reservas
     */
    public List<Reserva> listarReservas(){
        return reservaRepository.findAll();
    }
    /**
     * Metodo que muestra las reservas de un día y un turno
     */
    public List<Reserva> filtroFechaTurno(LocalDate fecha, Turno turno){
        return reservaRepository.findByFechaTurno(fecha, turno);
    }
    /**
     * Metodo que se encarga de reservar una mesa.
     * Obtiene el usuario del JWT y le asigna una mesa segun el numero de comensales, la fecha y el turno.
     * Guarda la Reserva y la marca como CONFIRMADA.
     */
    public Reserva reservarMesa(Reserva reserva){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); // username/email

        Usuario cliente = usuarioRepository.findByEmail(email);
        if (cliente == null) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }

        List<Mesa> mesasDisponibles = mesaRepository.findMesasLibres(reserva.getFecha(),reserva.getTurno(),reserva.getNumComensales());
        if (mesasDisponibles.isEmpty()) {
            throw new IllegalStateException("No hay mesas disponibles en esa fecha/turno");
        }

        Mesa mesa = mesasDisponibles.get(0);

        reserva.setCliente(cliente);
        reserva.setMesa(mesa);
        reserva.setEstado(EstadoReserva.CONFIRMADA);
        
        reservaRepository.save(reserva);
        System.out.println("Nueva reserva confirmada: " + reserva);

        return reserva;
    }
    /**
     * Metodo que marca la reserva como CANCELADA, la guarda en el historial y la borra de la tabla Reserva
     */
    public boolean borrarReserva(Long id){

        Optional<Reserva> reservaOPT = reservaRepository.findById(id);
        if (reservaOPT.isEmpty()) {
            return false;
        }

        Reserva reservaCancelada = reservaOPT.get();
        reservaCancelada.setEstado(EstadoReserva.CANCELADA);
        moverReservas(reservaCancelada);
        reservaRepository.delete(reservaCancelada);

        System.out.println("Cancelando reserva..." + reservaCancelada.getFecha() + " " + reservaCancelada.getCliente().getNombre());
        return true;
    }
    /**
     * Metodo que se encarga de guardar las reservas del día anterior en un historial y las borrarlas de la tabla de Reserva
     */
    @Transactional
    public void moverReservasDelDia(LocalDate fecha){

        List<Reserva> reservasAyer = reservaRepository.findByFecha(fecha);

        for (Reserva reserva : reservasAyer) {         
            moverReservas(reserva);
            reservaRepository.delete(reserva);
        }
    }
    /**
     * Metodo privado que mueve las reservas de la tabla Reserva a Historial_Reservas
     */
    private void moverReservas (Reserva reserva){

        Historial_Reservas historial = new Historial_Reservas();

        historial.setCliente(reserva.getCliente());
        historial.setMesa(reserva.getMesa());
        historial.setNumComensales(reserva.getNumComensales());
        historial.setFecha(reserva.getFecha());
        historial.setTurno(reserva.getTurno());
        historial.setEstado(reserva.getEstado());

        historialRepository.save(historial);    
    }
}
