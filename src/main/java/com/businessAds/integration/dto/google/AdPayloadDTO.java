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
		private ExpandedTextAdDTO expandedTextAd;
		private ResponsiveDisplayAdDTO responsiveDisplayAd;
		private VideoAdDTO videoAd;
		private ProductAdDTO productAd;
		private ResponsiveSearchAdDTO responsiveSearchAd;
		private CallOnlyAdDTO callOnlyAd;
		private AppAdDTO appAd;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ExpandedTextAdDTO {
		private String headlinePart1;
		private String description;
		private List<String> finalUrls;
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
	public static class VideoAdDTO {
		private VideoDTO video;
		private List<String> finalUrls;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class VideoDTO {
		private String id;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ProductAdDTO {
		private String productChannel;
		private String productId;
		private List<String> finalUrls;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ResponsiveSearchAdDTO {
		private List<HeadlineDTO> headlines;
		private List<DescriptionDTO> descriptions;
		private List<String> finalUrls;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class CallOnlyAdDTO {
		private String businessName;
		private String phoneNumber;
		private String countryCode;
		private String headline1;
		private String description1;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class AppAdDTO {
		private String appId;
		private String appStore;
		private List<HeadlineDTO> headlines;
		private List<DescriptionDTO> descriptions;
		private List<String> finalUrls;
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

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class DescriptionDTO {
		private String text;
	}
}
