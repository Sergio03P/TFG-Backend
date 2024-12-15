package com.sergio.tfg.primeStoreService.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ImageModelDTO {
  private Integer id;
  private String image;
}
