package com.grupo9.NutriTrack.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.grupo9.NutriTrack.dtos.NutritionInfo;
import com.grupo9.NutriTrack.dtos.OFFResponse;

@Service
public class NutritionServiceImpl implements NutritionService {

    private final RestTemplate restTemplate;

    public NutritionServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public Optional<NutritionInfo> analyzeFood(String foodName) {
        // Limpiar nombre de alimento (evita errores con espacios y símbolos)
        String cleanName = foodName.trim().replaceAll("[^a-zA-Z0-9\\s]", "").replaceAll("\\s+", "+");
        String apiUrl = "https://world.openfoodfacts.org/cgi/search.pl?search_terms=" + cleanName + "&json=true";

        try {
            OFFResponse response = restTemplate.getForObject(apiUrl, OFFResponse.class);
            if (response != null && response.getProducts() != null && !response.getProducts().isEmpty()) {
                NutritionInfo info = response.toNutritionInfo();

                List<String> advertencias = new ArrayList<>();
                List<String> recomendaciones = new ArrayList<>();

                // Umbrales recomendados
                double SODIO_MAX = 1000;   // mg
                double AZUCAR_MAX = 50;    // g
                double GRASA_MAX = 20;     // g
                double CALORIAS_MAX = 300; // kcal

                if (info.getSodium() > SODIO_MAX)
                    advertencias.add("⚠️ Alto contenido de sodio: " + info.getSodium() + " mg");
                if (info.getSugar() > AZUCAR_MAX)
                    advertencias.add("⚠️ Alto contenido de azúcar: " + info.getSugar() + " g");
                if (info.getFat() > GRASA_MAX)
                    advertencias.add("⚠️ Alto contenido de grasa: " + info.getFat() + " g");

                if (info.getCalories() > CALORIAS_MAX) {
                    recomendaciones.add("🏃 Camina 30 min para quemar " + info.getCalories() + " kcal.");
                    recomendaciones.add("🧘 Haz yoga o cardio 20 minutos.");
                }

                info.setAdvertencias(advertencias);
                info.setRecomendaciones(recomendaciones);

                return Optional.of(info);
            }
        } catch (Exception e) {
            System.err.println("Error al analizar el alimento: " + e.getMessage());
        }

        return Optional.empty();
    }
}
