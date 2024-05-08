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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

//	@GetMapping("/google/campaigns")
//	public ResponseEntity<String> getCampaigns(@RequestParam("email") String email,
//			@RequestParam("customerId") String customerId) {
//		String campaigns = googleAdsService.getCampaigns(accessToken, customerId);
//		return new ResponseEntity<>(campaigns, HttpStatus.OK);
//	}
//
//	@GetMapping("/google/ads")
//	public ResponseEntity<String> getAds(@RequestParam("email") String email,
//			@RequestParam("customerId") String customerId, @RequestParam("campaignId") String campaignId) {
//		String ads = googleAdsService.getAds(accessToken, customerId, campaignId);
//		return new ResponseEntity<>(ads, HttpStatus.OK);
//	}


}
