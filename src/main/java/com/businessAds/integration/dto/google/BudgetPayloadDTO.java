package com.businessAds.integration.dto.google;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class BudgetPayloadDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private long amountMicros;
	private String deliveryMethod;

}
