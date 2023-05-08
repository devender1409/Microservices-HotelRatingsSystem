package com.devender.hotel.service.impl;

import com.devender.hotel.entities.Hotel;
import com.devender.hotel.exception.ResourceNotFoundException;
import com.devender.hotel.repositories.HotelRepository;
import com.devender.hotel.service.HotelService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel create(Hotel hotel) {
        hotel.setId(UUID.randomUUID().toString());
        return this.hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return this.hotelRepository.findAll();
    }

    @Override
    public Hotel get(String id) {
        return this.hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel " ,id));
    }
}
