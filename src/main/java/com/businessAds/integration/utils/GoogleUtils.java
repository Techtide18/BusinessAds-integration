package com.businessAds.integration.utils;

import com.businessAds.integration.constants.BusinessAdsCommonConstants;
import com.businessAds.integration.dto.google.AdPayloadDTO;
import com.businessAds.integration.dto.google.BudgetDTO;
import com.businessAds.integration.dto.google.CreateOperationDTO;
import com.businessAds.integration.dto.google.GoogleBaseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class GoogleUtils {

	public static AdPayloadDTO constructAdPayload(String adType, String adGroupId, String title, String description,
			String imageUrl, String finalUrl, String videoId, String productId, String businessName,
			String callToAction, String phoneNumber, String countryCode, String appId, String appStore) {

		AdPayloadDTO adPayload = new AdPayloadDTO();
		adPayload.setAdGroup(adGroupId);
		AdPayloadDTO.AdDTO adDTO = new AdPayloadDTO.AdDTO();

		switch (adType.toLowerCase()) {
		case "search":
			AdPayloadDTO.ExpandedTextAdDTO searchAd = new AdPayloadDTO.ExpandedTextAdDTO();
			searchAd.setHeadlinePart1(title);
			searchAd.setDescription(description);
			searchAd.setFinalUrls(Collections.singletonList(finalUrl));
			adDTO.setExpandedTextAd(searchAd);
			break;
		case "display":
			AdPayloadDTO.ResponsiveDisplayAdDTO displayAd = new AdPayloadDTO.ResponsiveDisplayAdDTO();
			displayAd.setHeadlines(Collections.singletonList(new AdPayloadDTO.HeadlineDTO(title)));
			displayAd.setMarketingImages(Collections.singletonList(new AdPayloadDTO.MarketingImageDTO(imageUrl)));
			displayAd.setFinalUrls(Collections.singletonList(finalUrl));
			displayAd.setBusinessName(businessName);
			displayAd.setCallToActionText(callToAction);
			displayAd.setPhoneNumber(new AdPayloadDTO.PhoneNumberDTO(countryCode, phoneNumber));
			adDTO.setResponsiveDisplayAd(displayAd);
			break;
		case "video":
			AdPayloadDTO.VideoAdDTO videoAd = new AdPayloadDTO.VideoAdDTO();
			videoAd.setVideo(new AdPayloadDTO.VideoDTO(videoId));
			videoAd.setFinalUrls(Collections.singletonList(finalUrl));
			adDTO.setVideoAd(videoAd);
			break;
		case "shopping":
			AdPayloadDTO.ProductAdDTO shoppingAd = new AdPayloadDTO.ProductAdDTO();
			shoppingAd.setProductChannel("ONLINE");
			shoppingAd.setProductId(productId);
			shoppingAd.setFinalUrls(Collections.singletonList(finalUrl));
			adDTO.setProductAd(shoppingAd);
			break;
		case "responsive":
			AdPayloadDTO.ResponsiveSearchAdDTO responsiveAd = new AdPayloadDTO.ResponsiveSearchAdDTO();
			responsiveAd.setHeadlines(Collections.singletonList(new AdPayloadDTO.HeadlineDTO(title)));
			responsiveAd.setDescriptions(Collections.singletonList(new AdPayloadDTO.DescriptionDTO(description)));
			responsiveAd.setFinalUrls(Collections.singletonList(finalUrl));
			adDTO.setResponsiveSearchAd(responsiveAd);
			break;
		case "call-only":
			AdPayloadDTO.CallOnlyAdDTO callOnlyAd = new AdPayloadDTO.CallOnlyAdDTO();
			callOnlyAd.setBusinessName(businessName);
			callOnlyAd.setPhoneNumber(phoneNumber);
			callOnlyAd.setCountryCode(countryCode);
			callOnlyAd.setHeadline1(title);
			callOnlyAd.setDescription1(description);
			adDTO.setCallOnlyAd(callOnlyAd);
			break;
		case "app-promotion":
			AdPayloadDTO.AppAdDTO appPromotionAd = new AdPayloadDTO.AppAdDTO();
			appPromotionAd.setAppId(appId);
			appPromotionAd.setAppStore(appStore);
			appPromotionAd.setHeadlines(Collections.singletonList(new AdPayloadDTO.HeadlineDTO(title)));
			appPromotionAd.setDescriptions(Collections.singletonList(new AdPayloadDTO.DescriptionDTO(description)));
			appPromotionAd.setFinalUrls(Collections.singletonList(finalUrl));
			adDTO.setAppAd(appPromotionAd);
			break;
		default:
			throw new IllegalArgumentException("Invalid ad type: " + adType);
		}

		adPayload.setAd(adDTO);
		return adPayload;
	}
	//Search Ads: Text ads that appear on Google search results pages.
	//Display Ads: Image or video ads that appear on websites in the Google Display Network.
	//Video Ads: Ads that appear on YouTube and other video partner sites.
	//Shopping Ads: Product ads that appear in Google Shopping and search results.
	//Responsive Ads: Ads that automatically adjust their size, appearance, and format to fit available ad spaces.
	//Call-Only Ads: Ads designed to encourage phone calls to your business.
	//App Promotion Ads: Ads that promote app installations and engagement.

	//5216290242 USDnI2NhsnWvQFezdmUe_g
	public static HttpHeaders getGoogleHeaders(String accessToken, String loginCustomerId, String developerToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add(BusinessAdsCommonConstants.AUTHORIZATION, accessToken);
		headers.add(BusinessAdsCommonConstants.LOGIN_CUSTOMER_ID, loginCustomerId);
		headers.add(BusinessAdsCommonConstants.DEVELOPER_TOKEN, developerToken);
		return headers;
	}

	public static GoogleBaseDTO<CreateOperationDTO<?>> getSingleGoogleCreateOperation(Object object) {
		CreateOperationDTO<?> createOperation = new CreateOperationDTO<>(object);
		return new GoogleBaseDTO<>(List.of(createOperation));
	}

}


