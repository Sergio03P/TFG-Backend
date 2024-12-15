package com.sergio.tfg.primeStoreService.controller;

import com.sergio.tfg.primeStoreService.model.dto.FeeModelDTO;

import java.util.List;

public interface FeeController {
  List<FeeModelDTO> getFeesList();
  FeeModelDTO getFeeById(Integer id);
  FeeModelDTO findByName(String name);
  FeeModelDTO saveFee(FeeModelDTO feeModelDTO);
}
