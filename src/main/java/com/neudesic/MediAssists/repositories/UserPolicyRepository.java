package com.neudesic.MediAssists.repositories;

import com.neudesic.MediAssists.modules.UserPolicyDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPolicyRepository extends JpaRepository<UserPolicyDetails, Integer> {
    List<UserPolicyDetails> findByUser_Id(Integer uid);
}
