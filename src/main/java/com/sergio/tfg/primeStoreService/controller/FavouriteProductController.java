package com.sergio.tfg.primeStoreService.controller;

import com.sergio.tfg.primeStoreService.model.dto.ProductModelDTO;

import java.util.List;

public interface FavouriteProductController {
  public List<ProductModelDTO> getAllFavouriteProducts();
  public List<ProductModelDTO> getFavouriteUserProducts(Integer id);
  public ProductModelDTO addFavouriteProduct(Integer userId, Integer productId);
  public ProductModelDTO removeFavouriteProduct(Integer userId, Integer productId);
}
