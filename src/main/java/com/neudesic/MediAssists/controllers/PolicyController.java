package com.neudesic.MediAssists.controllers;

import com.neudesic.MediAssists.exceptions.BindingResultException;
import com.neudesic.MediAssists.modules.Policy;
import com.neudesic.MediAssists.services.PolicyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/policy")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    @PostMapping
    public ResponseEntity<Policy> createPolicy(@Valid @RequestBody Policy policy, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BindingResultException("Policy Field errors", bindingResult);
        }
        return ResponseEntity.ok(policyService.createPolicy(policy));
    }

    @GetMapping("list")
    public ResponseEntity<List<Policy>> getAll(){
        return ResponseEntity.ok(policyService.getAll());
    }

    @DeleteMapping("{pId}")
    public ResponseEntity<Policy> deletePolicy(@PathVariable("pId") Integer pid){
        return ResponseEntity.ok(policyService.deletePolicy(pid));
    }
}
