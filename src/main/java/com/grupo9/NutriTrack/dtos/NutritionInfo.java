package com.grupo9.NutriTrack.dtos;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // genera automáticamente getters, setters, toString, equals, hashCode
@NoArgsConstructor
@AllArgsConstructor
public class NutritionInfo {
  private String name;
  private double calories;
  private double fat;
  private double sugar;
  private double sodium;
  private double protein;

  private List<String> advertencias;
  private List<String> recomendaciones;
}
