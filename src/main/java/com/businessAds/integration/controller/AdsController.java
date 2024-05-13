package com.businessAds.integration.controller;

import com.businessAds.integration.dto.google.BudgetDTO;
import com.businessAds.integration.dto.google.GoogleResultDTO;
import com.businessAds.integration.dto.google.ResourceNamesDTO;
import com.businessAds.integration.services.impl.GoogleAdsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/ads")
public class AdsController {

	@Autowired
	private GoogleAdsService googleAdsService;

	Logger logger = LoggerFactory.getLogger(AdsController.class);

	@GetMapping("/google/account")
	public ResponseEntity<?> getAdAccounts(@RequestParam("email") String email) {
		ResourceNamesDTO adAccounts = googleAdsService.getAdAccounts(email);
		return new ResponseEntity<>(adAccounts, HttpStatus.OK);
	}

	@PostMapping("/google/create-budget")
	public ResponseEntity<?> createBudget(
			@RequestBody BudgetDTO budgetDTO,
			@RequestParam(value = "email", required = true) String email,
			@RequestHeader(value = "customerId", required = true) String customerId) {
		GoogleResultDTO budgetResponse = googleAdsService.createBudget(email, customerId, budgetDTO);
		return new ResponseEntity<>(budgetResponse, HttpStatus.OK);
	}

	@PostMapping("/google/create-campaign")
	public ResponseEntity<?> createCampaign(@RequestParam("email") String email,
			@RequestHeader("customerId") String customerId, @RequestHeader("budgetId") String budgetId) {
		String campaignResponse = googleAdsService.createCampaign(email, customerId, budgetId);
		return new ResponseEntity<>(campaignResponse, HttpStatus.OK);
	}

	@PostMapping("/google/create-adgroup")
	public ResponseEntity<?> createAdGroup(@RequestParam("email") String email,
			@RequestHeader("customerId") String customerId, @RequestHeader("campaignId") String campaignId) {
		String adGroupResponse = googleAdsService.createAdGroup(email, customerId, campaignId);
		return new ResponseEntity<>(adGroupResponse, HttpStatus.OK);
	}

	/**
	 * Type of Ads -> Required Fields
	 *
	 * 1. Search Ads:title (headlinePart1), description, finalUrl
	 * 2. Display Ads: title (headline), imageUrl (marketingImage), finalUrl, businessName, callToAction
	 * 3. Video Ads: videoId, finalUrl
	 * 4. Shopping Ads: productId, finalUrl
	 * 5. Responsive Ads: title (headline), description, finalUrl
	 * 6. Call-Only Ads: businessName, phoneNumber, countryCode, title (headline1), description (description1)
	 * 7. App Promotion Ads: appId, appStore, title (headline), description, finalUrl
	 */
	@PostMapping("/google/create-ad")
	public ResponseEntity<?> createAd(@RequestParam("email") String email,
			@RequestParam(value = "ad_type") String adType,
			@RequestHeader(value = "customerId", required = false) String customerId,
			@RequestHeader(value = "adGroupId", required = false) String adGroupId,
			@RequestHeader(value = "title", required = false) String title,
			@RequestHeader(value = "description", required = false) String description,
			@RequestHeader(value = "imageUrl", required = false) String imageUrl,
			@RequestHeader(value = "finalUrl", required = false) String finalUrl,
			@RequestHeader(value = "videoId", required = false) String videoId,
			@RequestHeader(value = "productId", required = false) String productId,
			@RequestHeader(value = "businessName", required = false) String businessName,
			@RequestHeader(value = "callToAction", required = false) String callToAction,
			@RequestHeader(value = "phoneNumber", required = false) String phoneNumber,
			@RequestHeader(value = "countryCode", required = false) String countryCode,
			@RequestHeader(value = "appId", required = false) String appId,
			@RequestHeader(value = "appStore", required = false) String appStore) {
		String adResponse = googleAdsService.createAd(email, adType, customerId, adGroupId, title, description,
				imageUrl, finalUrl, videoId, productId, businessName, callToAction, phoneNumber, countryCode, appId,
				appStore);
		return new ResponseEntity<>(adResponse, HttpStatus.OK);
	}

}
