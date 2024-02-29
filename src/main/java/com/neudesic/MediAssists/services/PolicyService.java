package com.neudesic.MediAssists.services;


import com.neudesic.MediAssists.modules.Policy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class PolicyService {

//    @Autowired
//    private PolicyRepository policyRepository;

    @Value("${micros1.url}")
    private String micros1Url;

    @Autowired
    private  WebClient.Builder webClient;



    public Policy createPolicy(Policy policy){
        return this.webClient.baseUrl(micros1Url).build()
                .post().uri("policy")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(policy))
                .retrieve()
                .bodyToMono(Policy.class).block();
    }

    public List<Policy> getAll(){
        return this.webClient.baseUrl(micros1Url).build()
                .get()
                .uri("policy/list")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Policy>>() {}).block();
    }
    public Policy getPolicy(Integer pId){
            return this.webClient.baseUrl(micros1Url).build()
                    .get()
                    .uri("policy/"+pId)
                    .retrieve()
                    .bodyToMono(Policy.class).block();
    }

    public Policy deletePolicy(Integer policyId){
        Policy policy = getPolicy(policyId);
        if(policy!=null){
            return this.webClient.baseUrl(micros1Url).build()
                    .delete()
                    .uri("policy/"+policyId)
                    .retrieve()
                    .bodyToMono(Policy.class).block();
        }
        return null;
    }

}
