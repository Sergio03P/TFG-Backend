package com.sergio.tfg.primeStoreService.repository;

import com.sergio.tfg.primeStoreService.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Integer> {
  List<ProductModel> findByUserIdNot(Integer userId);
  List<ProductModel> findByUserId(Integer userId);
  @Query("SELECT p FROM ProductModel p WHERE p IN (SELECT fp FROM UserModel u JOIN u.favouriteProductsModelList fp)")
  List<ProductModel> findAllFavouriteProducts();
}
