package com.sergio.tfg.primeStoreService.service.impl;

import com.sergio.tfg.primeStoreService.exception.CustomException;
import com.sergio.tfg.primeStoreService.model.UserModel;
import com.sergio.tfg.primeStoreService.model.WalletModel;
import com.sergio.tfg.primeStoreService.model.dto.WalletModelDTO;
import com.sergio.tfg.primeStoreService.repository.UserRepository;
import com.sergio.tfg.primeStoreService.repository.WalletRepository;
import com.sergio.tfg.primeStoreService.service.WalletService;
import com.sergio.tfg.primeStoreService.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
  private final WalletRepository walletRepository;
  private final UserRepository userRepository;
  private final Mapper mapper;
  @Override
  public WalletModelDTO getWalletByUserId(Integer id) {
    return mapper.toWalletModelDTO(walletRepository.findById(id).orElseThrow(() -> new CustomException("No wallet found by user id: " + id)));
  }

  @Override
  public WalletModelDTO saveWallet(WalletModelDTO walletModelDTO) {
    UserModel userModel = userRepository.findById(walletModelDTO.getUserModelId()).orElseThrow(()->{
      return new CustomException("User not found by id: " + walletModelDTO.getUserModelId());
    });
    WalletModel walletModel = mapper.fromWalletModelDTO(walletModelDTO);
    walletModel.setUserModel(userModel);
    return mapper.toWalletModelDTO(walletRepository.save(walletModel));
  }

  public void updateWalletAmount(double amount, Integer walletId){
    WalletModel walletModel = walletRepository.findById(walletId)
            .orElseThrow(() -> new CustomException("No wallet found by id: " + walletId));
    walletModel.setAmount(walletModel.getAmount() + amount);
    walletRepository.save(walletModel);

  }
}
