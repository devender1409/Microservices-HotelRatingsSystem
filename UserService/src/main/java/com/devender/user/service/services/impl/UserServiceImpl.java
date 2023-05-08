package com.devender.user.service.services.impl;

import com.devender.user.service.entities.Hotel;
import com.devender.user.service.entities.Rating;
import com.devender.user.service.entities.User;
import com.devender.user.service.exceptions.ResourceNotFoundException;
import com.devender.user.service.repositories.UserRepository;
import com.devender.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return this.userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {

        List<User> users = this.userRepository.findAll();
        List<User> usersListWithRatingsAttached = new ArrayList<>();
        for(User user : users){
            ArrayList ratingOfUser = restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(), ArrayList.class);
//        logger.info("{}",ratingOfUser);
            user.setRatings(ratingOfUser);
            usersListWithRatingsAttached.add(user);
        }
        return usersListWithRatingsAttached;
    }

    @Override
    public User getUser(String userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User",userId));

        //fetch rating of the above user from RATING-SERVICE
        //localhost:8083/ratings/users/c25082ba-bc4d-4b24-8f9c-5ded23189bf1

        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+userId, Rating[].class);
//        logger.info("{}",ratingOfUser);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingListWithHotels = ratings.stream().map(rating->{
//            //api call to hotel service to get the hotel
////            localhost:8082/hotels/be534350-606c-431f-a06e-768b81ab2a64
            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();
//            //set the hotel to rating
            rating.setHotel(hotel);
//            //return the rating
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingListWithHotels);


        return user;
    }
}
