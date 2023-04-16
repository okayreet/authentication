package com.catalogue.authentication.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.catalogue.authentication.dto.RegisterRequest;

@FeignClient(url = "${registration.service.url}", value = "registration-feign-client")
public interface RegistrationFeign {

    @PostMapping(path = "/register")
    Long register(@RequestBody RegisterRequest registerRequest);

    @PostMapping(path = "/register2")
    String register2();
}
