package com.grupo9.NutriTrack.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo9.NutriTrack.Entities.Usuario;
import com.grupo9.NutriTrack.Services.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*") // Permite peticiones desde cualquier frontend
public class UsuarioController {

  @Autowired
  private UsuarioService service;

  @GetMapping
  public List<Usuario> obtenerUsuarios() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  public Usuario obtenerUsuarioPorId(@PathVariable Long id) {
    return service.getById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
  }

  @PostMapping
  public Usuario crearUsuario(@RequestBody Usuario usuario) {
    return service.create(usuario);
  }

  @PutMapping("/{id}")
  public Usuario actualizarUsuario(@PathVariable Long id, @RequestBody Usuario datos) {
    return service.update(id, datos);
  }

  @DeleteMapping("/{id}")
  public String borrarUsuario(@PathVariable Long id) {
    service.delete(id);
    return "Usuario eliminado con ID: " + id;
  }
}
