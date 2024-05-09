package com.businessAds.integration.utils;

import com.businessAds.integration.dto.google.AdPayloadDTO;

import java.util.Collections;

public class GoogleUtils {

	public static AdPayloadDTO constructAdPayload(String adGroupId, String title, String imageUrl, String finalUrl, String phoneNumber) {
		AdPayloadDTO.HeadlineDTO headline = new AdPayloadDTO.HeadlineDTO(title);
		AdPayloadDTO.MarketingImageDTO marketingImage = new AdPayloadDTO.MarketingImageDTO(imageUrl);
		AdPayloadDTO.PhoneNumberDTO phone = new AdPayloadDTO.PhoneNumberDTO("US", phoneNumber);

		AdPayloadDTO.ResponsiveDisplayAdDTO responsiveDisplayAd = new AdPayloadDTO.ResponsiveDisplayAdDTO(
				Collections.singletonList(headline),
				Collections.singletonList(marketingImage),
				Collections.singletonList(finalUrl),
				"Business Name",
				"Call now",
				phone
		);

		AdPayloadDTO.AdDTO ad = new AdPayloadDTO.AdDTO(responsiveDisplayAd);
		return new AdPayloadDTO(adGroupId, ad);
	}
}

//GoogleAdsService: This service handles the creation of budgets, campaigns, ad groups, and ads using the Google Ads API.
//createBudget: Creates a budget for the campaign.
//createCampaign: Creates a campaign using the specified budget.
//createAdGroup: Creates an ad group within the campaign.
//createAd: Creates an ad within the ad group.
//AuthController: The controller provides endpoints to create budgets, campaigns, ad groups, and ads.
//Each method retrieves the access token for the user and calls the appropriate service method to create the desired component.
//Types of Ads
//Google Ads supports various types of ads, including:
//
//Search Ads: Text ads that appear on Google search results pages.
//Display Ads: Image or video ads that appear on websites in the Google Display Network.
//Video Ads: Ads that appear on YouTube and other video partner sites.
//Shopping Ads: Product ads that appear in Google Shopping and search results.
//Responsive Ads: Ads that automatically adjust their size, appearance, and format to fit available ad spaces.
//Call-Only Ads: Ads designed to encourage phone calls to your business.
//App Promotion Ads: Ads that promote app installations and engagement.
//This setup provides a comprehensive approach to creating and managing Google Ads components using the Google Ads API with RestTemplate. Adjust the payloads and endpoints as needed to fit your specific requirements.
