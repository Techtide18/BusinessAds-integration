package com.businessAds.integration.dto.google;

import lombok.Data;

import java.util.List;

@Data
public class GoogleResultDTO {
    private List<Result> results;

    @Data
    public static class Result {
        private String resourceName;
    }
}
