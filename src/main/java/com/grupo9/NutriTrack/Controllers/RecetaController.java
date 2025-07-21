package com.grupo9.NutriTrack.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo9.NutriTrack.Entities.Ingrediente;
import com.grupo9.NutriTrack.Entities.Receta;
import com.grupo9.NutriTrack.Services.RecetaService;
import com.grupo9.NutriTrack.dtos.RecetaDTO;

@RestController
@RequestMapping("/api/recetas")
@CrossOrigin(origins = "*")
public class RecetaController {

    @Autowired
    private RecetaService recetaService;

    @Autowired
    private com.grupo9.NutriTrack.Repositories.IngredienteRepository ingredienteRepo;

    @PostMapping
    public Receta crearReceta(@RequestBody RecetaDTO recetaDTO) {
        Receta receta = new Receta();
        receta.setNombre(recetaDTO.getNombre());
        receta.setDescripcion(recetaDTO.getDescripcion());
        receta.setUsuarioEmail(recetaDTO.getUsuarioEmail());

        List<Ingrediente> ingredientes = recetaDTO.getIngredientes().stream()
                .map(id -> ingredienteRepo.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Ingrediente con ID inválido: " + id)))
                .toList();

        receta.setIngredientes(ingredientes);
        return recetaService.crearReceta(receta);
    }

    @GetMapping
    public List<Receta> listar(@RequestParam String email) {
        return recetaService.listarRecetasPorUsuario(email);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return recetaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Receta actualizar(@PathVariable Long id, @RequestBody Receta datos) {
        return recetaService.actualizarReceta(id, datos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        recetaService.eliminarReceta(id);
        return ResponseEntity.ok().build();
    }
}
