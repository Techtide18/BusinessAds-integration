package com.businessAds.integration.connectors;

import com.businessAds.integration.constants.BusinessAdsCommonConstants;
import com.businessAds.integration.dto.google.GoogleTokenDTO;
import org.springframework.http.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GoogleApiConnector {

//	@Autowired
//	private RestTemplate restTemplate;

	//use @value here
	private String authUrl = "https://accounts.google.com/o/oauth2/v2/auth";

	//use @value here
	private String tokenUrl = "https://oauth2.googleapis.com/token";

	//use @value here
	private String clientId = "740815914417-o9du9pqif9ppj5e6t22vs6qhon9imeuf.apps.googleusercontent.com";

	//use @value here
	private String clientSecret = "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";

	//use @value here
	private String redirectUri = "http://localhost:8080/auth/google/callback";


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

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<GoogleTokenDTO> responseEntity = restTemplate.exchange(tokenUrl, HttpMethod.POST, entity, GoogleTokenDTO.class);

		return responseEntity != null ? responseEntity.getBody() : null;
	}

}
