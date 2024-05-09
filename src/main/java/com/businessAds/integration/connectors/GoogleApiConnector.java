package com.businessAds.integration.connectors;

import com.businessAds.integration.constants.BusinessAdsCommonConstants;
import com.businessAds.integration.dto.google.*;
import com.businessAds.integration.utils.GoogleUtils;
import com.google.auth.oauth2.GoogleAuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Service
public class GoogleApiConnector {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${google.auth.url}")
	private String authUrl;

	@Value("${google.token.url}")
	private String tokenUrl;

	@Value("${google.client.id}")
	private String clientId;

	@Value("${google.client.secret}")
	private String clientSecret;

	@Value("${google.redirect.uri}")
	private String redirectUri;

	@Value("${google.auth.scope}")
	private String GOOGLE_ADS_API_SCOPE;

	@Value("${google.customers.url}")
	private String customersUri;

	@Value("${google.campaign.budget.url}")
	private String campaignBudgetUri;

	@Value("${google.campaign.url}")
	private String campaignUri;

	@Value("${google.adGroup.url}")
	private String adGroupUri;

	@Value("${google.ads.url}")
	private String adsUri;

	@Value("${google.developer.token}")
	private String GOOGLE_DEV_TOKEN;

	public String getAuthenticationUrl() {

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(authUrl)
				.queryParam(BusinessAdsCommonConstants.RESPONSE_TYPE, "code")
				.queryParam(BusinessAdsCommonConstants.CLIENT_ID, clientId)
				.queryParam(BusinessAdsCommonConstants.REDIRECT_URI, redirectUri)
				.queryParam(BusinessAdsCommonConstants.SCOPE, GOOGLE_ADS_API_SCOPE)
				.queryParam(BusinessAdsCommonConstants.ACCESS_TYPE, "offline")
				.queryParam(BusinessAdsCommonConstants.PROMPT, "consent");

		return builder.toUriString();
	}

	public GoogleTokenDTO exchangeAuthorizationCodeForTokens(String authorizationCode) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add(BusinessAdsCommonConstants.CODE_LITERAL, authorizationCode);
		map.add(BusinessAdsCommonConstants.CLIENT_ID, clientId);
		map.add(BusinessAdsCommonConstants.CLIENT_SECRET, clientSecret);
		map.add(BusinessAdsCommonConstants.REDIRECT_URI, redirectUri);
		map.add(BusinessAdsCommonConstants.GRANT_TYPE, BusinessAdsCommonConstants.AUTHORIZATION_CODE);
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

		ResponseEntity<GoogleTokenDTO> responseEntity = restTemplate.exchange(tokenUrl, HttpMethod.POST, entity,
				GoogleTokenDTO.class);
		return responseEntity != null ? responseEntity.getBody() : null;
	}

	public ResourceNamesDTO getAdAccounts(String accessToken) {

		String url = customersUri + ":listAccessibleCustomers";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add(BusinessAdsCommonConstants.AUTHORIZATION, accessToken);
		headers.add(BusinessAdsCommonConstants.DEVELOPER_TOKEN, GOOGLE_DEV_TOKEN);
		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<ResourceNamesDTO> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity,
				ResourceNamesDTO.class);
		return responseEntity != null ? responseEntity.getBody() : null;

	}

	public String createBudget(String accessToken, String customerId, BudgetPayloadDTO budgetPayloadDTO) {

		String url = campaignBudgetUri.replace(BusinessAdsCommonConstants.CUSTOMER_ID, customerId);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add(BusinessAdsCommonConstants.AUTHORIZATION, accessToken);
		HttpEntity<?> httpEntity = new HttpEntity<>(budgetPayloadDTO, headers);

		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
		return responseEntity != null ? responseEntity.getBody() : null;
	}

	public String createCampaign(String accessToken, String customerId, CampaignPayloadDTO campaignPayloadDTO) {

		String url = campaignUri.replace(BusinessAdsCommonConstants.CUSTOMER_ID, customerId);
		HttpHeaders headers = new HttpHeaders();
		headers.add(BusinessAdsCommonConstants.AUTHORIZATION, accessToken);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<?> entity = new HttpEntity<>(campaignPayloadDTO, headers);

		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		return responseEntity != null ? responseEntity.getBody() : null;
	}

	public String createAdGroup(String accessToken, String customerId, AdGroupPayloadDTO adGroupPayloadDTO) {

		String url = adGroupUri.replace(BusinessAdsCommonConstants.CUSTOMER_ID, customerId);
		HttpHeaders headers = new HttpHeaders();
		headers.add(BusinessAdsCommonConstants.AUTHORIZATION, accessToken);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<?> entity = new HttpEntity<>(adGroupPayloadDTO, headers);

		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		return responseEntity != null ? responseEntity.getBody() : null;
	}

	public String createAd(String accessToken, String customerId, AdPayloadDTO adPayload) {

		String url = adsUri.replace(BusinessAdsCommonConstants.CUSTOMER_ID, customerId);
		HttpHeaders headers = new HttpHeaders();
		headers.add(BusinessAdsCommonConstants.AUTHORIZATION, accessToken);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<?> entity = new HttpEntity<>(adPayload, headers);

		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		return responseEntity != null ? responseEntity.getBody() : null;
	}

}
