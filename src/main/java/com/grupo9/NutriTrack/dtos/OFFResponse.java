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

  public void setProducts(List<Product> products) { // << NECESARIO PARA JACKSON
    this.products = products;
  }

  public NutritionInfo toNutritionInfo() {
    if (products == null || products.isEmpty())
      return null;

    Product p = products.get(0);
    Nutriments n = p.getNutriments();

    NutritionInfo info = new NutritionInfo();
    info.setName(p.getProductName());
    info.setCalories(n.getEnergyKcal100g());
    info.setFat(n.getFat100g());
    info.setSugar(n.getSugars100g());
    info.setSodium(n.getSodium100g() * 1000); // g → mg
    info.setProtein(n.getProteins100g());
    return info;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Product {

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("nutriments")
    private Nutriments nutriments;

    public String getProductName() {
      return productName;
    }

    public void setProductName(String productName) {
      this.productName = productName;
    }

    public Nutriments getNutriments() {
      return nutriments;
    }

    public void setNutriments(Nutriments nutriments) {
      this.nutriments = nutriments;
    }
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Nutriments {

    @JsonProperty("energy-kcal_100g")
    private double energyKcal100g;

    @JsonProperty("fat_100g")
    private double fat100g;

    @JsonProperty("sugars_100g")
    private double sugars100g;

    @JsonProperty("sodium_100g")
    private double sodium100g;

    @JsonProperty("proteins_100g")
    private double proteins100g;

    public double getEnergyKcal100g() {
      return energyKcal100g;
    }

    public void setEnergyKcal100g(double energyKcal100g) {
      this.energyKcal100g = energyKcal100g;
    }

    public double getFat100g() {
      return fat100g;
    }

    public void setFat100g(double fat100g) {
      this.fat100g = fat100g;
    }

    public double getSugars100g() {
      return sugars100g;
    }

    public void setSugars100g(double sugars100g) {
      this.sugars100g = sugars100g;
    }

    public double getSodium100g() {
      return sodium100g;
    }

    public void setSodium100g(double sodium100g) {
      this.sodium100g = sodium100g;
    }

    public double getProteins100g() {
      return proteins100g;
    }

    public void setProteins100g(double proteins100g) {
      this.proteins100g = proteins100g;
    }
  }
}
