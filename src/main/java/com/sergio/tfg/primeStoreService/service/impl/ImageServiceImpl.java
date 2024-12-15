package com.sergio.tfg.primeStoreService.service.impl;

import com.sergio.tfg.primeStoreService.model.ImageModel;
import com.sergio.tfg.primeStoreService.model.ProductModel;
import com.sergio.tfg.primeStoreService.repository.ImageRepository;
import com.sergio.tfg.primeStoreService.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
  private final ImageRepository imageRepository;

  @Override
  public List<String> getAllImages(Integer id) {
    List<ImageModel> imageModelList = imageRepository.findByProductModelId(id);
    return imageModelList.stream().map(imageModel -> imageModel.getImage()).collect(Collectors.toList());
  }

  @Override
  public List<ImageModel> addImages(List<String> images, ProductModel productModel) {
    return images.stream()
            .map(image -> {
              ImageModel imageModel = new ImageModel();
              imageModel.setImage(image);
              imageModel.setProductModel(productModel);
              System.out.println(imageModel);
              return imageRepository.save(imageModel);
            })
            .collect(Collectors.toList());
  }
}
