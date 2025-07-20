package com.grupo9.NutriTrack.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo9.NutriTrack.Services.NutritionService;

@RestController
@RequestMapping("/api/nutricion")
@CrossOrigin(origins = "*") // Opcional: si el frontend está separado
public class NutritionController {

    @Autowired
    private NutritionService nutritionService;

    @GetMapping
    public ResponseEntity<?> getNutrition(@RequestParam String alimento) {
        return nutritionService.analyzeFood(alimento)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
