package com.sergio.tfg.primeStoreService.service;

import com.sergio.tfg.primeStoreService.model.dto.TransactionFeeModelDTO;
import java.util.List;

public interface TransactionFeeService {
  List<TransactionFeeModelDTO> getTransactionFeeModelByTransactionId(Integer transactionId);
}
