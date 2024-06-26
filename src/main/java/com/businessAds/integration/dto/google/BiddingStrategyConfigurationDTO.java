package com.businessAds.integration.dto.google;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiddingStrategyConfigurationDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String biddingStrategyType;

}
