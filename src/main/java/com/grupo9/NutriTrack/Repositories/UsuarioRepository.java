package com.grupo9.NutriTrack.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.grupo9.NutriTrack.Entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @NonNull
    List<Usuario> findAll(); // Reemplaza getUsuarios()

    void deleteById(@NonNull Long id); // Reemplaza eliminar()

    Optional<Usuario> findByEmail(String email); // Para autenticación
}
