package com.devender.user.service.entities.controller;

import com.devender.user.service.entities.User;
import com.devender.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    //create

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = this.userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    //singleuser get
    @GetMapping(value = "/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        User user = this.userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    //all user get
    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = this.userService.getAllUser();
        return ResponseEntity.ok(users);
    }
}
