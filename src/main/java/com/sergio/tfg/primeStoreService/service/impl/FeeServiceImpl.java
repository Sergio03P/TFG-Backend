package com.sergio.tfg.primeStoreService.service.impl;

import com.sergio.tfg.primeStoreService.exception.CustomException;
import com.sergio.tfg.primeStoreService.model.FeeModel;
import com.sergio.tfg.primeStoreService.model.dto.FeeModelDTO;
import com.sergio.tfg.primeStoreService.repository.FeeRepository;
import com.sergio.tfg.primeStoreService.service.FeeService;
import com.sergio.tfg.primeStoreService.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeeServiceImpl implements FeeService {
  private final FeeRepository feeRepository;
  private final Mapper mapper;
  @Override
  public List<FeeModelDTO> getFeesList() {
    return feeRepository.findAll().stream().map((fee) -> mapper.toFeeModelDTO(fee)).collect(Collectors.toList());
  }

  @Override
  public FeeModelDTO getFeeById(Integer id) {
    return mapper.toFeeModelDTO(feeRepository.findById(id)
            .orElseThrow(() -> new CustomException("Not fee found by id: " + id)));
  }

  @Override
  public FeeModelDTO findByName(String name) {
    return mapper.toFeeModelDTO(feeRepository.findByName(name));
  }

  @Override
  public FeeModelDTO saveFee(FeeModelDTO feeModelDTO) {
    FeeModel feeModel = mapper.fromFeeModelDTO(feeModelDTO);
    return mapper.toFeeModelDTO(feeRepository.save(feeModel));
  }
}
