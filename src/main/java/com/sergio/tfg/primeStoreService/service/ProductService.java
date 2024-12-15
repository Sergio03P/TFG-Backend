package com.sergio.tfg.primeStoreService.service;

import com.sergio.tfg.primeStoreService.model.dto.ProductModelDTO;
import java.util.List;

public interface ProductService {
  public ProductModelDTO getProductById(Integer id);
  public List<ProductModelDTO> getAllOnSaleProductFromUserId(Integer userId);
  public List<ProductModelDTO> getAllOnSaleProducts(Integer userId);
  public ProductModelDTO saveProduct(ProductModelDTO productDTO, Integer id);
  public ProductModelDTO updateProduct(ProductModelDTO productModelDTO);
  public void deleteProductById(Integer productId);

}
