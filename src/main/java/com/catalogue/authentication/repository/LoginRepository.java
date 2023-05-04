package com.catalogue.authentication.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catalogue.authentication.entity.Login;


public interface LoginRepository extends JpaRepository<Login, Long>{

    Optional<Login> findByEmail(String email);

    Optional<Login> findByUserId(Long user_id);
    
}
