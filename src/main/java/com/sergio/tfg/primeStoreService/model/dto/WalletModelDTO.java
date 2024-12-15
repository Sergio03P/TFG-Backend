package com.sergio.tfg.primeStoreService.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletModelDTO {
  private Integer id;
  private double amount;
  private Integer userModelId;
}
