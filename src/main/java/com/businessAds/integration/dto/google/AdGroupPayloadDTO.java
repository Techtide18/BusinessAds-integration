package com.businessAds.integration.dto.google;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class AdGroupPayloadDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String campaign;
	private String status;
	private String type;
	private long cpcBidMicros;

}

