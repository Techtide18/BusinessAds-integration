package com.businessAds.integration.dto.google;

import com.businessAds.integration.enums.BudgetDeliveryMethod;
import com.businessAds.integration.enums.BudgetPeriod;
import com.businessAds.integration.enums.BudgetStatus;
import com.businessAds.integration.enums.BudgetType;
import lombok.Data;

@Data
public class BudgetDTO {
    private String resourceName;
    private BudgetStatus status;
    private BudgetDeliveryMethod deliveryMethod;
    private BudgetPeriod period;
    private BudgetType type;
    private String alignedBiddingStrategyId;
    private String id;
    private String name;
    private Long amountMicros;
    private String totalAmountMicros;
    private boolean explicitlyShared;
    private String referenceCount;
    private boolean hasRecommendedBudget;
    private String recommendedBudgetAmountMicros;
    private String recommendedBudgetEstimatedChangeWeeklyClicks;
    private String recommendedBudgetEstimatedChangeWeeklyCostMicros;
    private String recommendedBudgetEstimatedChangeWeeklyInteractions;
    private String recommendedBudgetEstimatedChangeWeeklyViews;

}