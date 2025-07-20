package com.grupo9.NutriTrack.Services;

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
        String apiUrl = "https://world.openfoodfacts.org/cgi/search.pl?search_terms=" + foodName + "&json=true";

        try {
            OFFResponse response = restTemplate.getForObject(apiUrl, OFFResponse.class);
            if (response != null && response.getProducts() != null && !response.getProducts().isEmpty()) {
                return Optional.of(response.toNutritionInfo());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
