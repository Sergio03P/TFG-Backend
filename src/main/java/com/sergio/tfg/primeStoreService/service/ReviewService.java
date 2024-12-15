package com.sergio.tfg.primeStoreService.service;

import com.sergio.tfg.primeStoreService.model.dto.ReviewModelDTO;

import java.util.List;

public interface ReviewService {
  List<ReviewModelDTO> getAllReviews();
}
