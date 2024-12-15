package com.sergio.tfg.primeStoreService.model.dto;

import com.sergio.tfg.primeStoreService.model.enums.productEnums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModelDTO {
  private Integer id;
  private String name;
  private String description;
  private double price;
  private String date;
  private List<String> images = new ArrayList<>();
  private Category category;
  private int ownerUserId;
}
