package com.businessAds.integration.auth.service;

import com.businessAds.integration.constants.BusinessAdsCommonConstants;
import com.businessAds.integration.dto.google.GoogleTokenDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleOAuthClient {

	//use @value here
	private String tokenUrl = "https://oauth2.googleapis.com/token";

	//use @value here
	private String clientId = "740815914417-o9du9pqif9ppj5e6t22vs6qhon9imeuf.apps.googleusercontent.com";

	//use @value here
	private String clientSecret = "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";

	public String getAccessToken(String refreshToken) {
		GoogleTokenDTO accessTokenResponse = getAccessTokenResponseBody(refreshToken);
		String accessToken = accessTokenResponse.getAccessToken();
		if (StringUtils.isNotBlank(accessToken)) {
			return BusinessAdsCommonConstants.BEARER_PREFIX + accessToken;
		}
		return null;
	}

	private GoogleTokenDTO getAccessTokenResponseBody(String refreshToken) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add(BusinessAdsCommonConstants.CLIENT_ID, clientId);
		map.add(BusinessAdsCommonConstants.CLIENT_SECRET, clientSecret);
		map.add(BusinessAdsCommonConstants.REFRESH_TOKEN, refreshToken);
		map.add(BusinessAdsCommonConstants.GRANT_TYPE, BusinessAdsCommonConstants.REFRESH_TOKEN);
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<GoogleTokenDTO> responseEntity = restTemplate.exchange(tokenUrl, HttpMethod.POST, entity,
				GoogleTokenDTO.class);

		return responseEntity != null ? responseEntity.getBody() : null;
	}

}