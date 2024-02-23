package com.neudesic.MediAssists.controllers;

import com.neudesic.MediAssists.dto.UserDto;
import com.neudesic.MediAssists.exceptions.ResourceNotFoundException;
import com.neudesic.MediAssists.jwt.JwtRequestFilter;
import com.neudesic.MediAssists.modules.User;
import com.neudesic.MediAssists.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @GetMapping("/list")
    public ResponseEntity<List<User>> allUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(userService.getUserId(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody User user){
        return ResponseEntity.ok(userService.updateUser(id, user));
    }



}
