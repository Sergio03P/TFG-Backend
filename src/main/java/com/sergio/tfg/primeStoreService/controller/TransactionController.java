package com.sergio.tfg.primeStoreService.controller;

import com.sergio.tfg.primeStoreService.model.dto.TransactionModelDTO;

import java.util.List;

public interface TransactionController {
  List<TransactionModelDTO> getAllTransactions();
  List<TransactionModelDTO> getAllTransactionsFromUserId(Integer id);
  TransactionModelDTO getTransactionByProductId(Integer id);
  TransactionModelDTO saveTransaction(TransactionModelDTO transactionModelDTO);
  TransactionModelDTO updateTransaction(TransactionModelDTO transactionModelDTO);
}
