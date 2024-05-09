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


}
