package com.grupo9.NutriTrack.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo9.NutriTrack.Entities.Usuario;
import com.grupo9.NutriTrack.Repositories.UsuarioRepository;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    @Override
    public List<Usuario> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Usuario> getById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Usuario create(Usuario usuario) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        usuario.setPassword(argon2.hash(1, 1024, 1, usuario.getPassword()));
        return repo.save(usuario);
    }

    @Override
    public Usuario update(Long id, Usuario datos) {
        Usuario usuario = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(datos.getNombre());
        usuario.setApellido(datos.getApellido());
        usuario.setEmail(datos.getEmail());
        usuario.setTelefono(datos.getTelefono());

        if (datos.getPassword() != null && !datos.getPassword().trim().isEmpty()) {
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            usuario.setPassword(argon2.hash(1, 1024, 1, datos.getPassword()));
        }

        return repo.save(usuario);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
