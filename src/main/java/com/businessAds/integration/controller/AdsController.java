package com.businessAds.integration.controller;

import com.businessAds.integration.dto.google.ResourceNamesDTO;
import com.businessAds.integration.services.impl.GoogleAdsService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	public ResponseEntity<?> createBudget(@RequestParam("email") String email,
			@RequestHeader("customerId") String customerId, @RequestHeader("amountMicros") long amountMicros) {
		String budgetResponse = googleAdsService.createBudget(email, customerId, amountMicros);
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

	@PostMapping("/google/create-ad")
	public ResponseEntity<?> createAd(@RequestParam("email") String email, @RequestParam("title") String title,
			@RequestHeader("customerId") String customerId, @RequestHeader("adGroupId") String adGroupId,
			@RequestHeader("imageUrl") String imageUrl, @RequestHeader("finalUrl") String finalUrl,
			@RequestHeader("phoneNumber") String phoneNumber) {
		String adResponse = googleAdsService.createAd(email, customerId, adGroupId, title, imageUrl, finalUrl,
				phoneNumber);
		return new ResponseEntity<>(adResponse, HttpStatus.OK);
	}

}
