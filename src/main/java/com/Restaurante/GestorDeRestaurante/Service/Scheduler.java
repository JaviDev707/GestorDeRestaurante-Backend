package com.Restaurante.GestorDeRestaurante.Service;

import java.time.LocalDate;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Scheduler {
    
    private final ReservaService reservaService;

    /**
     * Metodo que se encarga de mover las reservas de ayer a la tabla historial diariamente
     * Se ejecuta cada día a las 14:00
     * Cron: segundo minuto hora día mes díaSemana
     */
    @Scheduled(cron = "0 0 14 * * ?")
    @Transactional
    public void moverReservasDeAyerAlHistorial() {
        LocalDate ayer = LocalDate.now().minusDays(1);
        reservaService.moverReservasDelDia(ayer);
    }
}
