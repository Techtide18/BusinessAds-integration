package com.businessAds.integration.dto.google;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPayloadDTO {
	private String adGroup;
	private AdDTO ad;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class AdDTO {
		private ResponsiveDisplayAdDTO responsiveDisplayAd;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ResponsiveDisplayAdDTO {
		private List<HeadlineDTO> headlines;
		private List<MarketingImageDTO> marketingImages;
		private List<String> finalUrls;
		private String businessName;
		private String callToActionText;
		private PhoneNumberDTO phoneNumber;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class HeadlineDTO {
		private String text;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class MarketingImageDTO {
		private String url;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class PhoneNumberDTO {
		private String countryCode;
		private String phoneNumber;
	}

}
