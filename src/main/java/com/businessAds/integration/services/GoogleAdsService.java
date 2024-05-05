package com.businessAds.integration.services;

import com.businessAds.integration.auth.service.GoogleOAuthClient;
import com.businessAds.integration.connectors.GoogleApiConnector;
import com.businessAds.integration.constants.BusinessAdsCommonConstants;
import com.businessAds.integration.dao.ClientInformationRepository;
import com.businessAds.integration.dto.google.GoogleTokenDTO;
import com.businessAds.integration.pojo.ClientInformation;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class GoogleAdsService {

	@Autowired
	private ClientInformationRepository clientInformationRepository;

	@Autowired
	GoogleApiConnector googleApiConnector;

	@Autowired
	GoogleOAuthClient googleOAuthClient;

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
		String accessToken = getAccessToken(uniqueIdsAndEmailPair.getLeft());
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

		ClientInformation clientInfo = clientInformationRepository.findByUniqueId(uniqueIdsAndEmailPair.getLeft());
		if (ObjectUtils.isEmpty(clientInfo)) {
			clientInfo = new ClientInformation(uniqueIdsAndEmailPair.getLeft(), uniqueIdsAndEmailPair.getRight(),
					refreshToken);
		}

		clientInformationRepository.save(clientInfo);
	}

	//test method
	public String getAccessToken(String email) {
		//if reddis dosnt contain the token then hit api or retuen token from here
		return googleOAuthClient.getAccessToken(email);
	}

}

