package com.sergio.tfg.primeStoreService.controller.impl;

import com.sergio.tfg.primeStoreService.controller.FeeController;
import com.sergio.tfg.primeStoreService.model.dto.FeeModelDTO;
import com.sergio.tfg.primeStoreService.service.FeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class FeeControllerImpl implements FeeController {
  private final FeeService feeService;
  @Override
  @GetMapping("/getFeesList")
  public List<FeeModelDTO> getFeesList() {
    return feeService.getFeesList();
  }

  @Override
  @GetMapping("/getFeeById/{id}")
  public FeeModelDTO getFeeById(@PathVariable Integer id) {
    return feeService.getFeeById(id);
  }

  @Override
  @GetMapping("/getFeeByName/{name}")
  public FeeModelDTO findByName(@PathVariable String name) {
    return feeService.findByName(name);
  }

  @Override
  @PostMapping("/saveFee")
  public FeeModelDTO saveFee(@RequestBody FeeModelDTO feeModelDTO) {
    return feeService.saveFee(feeModelDTO);
  }
}
