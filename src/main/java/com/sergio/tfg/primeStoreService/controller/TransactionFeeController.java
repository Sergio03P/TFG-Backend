package com.sergio.tfg.primeStoreService.controller;

import com.sergio.tfg.primeStoreService.model.dto.TransactionFeeModelDTO;
import java.util.List;

public interface TransactionFeeController {
  List<TransactionFeeModelDTO> getTransactionFeeModelByTransactionId(Integer transactionId);
}
