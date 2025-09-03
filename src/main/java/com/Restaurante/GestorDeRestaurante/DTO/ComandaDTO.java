package com.Restaurante.GestorDeRestaurante.DTO;

import java.util.List;

public record ComandaDTO(

    Long mesaID,
    List<DetalleComandaDTO> pedido

) {}
