package com.businessAds.integration.services;

import com.businessAds.integration.dto.AdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class GoogleAdsService {

	private final OAuth2AuthorizedClientService clientService;

	@Autowired
	public GoogleAdsService(OAuth2AuthorizedClientService clientService) {
		this.clientService = clientService;
	}

	public List<AdDto> getUserAds(String userId) {
		OAuth2AuthorizedClient client = clientService.loadAuthorizedClient("google", userId);
		String accessToken = client.getAccessToken().getTokenValue();

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

		ResponseEntity<AdDto[]> response = restTemplate.exchange(
				"https://googleapis.com/ads/v6/customers/{customerId}/ads",
				HttpMethod.GET, entity, AdDto[].class, userId);

		return Arrays.asList(response.getBody());
	}

}

