package com.sergio.tfg.primeStoreService.service;

import com.sergio.tfg.primeStoreService.model.dto.TransactionModelDTO;
import java.util.List;


public interface TransactionService {
  List<TransactionModelDTO> getAllTransactions();
  List<TransactionModelDTO> getAllTransactionsFromUserId(Integer id);
  TransactionModelDTO getTransactionByProductId(Integer id);
  TransactionModelDTO saveTransaction(TransactionModelDTO transactionModelDTO);
  TransactionModelDTO updateTransaction(TransactionModelDTO transactionModelDTO);
}
