package com.sergio.tfg.primeStoreService.controller.impl;

import com.sergio.tfg.primeStoreService.controller.WalletController;
import com.sergio.tfg.primeStoreService.model.dto.WalletModelDTO;
import com.sergio.tfg.primeStoreService.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class WalletControllerImpl implements WalletController {
  private final WalletService walletService;
  @Override
  @GetMapping("/getWalletByUserId/{id}")
  public WalletModelDTO getWalletByUserId(@PathVariable Integer id) {
    return walletService.getWalletByUserId(id);
  }

  @Override
  @PostMapping("/saveWallet")
  public WalletModelDTO saveWallet(@RequestBody WalletModelDTO walletModelDTO) {
    return walletService.saveWallet(walletModelDTO);
  }

  @Override
  @PutMapping("/updateWalletAmount/{amount},{walletId}")
  public void updateWalletAmount(@PathVariable double amount, @PathVariable Integer walletId) {
    walletService.updateWalletAmount(amount, walletId);
  }
}
