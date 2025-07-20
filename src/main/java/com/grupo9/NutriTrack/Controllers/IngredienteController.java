package com.grupo9.NutriTrack.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo9.NutriTrack.Entities.Ingrediente;
import com.grupo9.NutriTrack.Repositories.IngredienteRepository;

@RestController
@RequestMapping("/api/ingredientes")
@CrossOrigin(origins = "*")
public class IngredienteController {

    @Autowired
    private IngredienteRepository ingredienteRepo;

    @GetMapping
    public List<Ingrediente> getAll(@RequestParam(required = false) String email) {
        return (email != null) ? ingredienteRepo.findByUsuarioEmail(email) : ingredienteRepo.findAll();
    }

    @PostMapping
    public Ingrediente create(@RequestBody Ingrediente ingrediente) {
        return ingredienteRepo.save(ingrediente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (ingredienteRepo.existsById(id)) {
            ingredienteRepo.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
