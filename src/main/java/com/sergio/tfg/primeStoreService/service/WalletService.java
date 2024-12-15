package com.sergio.tfg.primeStoreService.service;

import com.sergio.tfg.primeStoreService.model.dto.WalletModelDTO;

public interface WalletService {
  public WalletModelDTO getWalletByUserId(Integer id);
  public WalletModelDTO saveWallet(WalletModelDTO walletModelDTO);
  public void updateWalletAmount(double amount, Integer walletId);
}
