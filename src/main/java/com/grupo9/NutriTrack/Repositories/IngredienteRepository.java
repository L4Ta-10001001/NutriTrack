package com.grupo9.NutriTrack.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo9.NutriTrack.Entities.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
    List<Ingrediente> findByUsuarioEmail(String email);
}
