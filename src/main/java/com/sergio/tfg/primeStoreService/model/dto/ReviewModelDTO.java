package com.sergio.tfg.primeStoreService.model.dto;

import com.sergio.tfg.primeStoreService.model.enums.reviewEnums.ReviewType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ReviewModelDTO {
  private Integer id;
  private double rate;
  private String comment;
}
