package com.grupo9.NutriTrack.Services;

import java.util.List;
import java.util.Optional;

import com.grupo9.NutriTrack.Entities.Receta;

public interface RecetaService {
    Receta crearReceta(Receta receta);
    List<Receta> listarRecetasPorUsuario(String email);
    Optional<Receta> obtenerPorId(Long id);
    Receta actualizarReceta(Long id, Receta datos);
    void eliminarReceta(Long id);
}
