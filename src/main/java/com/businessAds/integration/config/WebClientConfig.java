package com.businessAds.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}

/*
@Autowired
    private WebClient.Builder webClientBuilder;
    String url = "https://api.example.com/data";
MyResponseObject response = webClientBuilder.build()
        .get()
        .uri(url)
        .retrieve()
        .bodyToMono(MyResponseObject.class)
        .block();

 */