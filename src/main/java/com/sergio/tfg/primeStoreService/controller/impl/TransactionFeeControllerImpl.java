package com.sergio.tfg.primeStoreService.controller.impl;

import com.sergio.tfg.primeStoreService.controller.TransactionFeeController;
import com.sergio.tfg.primeStoreService.model.dto.TransactionFeeModelDTO;
import com.sergio.tfg.primeStoreService.service.TransactionFeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class TransactionFeeControllerImpl implements TransactionFeeController {
  private final TransactionFeeService transactionFeeService;
  @Override
  @GetMapping("/getTransactionFeeModelByTransactionId/{transactionId}")
  public List<TransactionFeeModelDTO> getTransactionFeeModelByTransactionId(@PathVariable Integer transactionId) {
    return transactionFeeService.getTransactionFeeModelByTransactionId(transactionId);
  }
}
