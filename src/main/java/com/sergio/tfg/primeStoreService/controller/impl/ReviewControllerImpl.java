package com.sergio.tfg.primeStoreService.controller.impl;

import com.sergio.tfg.primeStoreService.controller.ReviewController;
import com.sergio.tfg.primeStoreService.model.dto.ReviewModelDTO;
import com.sergio.tfg.primeStoreService.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class ReviewControllerImpl implements ReviewController {
  private final ReviewService reviewService;
  @Override
  @GetMapping("/getAllReviews")
  public List<ReviewModelDTO> getAllReviews() {
    return reviewService.getAllReviews();
  }
}
