package com.businessAds.integration.dto.google;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignPayloadDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private String name;
	private String advertisingChannelType;
	private String status;
	private String campaignBudget;
	private BiddingStrategyConfigurationDTO biddingStrategyConfiguration;

}
