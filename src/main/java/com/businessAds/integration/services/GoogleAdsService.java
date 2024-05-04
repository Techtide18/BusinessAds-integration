package com.businessAds.integration.services;

import com.businessAds.integration.connectors.GoogleApiConnector;
import com.businessAds.integration.constants.BusinessAdsCommonConstants;
import com.businessAds.integration.dao.ClientInformationRepository;
import com.businessAds.integration.dto.google.GoogleTokenDTO;
import com.businessAds.integration.pojo.ClientInformation;
import org.apache.commons.lang3.tuple.Pair;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class GoogleAdsService {

	@Autowired
	private ClientInformationRepository clientInformationRepository;

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

		GoogleTokenDTO response = googleApiConnector.exchangeAuthorizationCodeForTokens(authorizationCode);
		Pair<String, String> uniqueIdsAndEmailPair = decodeAndGetUniqueIdsFromJwt(response.getIdToken());
		//add in redis/aerospike -> what will be the uniqueId (email_accessToken_value)?
		storeRefreshTokenInDb(uniqueIdsAndEmailPair, response.getRefreshToken());
	}

	public Pair<String, String> decodeAndGetUniqueIdsFromJwt(String idToken) {

		try {
			JWT jwt = JWTParser.parse(idToken);
			String uniqueId = jwt.getJWTClaimsSet().getSubject();
			String userEmail = jwt.getJWTClaimsSet().getStringClaim(BusinessAdsCommonConstants.EMAIL_LITERAL);
			return Pair.of(uniqueId, userEmail);
		} catch (Exception e) {
			//logger.error("Failed to decode ID token: {}", e.getMessage(), e);
			return null;
		}
	}

	private void storeRefreshTokenInDb(Pair<String, String> uniqueIdsAndEmailPair, String refreshToken) {

		ClientInformation clientInfo = clientInformationRepository.findById(uniqueIdsAndEmailPair.getLeft())
				.orElse(new ClientInformation());

		clientInfo.setUniqueId(uniqueIdsAndEmailPair.getLeft());
		clientInfo.setEmail(uniqueIdsAndEmailPair.getRight());
		clientInfo.setRefreshToken(refreshToken);

		clientInformationRepository.save(clientInfo);
	}

}

