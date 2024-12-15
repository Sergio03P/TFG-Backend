package com.sergio.tfg.primeStoreService.service;

import com.sergio.tfg.primeStoreService.model.ImageModel;
import com.sergio.tfg.primeStoreService.model.ProductModel;

import java.util.List;

public interface ImageService {
  public List<String> getAllImages(Integer id);
  public List<ImageModel> addImages(List<String> images, ProductModel productModel);
}
