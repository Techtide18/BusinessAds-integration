package com.businessAds.integration.connectors;

import com.businessAds.integration.constants.BusinessAdsCommonConstants;
import com.businessAds.integration.dto.google.GoogleTokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

		ResponseEntity<GoogleTokenDTO> responseEntity = restTemplate.exchange(tokenUrl, HttpMethod.POST, entity, GoogleTokenDTO.class);

		return responseEntity != null ? responseEntity.getBody() : null;
	}

}
