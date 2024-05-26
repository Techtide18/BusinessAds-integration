package com.businessAds.integration.dto.google;

import lombok.Data;

import java.util.List;

public abstract class CampaignSubDTO {

    @Data
    public class CustomParameter {
        private String key;
        private String value;

        // Getters and setters
    }
    @Data
    public class LocalServicesCampaignSettings {
        private String category;
        private String serviceAreaBusiness;

        // Getters and setters
    }
    @Data
    public class TravelCampaignSettings {
        private String travelAgencyId;

        // Getters and setters
    }
    @Data
    public class DiscoveryCampaignSettings {
        private String targetCpa;

        // Getters and setters
    }
    @Data
    public class RealTimeBiddingSetting {
        private boolean optIn;

        // Getters and setters
    }
    @Data
    public class NetworkSettings {
        private boolean targetGoogleSearch;
        private boolean targetSearchNetwork;
        private boolean targetContentNetwork;
        private boolean targetPartnerSearchNetwork;

        // Getters and setters
    }
    @Data
    public class HotelSettingInfo {
        private boolean hotelCenterId;

        // Getters and setters
    }
    @Data
    public class DynamicSearchAdsSetting {
        private String domainName;
        private String languageCode;

        // Getters and setters
    }
    @Data
    public class ShoppingSetting {
        private long merchantId;
        private String salesCountry;
        private long campaignPriority;
        private boolean enableLocal;
        private String feedLabel;

        // Getters and setters
    }
    @Data
    public class TargetingSetting {
        private List<String> targetRestrictions;
        private List<String> targetRestrictionsOperation;

        // Getters and setters
    }
    @Data
    public class GeoTargetTypeSetting {
        private String positiveGeoTargetType;
        private String negativeGeoTargetType;

        // Getters and setters
    }
    @Data
    public class LocalCampaignSetting {
        private String locationSourceType;

        // Getters and setters
    }
    @Data
    public class AppCampaignSetting {
        private String appId;
        private String appStore;

        // Getters and setters
    }
    @Data
    public class FrequencyCapEntry {
        private long impressions;
        private String timeUnit;
        private String level;
        private long cap;

        // Getters and setters
    }
    @Data
    public class VanityPharma {
        private String vanityPharmaText;
        private String vanityPharmaDisplayUrlMode;

        // Getters and setters
    }
    @Data
    public class SelectiveOptimization {
        private List<String> conversionActions;

        // Getters and setters
    }
    @Data
    public class OptimizationGoalSetting {
        private List<String> optimizationGoalTypes;

        // Getters and setters
    }
    @Data
    public class TrackingSetting {
        private String trackingUrl;

        // Getters and setters
    }
    @Data
    public class PerformanceMaxUpgrade {
        private String performanceMaxCampaign;
        private boolean preUpgradeCampaign;

        // Getters and setters
    }
    @Data
    public class AssetAutomationSetting {
        private String assetType;
        private boolean enabled;

        // Getters and setters
    }
    @Data
    public class AudienceSetting {
        private String audienceId;

        // Getters and setters
    }
    @Data
    public class Commission {
        private double commissionRateMicros;

        // Getters and setters
    }
    @Data
    public class ManualCpa {
        // Fields as per API documentation

        // Getters and setters
    }
    @Data
    public class ManualCpc {
        private boolean enhancedCpcEnabled;

        // Getters and setters
    }
    @Data
    public class ManualCpm {
        // Fields as per API documentation

        // Getters and setters
    }
    @Data
    public class ManualCpv {
        // Fields as per API documentation

        // Getters and setters
    }
    @Data
    public class MaximizeConversions {
        // Fields as per API documentation

        // Getters and setters
    }
    @Data
    public class MaximizeConversionValue {
        // Fields as per API documentation

        // Getters and setters
    }
    @Data
    public class TargetCpa {
        private double targetCpaMicros;

        // Getters and setters
    }
    @Data
    public class TargetImpressionShare {
        private String location;
        private double locationFractionMicros;
        private double cpcBidCeilingMicros;

        // Getters and setters
    }
    @Data
    public class TargetRoas {
        private double targetRoas;

        // Getters and setters
    }
    @Data
    public class TargetSpend {
        private double targetSpendMicros;

        // Getters and setters
    }
    @Data
    public class PercentCpc {
        private double cpcBidCeilingMicros;
        private boolean enhancedCpcEnabled;

        // Getters and setters
    }
    @Data
    public class TargetCpm {
        // Fields as per API documentation

        // Getters and setters
    }

}
