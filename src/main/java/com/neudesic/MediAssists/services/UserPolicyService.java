package com.neudesic.MediAssists.services;


import com.neudesic.MediAssists.dto.UserPolicyDto;
import com.neudesic.MediAssists.exceptions.ResourceNotFoundException;
import com.neudesic.MediAssists.modules.Policy;
import com.neudesic.MediAssists.modules.User;
import com.neudesic.MediAssists.modules.UserPolicyDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class UserPolicyService {



    @Autowired
    private WebClient.Builder webclient;

    @Value("${micros1.url}")
    private String microS1Url;

    @Autowired
    private UserService userService;

    @Autowired
    private PolicyService policyService;

    public UserPolicyDetails create(UserPolicyDto userPolicyDto){
        Policy policy = policyService.getPolicy(userPolicyDto.getPolicyId());
        User user =  userService.getUserId(userPolicyDto.getUserId());
        if(policy !=null && user !=null){
            userPolicyDto.getUserPolicyDetails().setUserId(user.getid());
            userPolicyDto.getUserPolicyDetails().setPolicy(policy);
            return webclient.baseUrl(microS1Url).build()
                    .post()
                    .uri("user-policy")
                    .body(BodyInserters.fromValue(userPolicyDto))
                    .retrieve()
                    .bodyToMono(UserPolicyDetails.class)
                    .block();
        }
        throw new ResourceNotFoundException("User or Policy","userId || policy Id", userPolicyDto);
    }

    public List<UserPolicyDetails> getListOfUserDetails(Integer uid){
        return  webclient.baseUrl(microS1Url).build()
                .get()
                .uri("user-policy/user/"+uid)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<UserPolicyDetails>>() {}).block();
    }



}
