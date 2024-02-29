package com.neudesic.MediAssists.controllers;


import com.neudesic.MediAssists.dto.UserPolicyDto;
import com.neudesic.MediAssists.modules.UserPolicyDetails;
import com.neudesic.MediAssists.services.UserPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-policy")
public class UserPolicyController {

    @Autowired
    private UserPolicyService userPolicyService;

    @PostMapping
    public ResponseEntity<UserPolicyDetails> create(@RequestBody UserPolicyDto userPolicyDto){
        return ResponseEntity.ok(userPolicyService.create(userPolicyDto));
    }

    @GetMapping("/user/{uId}")
    public ResponseEntity<List<UserPolicyDetails>> getListOfUserDetails(@PathVariable("uId") Integer uId ){
        return ResponseEntity.ok(userPolicyService.getListOfUserDetails(uId));
    }
}
