package com.devender.ratings.services.impl;

import com.devender.ratings.entities.Rating;
import com.devender.ratings.repositories.RatingRepository;
import com.devender.ratings.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating create(Rating rating) {
        return this.ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getRatings() {
        return this.ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingsByUserId(String userId) {
        return this.ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingsByHotelId(String hotelId) {
        return this.ratingRepository.findByHotelId(hotelId);
    }
}
