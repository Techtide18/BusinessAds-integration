package com.businessAds.integration.controller;

import com.businessAds.integration.repositories.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class dummyController {

    @Autowired
    TokenRepository tokenRepository;


}
