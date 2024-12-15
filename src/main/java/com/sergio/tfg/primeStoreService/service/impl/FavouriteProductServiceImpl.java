package com.sergio.tfg.primeStoreService.service.impl;

import com.sergio.tfg.primeStoreService.exception.CustomException;
import com.sergio.tfg.primeStoreService.model.ProductModel;
import com.sergio.tfg.primeStoreService.model.UserModel;
import com.sergio.tfg.primeStoreService.model.dto.ProductModelDTO;
import com.sergio.tfg.primeStoreService.repository.ProductRepository;
import com.sergio.tfg.primeStoreService.repository.UserRepository;
import com.sergio.tfg.primeStoreService.service.FavouriteProductService;
import com.sergio.tfg.primeStoreService.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavouriteProductServiceImpl implements FavouriteProductService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private Mapper mapper;

  @Override
  public List<ProductModelDTO> getAllFavouriteProducts() {
    return productRepository.findAllFavouriteProducts().stream().map((productModel -> mapper.toProductModelDTO(productModel))).collect(Collectors.toList());
  }

  @Override
  public List<ProductModelDTO> getFavouriteUserProducts(Integer id) {
    Optional<UserModel> userModel = userRepository.findById(id);
    List<ProductModel> productModelList = userModel.map(UserModel::getFavouriteProductsModelList).orElseThrow(() -> new CustomException("User not found"));
    return productModelList.stream().map(productModel -> mapper.toProductModelDTO(productModel)).collect(Collectors.toList());
  }

  @Override
  public ProductModelDTO addFavouriteProduct(Integer userId, Integer productId) {
    UserModel userModel = userRepository.findById(userId).orElseThrow(() -> new CustomException("User not found"));
    ProductModel productModel = productRepository.findById(productId).orElseThrow(() -> new CustomException("Product not found"));

    if(!userModel.getFavouriteProductsModelList().contains(productModel)){
      userModel.getFavouriteProductsModelList().add(productModel);
      userRepository.save(userModel);
      return mapper.toProductModelDTO(productModel);
    }else {
      return null;
    }
  }

  @Override
  public ProductModelDTO removeFavouriteProduct(Integer userId, Integer productId) {
    UserModel userModel = userRepository.findById(userId).orElseThrow(() -> new CustomException("User not found"));
    ProductModel productModel = productRepository.findById(productId).orElseThrow(() -> new CustomException("Product not found"));

    userModel.getFavouriteProductsModelList().remove(productModel);
    userRepository.save(userModel);
    return mapper.toProductModelDTO(productModel);
  }
}
