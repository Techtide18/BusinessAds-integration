package com.businessAds.integration.enums;

public class CampaignEnums {
    public enum CampaignPrimaryStatus {
        UNKNOWN,
        ELIGIBLE,
        PAUSED,
        REMOVED,
        ENDED,
        PENDING,
        SUSPENDED
    }

    public enum CampaignPrimaryStatusReason {
        UNKNOWN,
        EXCEEDED_BUDGET,
        NOT_ELIGIBLE,
        // Add more reasons as per the API documentation
    }

    public enum CampaignStatus {
        UNKNOWN,
        ENABLED,
        PAUSED,
        REMOVED
    }

    public enum CampaignServingStatus {
        SERVING,
        NONE,
        ENDED,
        PENDING,
        SUSPENDED
    }

    public enum BiddingStrategySystemStatus {
        ELIGIBLE,
        LEARNING,
        LIMITED,
        MISCONFIGURED,
        PAUSED,
        UNKNOWN
    }

    public enum AdServingOptimizationStatus {
        UNOPTIMIZED,
        OPTIMIZED,
        UNKNOWN
    }

    public enum AdvertisingChannelType {
        SEARCH,
        DISPLAY,
        SHOPPING,
        HOTEL,
        VIDEO,
        MULTI_CHANNEL
    }

    public enum AdvertisingChannelSubType {
        SEARCH_MOBILE_APP,
        DISPLAY_MOBILE_APP,
        SEARCH_EXPRESS,
        DISPLAY_EXPRESS,
        SHOPPING_SMART_ADS,
        DISPLAY_GMAIL_AD,
        DISPLAY_SMART_CAMPAIGN,
        VIDEO_OUTSTREAM,
        VIDEO_ACTION,
        VIDEO_NON_SKIPPABLE,
        APP_CAMPAIGN,
        APP_CAMPAIGN_FOR_ENGAGEMENT,
        LOCAL_CAMPAIGN,
        PERFORMANCE_MAX,
        SMART
    }

    public enum CampaignExperimentType {
        BASE,
        DRAFT,
        EXPERIMENT
    }

    public enum BiddingStrategyType {
        COMMISSION,
        ENHANCED_CPC,
        MANUAL_CPC,
        MANUAL_CPM,
        MANUAL_CPV,
        MAXIMIZE_CONVERSIONS,
        MAXIMIZE_CONVERSION_VALUE,
        TARGET_CPA,
        TARGET_IMPRESSION_SHARE,
        TARGET_ROAS,
        TARGET_SPEND,
        PERCENT_CPC
    }

    public enum BrandSafetySuitability {
        UNSPECIFIED,
        ALL,
        LIMITED,
        NONE
    }

    public enum PaymentMode {
        CLICKS,
        CONVERSIONS,
        UNKNOWN
    }

    public enum AssetFieldType {
        UNKNOWN,
        HEADLINE,
        DESCRIPTION,
        MANDATORY_AD_TEXT,
        MARKETING_IMAGE,
        MEDIA_BUNDLE,
        ICON_IMAGE,
        LOGO,
        LONG_HEADLINE,
        BUSINESS_NAME,
        SQUARE_MARKETING_IMAGE,
        PORTRAIT_MARKETING_IMAGE,
        YOUTUBE_VIDEO
    }

    public enum AssetSetType {
        UNKNOWN,
        PAGE_FEED,
        DYNAMIC_EDUCATION,
        DYNAMIC_REAL_ESTATE,
        DYNAMIC_CUSTOM,
        DYNAMIC_HOTELS_AND_RENTALS,
        DYNAMIC_FLIGHTS,
        DYNAMIC_TRAVEL,
        DYNAMIC_LOCAL,
        DYNAMIC_JOBS
    }

    public enum ListingType {
        UNKNOWN,
        LISTING,
        UNIT_LISTING,
        OFFER,
        UNKNOWN_LISTING_TYPE
    }

}
