package com.sergio.tfg.primeStoreService.controller;

import com.sergio.tfg.primeStoreService.model.dto.ReviewModelDTO;

import java.util.List;

public interface ReviewController {
  List<ReviewModelDTO> getAllReviews();
}
