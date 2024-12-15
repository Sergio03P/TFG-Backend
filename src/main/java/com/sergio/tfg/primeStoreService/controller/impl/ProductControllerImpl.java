package com.sergio.tfg.primeStoreService.controller.impl;

import com.sergio.tfg.primeStoreService.controller.ProductController;
import com.sergio.tfg.primeStoreService.model.ProductModel;
import com.sergio.tfg.primeStoreService.model.dto.ProductModelDTO;
import com.sergio.tfg.primeStoreService.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class ProductControllerImpl implements ProductController {
  private final ProductService productService;

  @Override
  @GetMapping("/getProductById/{id}")
  public ProductModelDTO getProductById(@PathVariable Integer id) {
    return productService.getProductById(id);
  }

  @Override
  @GetMapping("getAllProductFromUserId/{userId}")
  public List<ProductModelDTO> getAllOnSaleProductFromUserId(@PathVariable Integer userId) {
    return productService.getAllOnSaleProductFromUserId(userId);
  }

  /**
   * Obtiene todos los productos menos los del usuario autenticado
   * @param userId id del usuario autenticado
   * @return
   */
  @Override
  @GetMapping("/productsOnSale/{userId}")
  public List<ProductModelDTO> getAllOnSaleProducts(@PathVariable Integer userId) {
    List<ProductModelDTO> list = productService.getAllOnSaleProducts(userId);
    return list;
  }

  @Override
  @PostMapping("/saveProduct/{id}")
  public ProductModelDTO saveProduct(@RequestBody ProductModelDTO productDTO, @PathVariable Integer id) {
    ProductModelDTO productModelDTO = productService.saveProduct(productDTO, id);
    System.out.println("Desde el controlador" + productModelDTO);
    return productModelDTO;
  }

  @Override
  @PutMapping("updateProduct")
  public ProductModelDTO updateProduct(@RequestBody ProductModelDTO productModelDTO) {
    return productService.updateProduct(productModelDTO);
  }

  @Override
  @DeleteMapping("/deleteProduct/{productId}")
  public void deleteProductById(@PathVariable Integer productId) {
    productService.deleteProductById(productId);
  }
}
