package com.grupo9.NutriTrack.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo9.NutriTrack.Entities.Ingrediente;
import com.grupo9.NutriTrack.Entities.Receta;
import com.grupo9.NutriTrack.Repositories.IngredienteRepository;
import com.grupo9.NutriTrack.Repositories.RecetaRepository;

@Service
public class RecetaServiceImpl implements RecetaService {

    @Autowired
    private RecetaRepository recetaRepo;

    @Autowired
    private IngredienteRepository ingredienteRepo;

    @Override
    public Receta crearReceta(Receta receta) {
        validarIngredientes(receta.getIngredientes());
        calcularTotales(receta);
        return recetaRepo.save(receta);
    }

    private void validarIngredientes(List<Ingrediente> ingredientes) {
        for (Ingrediente ing : ingredientes) {
            if (ing.getId() == null || !ingredienteRepo.existsById(ing.getId())) {
                throw new IllegalArgumentException("Ingrediente con ID inválido: " + ing.getId());
            }
        }
    }

    @Override
    public List<Receta> listarRecetasPorUsuario(String email) {
        return recetaRepo.findByUsuarioEmail(email);
    }

    @Override
    public Optional<Receta> obtenerPorId(Long id) {
        return recetaRepo.findById(id);
    }

    @Override
    public Receta actualizarReceta(Long id, Receta datos) {
        Receta receta = recetaRepo.findById(id).orElseThrow(() -> new RuntimeException("No encontrada"));

        receta.setNombre(datos.getNombre());
        receta.setDescripcion(datos.getDescripcion());
        receta.setIngredientes(datos.getIngredientes());
        calcularTotales(receta);

        return recetaRepo.save(receta);
    }

    @Override
    public void eliminarReceta(Long id) {
        recetaRepo.deleteById(id);
    }

    private void calcularTotales(Receta receta) {
        double totalCal = 0, grasa = 0, azucar = 0, sodio = 0, prote = 0;
        for (Ingrediente ing : receta.getIngredientes()) {
            totalCal += ing.getCalorias();
            grasa += ing.getGrasa();
            azucar += ing.getAzucar();
            sodio += ing.getSodio();
            prote += ing.getProteina();
        }
        receta.setTotalCalorias(totalCal);
        receta.setTotalGrasa(grasa);
        receta.setTotalAzucar(azucar);
        receta.setTotalSodio(sodio);
        receta.setTotalProteina(prote);
    }
}
