package com.grupo9.NutriTrack.Services;

import java.util.List;
import java.util.Optional;

import com.grupo9.NutriTrack.Entities.Usuario;

public interface UsuarioService {
    List<Usuario> getAll();
    Optional<Usuario> getById(Long id);
    Usuario create(Usuario usuario);
    Usuario update(Long id, Usuario usuario);
    void delete(Long id);
}
