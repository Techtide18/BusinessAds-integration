package com.businessAds.integration.auth.service;

import com.businessAds.integration.constants.BusinessAdsCommonConstants;
import com.businessAds.integration.dao.ClientInformationRepository;
import com.businessAds.integration.dto.AccessToken;
import com.businessAds.integration.dto.google.GoogleTokenDTO;
import com.businessAds.integration.pojo.ClientInformation;
import com.businessAds.integration.services.impl.AccessTokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Service
public class GoogleOAuthClient {

	@Value("${google.token.url}")
	private String tokenUrl;

	@Value("${google.client.id}")
	private String clientId;

	@Value("${google.client.secret}")
	private String clientSecret;

	@Autowired
	private ClientInformationRepository clientInformationRepository;

	@Autowired
	private AccessTokenService accessTokenService;

	public String getAccessToken(String email) {
		AccessToken accessTokenFromRedis = accessTokenService.getAccessTokenFromRedis(email);
		if (accessTokenFromRedis == null) {
			ClientInformation clientInfo = clientInformationRepository.findByEmail(email);
			GoogleTokenDTO accessTokenResponse = getAccessTokenResponseBody(clientInfo.getRefreshToken());
			String accessToken = accessTokenResponse.getAccessToken();
			if (StringUtils.isNotBlank(accessToken)) {
				return BusinessAdsCommonConstants.BEARER_PREFIX + accessToken;
			}
			return null;
		}
		return accessTokenFromRedis.getAccessToken();
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
