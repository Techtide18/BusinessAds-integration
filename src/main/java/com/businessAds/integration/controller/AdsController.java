package com.businessAds.integration.controller;

import com.businessAds.integration.dto.AdDto;
import com.businessAds.integration.services.GoogleAdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ads")
public class AdsController {

	@Autowired
	private GoogleAdsService googleAdsService;

	@GetMapping("/{userId}")
	public ResponseEntity<List<AdDto>> getUserAds(@PathVariable String userId) {
		List<AdDto> ads = googleAdsService.getUserAds(userId);
		return ResponseEntity.ok(ads);
	}

}
