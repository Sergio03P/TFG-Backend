package com.sergio.tfg.primeStoreService.util;

import com.sergio.tfg.primeStoreService.model.*;
import com.sergio.tfg.primeStoreService.model.dto.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
//En esta clase se feinen los métodos de mapper para pasar de DTO a entidad y viceversa
public class Mapper {
  private final ModelMapper modelMapper;

  public UserModelDTO toUserModelDTO(UserModel userModel){
    return modelMapper.map(userModel, UserModelDTO.class);
  }

  public UserModel fromUserModelDTO(UserModelDTO userModelDTO){
    return modelMapper.map(userModelDTO, UserModel.class);
  }

  public ProductModelDTO toProductModelDTO(ProductModel productModel){
    ProductModelDTO dto = new ProductModelDTO();
    dto.setId(productModel.getId());
    dto.setName(productModel.getName());
    dto.setDescription(productModel.getDescription());
    dto.setPrice(productModel.getPrice());
    dto.setDate(productModel.getDate());
    dto.setCategory(productModel.getCategory());
    dto.setOwnerUserId(productModel.getUser().getId());

    // Transformación manual para obtener solo las URLs
    List<String> imageUrls = productModel.getImages().stream()
            .map(ImageModel::getImage)  // Mapeo solo a la URL de la imagen
            .collect(Collectors.toList());
    dto.setImages(imageUrls);

    return dto;
  }

  public ProductModel fromProductModelDTO(ProductModelDTO productModelDTO){
    return modelMapper.map(productModelDTO, ProductModel.class);
  }

  public TransactionModel fromTransactionModelDTO(TransactionModelDTO transactionModelDTO) {
    return modelMapper.map(transactionModelDTO, TransactionModel.class);
  }

  public TransactionModelDTO toTransactionModelDTO(TransactionModel transactionModel) {
    return modelMapper.map(transactionModel, TransactionModelDTO.class);
  }
  public WalletModelDTO toWalletModelDTO(WalletModel walletModel){
    return modelMapper.map(walletModel, WalletModelDTO.class);
  }

  public WalletModel fromWalletModelDTO(WalletModelDTO walletModelDTO){
    return modelMapper.map(walletModelDTO, WalletModel.class);
  }

  public FeeModelDTO toFeeModelDTO(FeeModel feeModel){
    return modelMapper.map(feeModel, FeeModelDTO.class);
  }

  public FeeModel fromFeeModelDTO(FeeModelDTO feeModelDTO){
    return modelMapper.map(feeModelDTO, FeeModel.class);
  }

  public TransactionFeeModelDTO toTransactionFeeModelDTO(TransactionFeeModel transactionFeeModel){
    return modelMapper.map(transactionFeeModel, TransactionFeeModelDTO.class);
  }

  public ReviewModel fromReviewDTO(ReviewModelDTO reviewModelDTO){
    return modelMapper.map(reviewModelDTO, ReviewModel.class);
  }

  public ReviewModelDTO toReviewModelDTO(ReviewModel reviewModel){
    return  modelMapper.map(reviewModel, ReviewModelDTO.class);
  }
}
