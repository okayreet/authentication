package com.catalogue.authentication;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface LoginRepository extends JpaRepository<Login, Long>{

    Optional<Login> findLoginByEmail(String email);
    
}
