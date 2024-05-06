package com.businessAds.integration.dto;

import lombok.Data;

import java.util.concurrent.TimeUnit;

@Data
public class AccessToken {

    private String accessToken;

    public AccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
