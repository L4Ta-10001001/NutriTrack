package com.grupo9.NutriTrack.dtos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecetaDTO {
    private String nombre;
    private String descripcion;
    private String usuarioEmail;
    private List<Long> ingredientes; // solo IDs
}
