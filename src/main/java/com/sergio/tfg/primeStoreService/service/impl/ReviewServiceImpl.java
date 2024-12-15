package com.sergio.tfg.primeStoreService.service.impl;
import com.sergio.tfg.primeStoreService.model.dto.ReviewModelDTO;
import com.sergio.tfg.primeStoreService.repository.ReviewRepository;
import com.sergio.tfg.primeStoreService.service.ReviewService;
import com.sergio.tfg.primeStoreService.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
  private final ReviewRepository reviewRepository;
  private final Mapper mapper;
  @Override
  public List<ReviewModelDTO> getAllReviews() {
    return reviewRepository.findAll().stream()
            .map(review -> mapper.toReviewModelDTO(review)).collect(Collectors.toList());
  }
}
