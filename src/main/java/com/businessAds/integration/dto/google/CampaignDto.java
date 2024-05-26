package com.businessAds.integration.dto.google;

import lombok.Data;

import java.util.List;

@Data
public class CampaignDto extends CampaignSubDTO {
    private String resourceName;
    private String primaryStatus;
    private List<String> primaryStatusReasons;
    private String status;
    private String servingStatus;
    private String biddingStrategySystemStatus;
    private String adServingOptimizationStatus;
    private String advertisingChannelType;
    private String advertisingChannelSubType;
    private List<CustomParameter> urlCustomParameters;
    private LocalServicesCampaignSettings localServicesCampaignSettings;
    private TravelCampaignSettings travelCampaignSettings;
    private DiscoveryCampaignSettings discoveryCampaignSettings;
    private RealTimeBiddingSetting realTimeBiddingSetting;
    private NetworkSettings networkSettings;
    private HotelSettingInfo hotelSetting;
    private DynamicSearchAdsSetting dynamicSearchAdsSetting;
    private ShoppingSetting shoppingSetting;
    private TargetingSetting targetingSetting;
    private GeoTargetTypeSetting geoTargetTypeSetting;
    private LocalCampaignSetting localCampaignSetting;
    private AppCampaignSetting appCampaignSetting;
    private List<String> labels;
    private String experimentType;
    private String biddingStrategyType;
    private String accessibleBiddingStrategy;
    private List<FrequencyCapEntry> frequencyCaps;
    private String videoBrandSafetySuitability;
    private VanityPharma vanityPharma;
    private SelectiveOptimization selectiveOptimization;
    private OptimizationGoalSetting optimizationGoalSetting;
    private TrackingSetting trackingSetting;
    private String paymentMode;
    private List<String> excludedParentAssetFieldTypes;
    private List<String> excludedParentAssetSetTypes;
    private PerformanceMaxUpgrade performanceMaxUpgrade;
    private List<AssetAutomationSetting> assetAutomationSettings;
    private String id;
    private String name;
    private String trackingUrlTemplate;
    private AudienceSetting audienceSetting;
    private String baseCampaign;
    private String campaignBudget;
    private String startDate;
    private String campaignGroup;
    private String endDate;
    private String finalUrlSuffix;
    private double optimizationScore;
    private boolean urlExpansionOptOut;
    private String hotelPropertyAssetSet;
    private String listingType;
    // Union field campaign_bidding_strategy can be only one of the following:
    private String biddingStrategy;
    private Commission commission;
    private ManualCpa manualCpa;
    private ManualCpc manualCpc;
    private ManualCpm manualCpm;
    private ManualCpv manualCpv;
    private MaximizeConversions maximizeConversions;
    private MaximizeConversionValue maximizeConversionValue;
    private TargetCpa targetCpa;
    private TargetImpressionShare targetImpressionShare;
    private TargetRoas targetRoas;
    private TargetSpend targetSpend;
    private PercentCpc percentCpc;
    private TargetCpm targetCpm;
    // End of list of possible types for union field campaign_bidding_strategy.

    // Constructors, getters, and setters
}
