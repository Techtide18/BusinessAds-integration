package com.businessAds.integration.services.impl;

import com.businessAds.integration.auth.service.GoogleOAuthClient;
import com.businessAds.integration.connectors.GoogleApiConnector;
import com.businessAds.integration.constants.BusinessAdsCommonConstants;
import com.businessAds.integration.dao.ClientInformationRepository;
import com.businessAds.integration.dto.google.*;
import com.businessAds.integration.pojo.ClientInformation;
import com.businessAds.integration.utils.GoogleUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.tuple.Pair;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class GoogleAdsService {

	@Autowired
	ClientInformationRepository clientInformationRepository;

	@Autowired
	GoogleApiConnector googleApiConnector;

	@Autowired
	GoogleOAuthClient googleOAuthClient;

	@Autowired
	AccessTokenService accessTokenService;

	Logger logger = LoggerFactory.getLogger(GoogleAdsService.class);

	private String getAccessToken(String email) {
		return googleOAuthClient.getAccessToken(email);
	}

	public ModelAndView redirectUserToAuthenticationUrl() {

		String authenticationUrl = googleApiConnector.getAuthenticationUrl();
		return new ModelAndView(BusinessAdsCommonConstants.REDIRECT_LITERAL + authenticationUrl);
	}

	public void exchangeAuthorizationCodeForTokensAndSaveUserRefreshTokenInDB(String authorizationCode) {

		GoogleTokenDTO response = googleApiConnector.exchangeAuthorizationCodeForTokens(authorizationCode);
		Pair<String, String> uniqueIdAndEmailPair = decodeAndGetUniqueIdFromJwt(response.getIdToken());

		String redisKey = uniqueIdAndEmailPair.getRight() + BusinessAdsCommonConstants.GOOGLE;
		accessTokenService.setAccessTokenInRedis(redisKey, response.getAccessToken(), response.getExpiresIn(),
				TimeUnit.SECONDS);

		storeRefreshTokenInDb(uniqueIdAndEmailPair, response.getRefreshToken());
	}

	public Pair<String, String> decodeAndGetUniqueIdFromJwt(String idToken) {

		try {
			JWT jwt = JWTParser.parse(idToken);
			String uniqueId = jwt.getJWTClaimsSet().getSubject();
			String userEmail = jwt.getJWTClaimsSet().getStringClaim(BusinessAdsCommonConstants.EMAIL_LITERAL);
			return Pair.of(uniqueId, userEmail);
		} catch (Exception e) {
			String exceptionReason = "Failed to decode ID token, e: " + e.getMessage();
			logger.error(exceptionReason, e);
			throw new RuntimeException(exceptionReason);
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

	public ResourceNamesDTO getAdAccounts(String email) {
		String accessToken = getAccessToken(email);
		ResourceNamesDTO adAccounts = googleApiConnector.getAdAccounts(accessToken);
		return adAccounts;
	}

	public GoogleResultDTO createBudget(String email, String customerId, BudgetDTO budgetDTO) {
		String accessToken = getAccessToken(email);
		GoogleBaseDTO<CreateOperationDTO<?>> googleBaseDTO = GoogleUtils.getSingleGoogleCreateOperation(budgetDTO);
		GoogleResultDTO resultDTO = googleApiConnector.createBudget(accessToken, customerId, googleBaseDTO);
		// save all results id in DB with respect to customer id
		return resultDTO;
	}

	public String createCampaign(String email, String customerId, String budgetId) {
		String accessToken = getAccessToken(email);
		BiddingStrategyConfigurationDTO biddingStrategyConfig = new BiddingStrategyConfigurationDTO("MANUAL_CPC");
		CampaignPayloadDTO campaignPayloadDTO = new CampaignPayloadDTO("Test Campaign", "SEARCH", "PAUSED", budgetId,
				biddingStrategyConfig);
		String campaign = googleApiConnector.createCampaign(accessToken, customerId, campaignPayloadDTO);
		return campaign;
	}

	public String createAdGroup(String email, String customerId, String campaignId) {
		String accessToken = getAccessToken(email);
		AdGroupPayloadDTO adGroupPayloadDTO = new AdGroupPayloadDTO("Test Ad Group", campaignId, "ENABLED",
				"SEARCH_STANDARD", 1000000L);
		String adGroup = googleApiConnector.createAdGroup(accessToken, customerId, adGroupPayloadDTO);
		return adGroup;
	}

	public String createAd(String email, String adType, String customerId, String adGroupId, String title,
			String description, String imageUrl, String finalUrl, String videoId, String productId, String businessName,
			String callToAction, String phoneNumber, String countryCode, String appId, String appStore) {

		String accessToken = getAccessToken(email);
		AdPayloadDTO adPayload = GoogleUtils.constructAdPayload(adType, adGroupId, title, description, imageUrl,
				finalUrl, videoId, productId, businessName, callToAction, phoneNumber, countryCode, appId, appStore);
		String ad = googleApiConnector.createAd(accessToken, customerId, adPayload);
		return ad;
	}

}

