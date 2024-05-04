package com.businessAds.integration.connectors;

import com.businessAds.integration.constants.BusinessAdsCommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GoogleApiConnector {

	@Autowired
	private RestTemplate restTemplate;

	//use @value here
	private String authUrl = "https://accounts.google.com/o/oauth2/v2/auth";

	//use @value here
	private String tokenUrl = "https://oauth2.googleapis.com/token";

	//use @value here
	private String clientId = "740815914417-o9du9pqif9ppj5e6t22vs6qhon9imeuf.apps.googleusercontent.com";

	//use @value here
	private String clientSecret = "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";

	//use @value here
	private String redirectUri = "@our.base.url@/auth/google/callback";

	//use @value here
	//included multiple scopes separated by spaces as google needs
	public static final String GOOGLE_ADS_API_SCOPE = "https://www.googleapis.com/auth/adwords openid https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email";


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

	public String exchangeAuthorizationCodeForTokens(String authorizationCode) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add(BusinessAdsCommonConstants.CODE_LITERAL, authorizationCode);
		map.add(BusinessAdsCommonConstants.CLIENT_ID, clientId);
		map.add(BusinessAdsCommonConstants.CLIENT_SECRET, clientSecret);
		map.add(BusinessAdsCommonConstants.REDIRECT_URI, redirectUri);
		map.add(BusinessAdsCommonConstants.GRANT_TYPE, BusinessAdsCommonConstants.AUTHORIZATION_CODE);

		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(tokenUrl, HttpMethod.GET, entity, String.class);

		return responseEntity != null ? responseEntity.getBody() : null;
	}

	//	public MindbodyClassResponse fetchClasses(String startDate, String endDate, BusinessIntegration businessIntegration,
//			String token) {
//		HttpEntity<?> httpEntity = new HttpEntity<>(getHttpHeaders(businessIntegration, token));
//		String classesURI = getClassUrl(businessIntegration, startDate, endDate);
//		logger.info("URI to fetchClasses details for businessNumber {} is {}", businessIntegration.getBusinessNumber(),
//				classesURI);
//		ResponseEntity<MindbodyClassResponse> responseEntity = restTemplate.exchange(classesURI, HttpMethod.GET,
//				httpEntity, MindbodyClassResponse.class);
//		return responseEntity != null ? responseEntity.getBody() : null;
//	}


}
