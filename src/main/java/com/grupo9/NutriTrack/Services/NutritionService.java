package com.grupo9.NutriTrack.Services;

import java.util.Optional;

import com.grupo9.NutriTrack.dtos.NutritionInfo;

public interface NutritionService {
    Optional<NutritionInfo> analyzeFood(String foodName);
}
