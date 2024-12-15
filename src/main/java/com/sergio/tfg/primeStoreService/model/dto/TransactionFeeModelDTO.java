package com.sergio.tfg.primeStoreService.model.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TransactionFeeModelDTO {
  private Integer id;
  private Integer TransactionId;
  private Integer FeeId;
}
