package com.sergio.tfg.primeStoreService.controller;

import com.sergio.tfg.primeStoreService.model.dto.WalletModelDTO;

public interface WalletController {
  public WalletModelDTO getWalletByUserId(Integer id);
  public WalletModelDTO saveWallet(WalletModelDTO walletModelDTO);
  public void updateWalletAmount(double amount, Integer walletId);
}
