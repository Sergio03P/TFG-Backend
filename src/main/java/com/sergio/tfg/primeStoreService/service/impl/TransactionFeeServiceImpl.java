package com.sergio.tfg.primeStoreService.service.impl;
import com.sergio.tfg.primeStoreService.model.dto.TransactionFeeModelDTO;
import com.sergio.tfg.primeStoreService.repository.TransactionFeeRepository;
import com.sergio.tfg.primeStoreService.service.TransactionFeeService;
import com.sergio.tfg.primeStoreService.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionFeeServiceImpl implements TransactionFeeService {
  private final TransactionFeeRepository transactionFeeRepository;
  private final Mapper mapper;

  @Override
  public List<TransactionFeeModelDTO> getTransactionFeeModelByTransactionId(Integer transactionId) {
    return transactionFeeRepository.findByTransactionId(transactionId).stream().map((transactionFee) ->{
      return mapper.toTransactionFeeModelDTO(transactionFee);
    }).collect(Collectors.toList());
  }
}
