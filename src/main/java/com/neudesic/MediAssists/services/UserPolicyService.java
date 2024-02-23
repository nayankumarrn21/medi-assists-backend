package com.neudesic.MediAssists.services;


import com.neudesic.MediAssists.dto.UserPolicyDto;
import com.neudesic.MediAssists.modules.UserPolicyDetails;
import com.neudesic.MediAssists.repositories.UserPolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPolicyService {


    @Autowired
    private UserPolicyRepository userPolicyRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PolicyService policyService;

    public UserPolicyDetails create(UserPolicyDto userPolicyDto){
        userPolicyDto.getUserPolicyDetails().setPolicy(policyService.getPolicy(userPolicyDto.getPolicyId()));
        userPolicyDto.getUserPolicyDetails().setUser(userService.getUserId(userPolicyDto.getUserId()));
        return userPolicyRepository.save(userPolicyDto.getUserPolicyDetails());
    }

    public List<UserPolicyDetails> getListOfUserDetails(Integer uid){
        return userPolicyRepository.findByUser_Id(uid);
    }



}
