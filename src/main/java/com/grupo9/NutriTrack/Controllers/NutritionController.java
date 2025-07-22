package com.grupo9.NutriTrack.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo9.NutriTrack.Services.NutritionService;
import com.grupo9.NutriTrack.dtos.NutritionInfo;

@RestController
@RequestMapping("/api/nutricion")
@CrossOrigin(origins = "*") // Permitir peticiones desde el frontend
public class NutritionController {

    @Autowired
    private NutritionService nutritionService;

    // Endpoint: GET /api/nutricion/nutella
    @GetMapping("/{alimento}")
    public ResponseEntity<NutritionInfo> getNutrition(@PathVariable String alimento) {
        return nutritionService.analyzeFood(alimento)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
