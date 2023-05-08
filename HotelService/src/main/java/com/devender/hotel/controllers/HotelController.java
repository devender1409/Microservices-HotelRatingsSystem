package com.devender.hotel.controllers;

import com.devender.hotel.entities.Hotel;
import com.devender.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.hotelService.create(hotel));
    }

    @GetMapping(value = "/{hotelId}")
    public  ResponseEntity<Hotel> getHotel(@PathVariable String hotelId){
        return ResponseEntity.status(HttpStatus.OK).body(this.hotelService.get(hotelId));
    }


    @GetMapping
    public  ResponseEntity<List<Hotel>> getHotel(){
        return ResponseEntity.status(HttpStatus.OK).body(this.hotelService.getAllHotels());
    }
}
