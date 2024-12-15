package com.sergio.tfg.primeStoreService.controller.impl;

import com.sergio.tfg.primeStoreService.controller.TransactionController;
import com.sergio.tfg.primeStoreService.model.dto.TransactionModelDTO;
import com.sergio.tfg.primeStoreService.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class TransactionControllerImpl implements TransactionController {
  private final TransactionService transactionService;

  @Override
  @GetMapping("/getAllTransactions")
  public List<TransactionModelDTO> getAllTransactions() {
    return transactionService.getAllTransactions();
  }

  @Override
  @GetMapping("/getAllTransactionsFromIdUser/{id}")
  public List<TransactionModelDTO> getAllTransactionsFromUserId(@PathVariable Integer id) {
    return transactionService.getAllTransactionsFromUserId(id);
  }

  @Override
  @GetMapping("/getTransactionByProductId/{id}")
  public TransactionModelDTO getTransactionByProductId(@PathVariable Integer id) {
    return transactionService.getTransactionByProductId(id);
  }

  @Override
  @PostMapping("/saveTransaction")
  public TransactionModelDTO saveTransaction(@RequestBody TransactionModelDTO transactionModelDTO) {
    return transactionService.saveTransaction(transactionModelDTO);
  }

  @Override
  @PutMapping("/updateTransaction")
  public TransactionModelDTO updateTransaction(@RequestBody TransactionModelDTO transactionModelDTO) {
    return transactionService.updateTransaction(transactionModelDTO);
  }
}
