package com.sergio.tfg.primeStoreService.controller.impl;

import com.sergio.tfg.primeStoreService.controller.FavouriteProductController;
import com.sergio.tfg.primeStoreService.model.dto.ProductModelDTO;
import com.sergio.tfg.primeStoreService.service.FavouriteProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class FavouriteProductControllerImpl implements FavouriteProductController {
  @Autowired
  private FavouriteProductService favouriteProductService;

  @Override
  @GetMapping("/getAllFavouriteProducts")
  public List<ProductModelDTO> getAllFavouriteProducts() {
    return favouriteProductService.getAllFavouriteProducts();
  }

  @Override
  @GetMapping("/getFavouriteUserProducts/{id}")
  public List<ProductModelDTO> getFavouriteUserProducts(@PathVariable Integer id) {
    return favouriteProductService.getFavouriteUserProducts(id);
  }

  @Override
  @PostMapping("/addFavouriteProduct/{userId},{productId}")
  public ProductModelDTO addFavouriteProduct(@PathVariable Integer userId, @PathVariable Integer productId) {
    return favouriteProductService.addFavouriteProduct(userId, productId);
  }

  @Override
  @DeleteMapping("/removeFavouriteProduct/{userId},{productId}")
  public ProductModelDTO removeFavouriteProduct(@PathVariable Integer userId, @PathVariable Integer productId) {
    return favouriteProductService.removeFavouriteProduct(userId, productId);
  }
}
