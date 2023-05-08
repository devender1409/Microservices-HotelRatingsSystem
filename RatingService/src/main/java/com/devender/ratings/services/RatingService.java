package com.devender.ratings.services;

import com.devender.ratings.entities.Rating;

import java.util.List;

public interface RatingService {
    //create
    Rating create(Rating rating);

    //get all ratings
    List<Rating> getRatings();

    //get all by user id
    List<Rating> getRatingsByUserId(String userId);

    //get all by hotelId
    List<Rating> getRatingsByHotelId(String hotelId);
}
