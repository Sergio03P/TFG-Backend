package com.sergio.tfg.primeStoreService.controller.impl;

import com.sergio.tfg.primeStoreService.controller.ImageController;
import com.sergio.tfg.primeStoreService.model.ImageModel;
import com.sergio.tfg.primeStoreService.model.ProductModel;
import com.sergio.tfg.primeStoreService.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ImageControllerImpl implements ImageController {
  private final ImageService imageService;
  @Override
  public List<String> getAllImages(Integer id) {
    return imageService.getAllImages(id);
  }

  @Override
  public List<ImageModel> addImages(List<String> images, ProductModel productModel) {
    return null;
  }
}
