package com.sergio.tfg.primeStoreService.repository;

import com.sergio.tfg.primeStoreService.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ImageModel,Integer> {
  List<ImageModel> findByProductModelId(Integer productModelId);
}
