package com.neudesic.MediAssists.controllers;


import com.neudesic.MediAssists.dto.AuthenticationRequest;
import com.neudesic.MediAssists.dto.AuthenticationResponse;
import com.neudesic.MediAssists.dto.UserDto;
import com.neudesic.MediAssists.exceptions.BindingResultException;
import com.neudesic.MediAssists.modules.User;
import com.neudesic.MediAssists.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BindingResultException("Field Validation Errors", bindingResult);
        }
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDto userDto,  BindingResult bindingResult) throws BindingResultException{
        if(bindingResult.hasErrors()){
            throw new BindingResultException("Field Validation Errors", bindingResult);
        }
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @GetMapping("/test")
    public String test(){
        return "Iam a Developer";
    }

}
