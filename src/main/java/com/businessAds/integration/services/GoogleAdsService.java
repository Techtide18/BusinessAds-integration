package com.businessAds.integration.services;

import com.businessAds.integration.connectors.GoogleApiConnector;
import com.businessAds.integration.constants.BusinessAdsCommonConstants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GoogleAdsService {

	@Autowired
	GoogleApiConnector googleApiConnector;

	/**
	 * ModelAndView is used to redirect the user's browser to Google's OAuth 2.0 server
	 */
	public ModelAndView redirectUserToAuthenticationUrl() {

		String authenticationUrl = googleApiConnector.getAuthenticationUrl();
		return new ModelAndView(BusinessAdsCommonConstants.REDIRECT_LITERAL + authenticationUrl);
	}

	public void exchangeAuthorizationCodeForTokensAndSaveUserRefreshTokenInDB(String authorizationCode) {

		String response = googleApiConnector.exchangeAuthorizationCodeForTokens(authorizationCode);
		List<String> uniqueIdAndRefreshTokenList = parseResponseAndDecodeIDToken(response);
		storeRefreshToken(uniqueIdAndRefreshTokenList);
	}

	private List<String> parseResponseAndDecodeIDToken(String responseBody) {

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, Object> tokenResponse = objectMapper.readValue(responseBody,
					new TypeReference<Map<String, Object>>() {
					});

			String idToken = (String) tokenResponse.get("id_token");
			String refreshToken = (String) tokenResponse.get("refresh_token");
			return decodeIdToken(idToken, refreshToken);
		} catch (Exception e) {
			e.printStackTrace();
			logger return new ArrayList<>();
		}
	}

	private List<String> decodeIdToken(String idToken, String refreshToken) {

		try {
			List<String> uniqueIdAndRefreshToken = new ArrayList<>();
			JWT jwt = JWTParser.parse(idToken);
			String uniqueId = jwt.getJWTClaimsSet().getSubject();
			String userEmail = jwt.getJWTClaimsSet().getStringClaim("email");

			uniqueIdAndRefreshToken.add(uniqueId);
			uniqueIdAndRefreshToken.add(userEmail);
			uniqueIdAndRefreshToken.add(refreshToken);

			return uniqueIdAndRefreshToken;
		} catch (Exception e) {
			e.printStackTrace();
			logger return new ArrayList<>();
		}
	}

	private void storeRefreshToken(List<String> uniqueIdsAndRefreshToken) {

		//0-> uniqueId, 1-> email, 3-> refreshToken
		db.save(uniqueIdsAndRefreshToken.get(0), uniqueIdsAndRefreshToken.get(1), uniqueIdsAndRefreshToken.get(2));
		//update email and refresh token if auth again for the same user (as email can also change)
		//UPDATE table t SET t.email = :email, t.refreshToken = :refreshToken WHERE t.uniqueId = :uniqueId
		//findById().orElse(new User())
	}

}

