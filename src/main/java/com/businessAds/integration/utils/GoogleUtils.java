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

//Search Ads: Text ads that appear on Google search results pages.
//Display Ads: Image or video ads that appear on websites in the Google Display Network.
//Video Ads: Ads that appear on YouTube and other video partner sites.
//Shopping Ads: Product ads that appear in Google Shopping and search results.
//Responsive Ads: Ads that automatically adjust their size, appearance, and format to fit available ad spaces.
//Call-Only Ads: Ads designed to encourage phone calls to your business.
//App Promotion Ads: Ads that promote app installations and engagement.
