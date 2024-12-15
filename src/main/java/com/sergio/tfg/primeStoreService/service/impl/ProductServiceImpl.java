package com.sergio.tfg.primeStoreService.service.impl;

import com.sergio.tfg.primeStoreService.exception.CustomException;
import com.sergio.tfg.primeStoreService.model.ImageModel;
import com.sergio.tfg.primeStoreService.model.ProductModel;
import com.sergio.tfg.primeStoreService.model.UserModel;
import com.sergio.tfg.primeStoreService.model.dto.ProductModelDTO;
import com.sergio.tfg.primeStoreService.repository.ProductRepository;
import com.sergio.tfg.primeStoreService.repository.UserRepository;
import com.sergio.tfg.primeStoreService.service.ImageService;
import com.sergio.tfg.primeStoreService.service.ProductService;
import com.sergio.tfg.primeStoreService.util.Mapper;
import com.sergio.tfg.primeStoreService.util.SpanishDateFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;
  private final UserRepository userRepository;
  private final ImageService imageService;
  private final SpanishDateFormatter spanishDateFormatter;
  @Autowired
  private Mapper mapper;

  @Override
  public ProductModelDTO getProductById(Integer id) {
    ProductModel productModel = productRepository.findById(id).orElseThrow(() -> new CustomException("soy gay"));
    ProductModelDTO productModelDTO = mapper.toProductModelDTO(productModel);
    return mapper.toProductModelDTO(productRepository.findById(id).orElseThrow(() -> new CustomException("No se ha encontrado el producto para el id: " + id)));

  }

  @Override
  public List<ProductModelDTO> getAllOnSaleProductFromUserId(Integer userId) {
    return productRepository.findByUserId(userId).stream().map((productModel -> mapper.toProductModelDTO(productModel))).collect(Collectors.toList());
  }

  @Override
  public List<ProductModelDTO> getAllOnSaleProducts(Integer userId) {
    return productRepository.findByUserIdNot(userId).stream().map(productModel -> mapper.toProductModelDTO(productModel)).collect(Collectors.toList());
  }

  @Override
  public ProductModelDTO saveProduct(ProductModelDTO productModelDTO, Integer id) {
    productModelDTO.setDate(spanishDateFormatter.formate(LocalDate.now()));
    List<String> imageUrls = productModelDTO.getImages();
    UserModel userModel = userRepository.findById(id).orElse(null);

    if (userModel != null) {
      // 1. Convertimos el DTO a la entidad ProductModel
      ProductModel productModel = mapper.fromProductModelDTO(productModelDTO);
      productModel.setUser(userModel);

      // 2. Guardamos el producto sin las imágenes para obtener su ID
      ProductModel savedProduct = productRepository.save(productModel);

      // 3. Llamamos a imageService para añadir las imágenes y obtenemos la lista de imágenes guardadas
      List<ImageModel> savedImages = imageService.addImages(imageUrls, savedProduct);

      // 4. Actualizamos la colección de imágenes sin cambiar la referencia
      if (savedProduct.getImages() != null) {
        savedProduct.getImages().clear(); // Limpiamos la lista existente
        savedProduct.getImages().addAll(savedImages); // Añadimos las nuevas imágenes
      }

      // 5. Guardamos el producto con las imágenes asociadas
      savedProduct = productRepository.save(savedProduct);

      // 6. Devolvemos el DTO del producto guardado
      return mapper.toProductModelDTO(savedProduct);
    } else {
      throw new CustomException("Error al subir el producto, no se ha podido obtener los datos del usuario para el id: " + id);
    }
  }

  @Override
  public ProductModelDTO updateProduct(ProductModelDTO productModelDTO) {
    ProductModel existingProduct = productRepository.findById(productModelDTO.getId())
            .orElseThrow(() -> new CustomException("Producto no encontrado para el id: " + productModelDTO.getId()));

    existingProduct.setName(productModelDTO.getName());
    existingProduct.setDescription(productModelDTO.getDescription());
    existingProduct.setPrice(productModelDTO.getPrice());
    existingProduct.setCategory(productModelDTO.getCategory());
    List<String> updatedImageUrls = productModelDTO.getImages();

    // Verificar si las imágenes existentes ya están en el DTO
    List<ImageModel> currentImages = existingProduct.getImages();

    for (String updatedImageUrl : updatedImageUrls) {
      // Comprobar si la imagen ya existe
      boolean imageExists = currentImages.stream()
              .anyMatch(image -> image.getImage().equals(updatedImageUrl));

      if (!imageExists) {
        // Si la imagen no existe, crear un nuevo ImageModel
        ImageModel newImage = new ImageModel();
        newImage.setImage(updatedImageUrl);
        newImage.setProductModel(existingProduct); // Asignar el producto actual
        currentImages.add(newImage); // Añadir la nueva imagen a la lista
      }
    }

    // Eliminar imágenes que ya no están presentes en el DTO
    currentImages.removeIf(image -> !updatedImageUrls.contains(image.getImage()));

    // Guardar el producto con las imágenes actualizadas
    return mapper.toProductModelDTO(productRepository.save(existingProduct));
  }


  @Override
  public void deleteProductById(Integer productId){
    ProductModel productModel = productRepository.findById(productId).orElse(null);
    if(productModel!=null){
      productRepository.deleteById(productId);
    }else{
      System.out.println("No existe el producto para ese id");
    }

  }
}
