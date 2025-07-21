package com.grupo9.NutriTrack.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OFFResponse {

    @JsonProperty("products")
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public NutritionInfo toNutritionInfo() {
        Product p = products.get(0);
        NutritionInfo info = new NutritionInfo();
        info.name = p.productName;
        info.calories = p.nutriments.energyKcal100g;
        info.fat = p.nutriments.fat100g;
        info.sugar = p.nutriments.sugars100g;
        info.sodium = p.nutriments.sodium100g * 1000; // en mg
        info.protein = p.nutriments.proteins100g;
        return info;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Product {
        @JsonProperty("product_name")
        public String productName;

        @JsonProperty("nutriments")
        public Nutriments nutriments;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Nutriments {
        @JsonProperty("energy-kcal_100g")
        public double energyKcal100g;

        @JsonProperty("fat_100g")
        public double fat100g;

        @JsonProperty("sugars_100g")
        public double sugars100g;

        @JsonProperty("sodium_100g")
        public double sodium100g;

        @JsonProperty("proteins_100g")
        public double proteins100g;
    }
}
