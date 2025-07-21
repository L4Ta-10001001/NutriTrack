package com.grupo9.NutriTrack.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo9.NutriTrack.Entities.Receta;

public interface RecetaRepository extends JpaRepository<Receta, Long> {
    List<Receta> findByUsuarioEmail(String email);
}
