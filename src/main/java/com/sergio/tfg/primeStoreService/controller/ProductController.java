package com.sergio.tfg.primeStoreService.controller;

import com.sergio.tfg.primeStoreService.model.dto.ProductModelDTO;

import java.util.List;

public interface ProductController {
  public ProductModelDTO getProductById(Integer id);
  public List<ProductModelDTO> getAllOnSaleProductFromUserId(Integer userId);
  public List<ProductModelDTO> getAllOnSaleProducts(Integer userId);
  public ProductModelDTO saveProduct(ProductModelDTO productDTO, Integer id);
  public ProductModelDTO updateProduct(ProductModelDTO productModelDTO);
  void deleteProductById(Integer productId);
}
