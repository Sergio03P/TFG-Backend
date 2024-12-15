package com.sergio.tfg.primeStoreService.controller;

import com.sergio.tfg.primeStoreService.model.ImageModel;
import com.sergio.tfg.primeStoreService.model.ProductModel;

import java.util.List;

public interface ImageController {
  public List<String> getAllImages(Integer id);
  public List<ImageModel> addImages(List<String> images, ProductModel productModel);
}
