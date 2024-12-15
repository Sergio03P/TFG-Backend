package com.sergio.tfg.primeStoreService.model.dto;

import com.sergio.tfg.primeStoreService.model.enums.feeEnums.FeeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeeModelDTO {
  private Integer id;
  private String name;
  private FeeType feeType;
  private double amount;
}
