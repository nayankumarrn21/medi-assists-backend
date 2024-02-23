package com.neudesic.MediAssists.repositories;


import com.neudesic.MediAssists.modules.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer> { }
