package com.devender.hotel.service;

import com.devender.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {
    //create
    Hotel create(Hotel hotel);
    List<Hotel> getAllHotels();

    Hotel get(String id);
}
