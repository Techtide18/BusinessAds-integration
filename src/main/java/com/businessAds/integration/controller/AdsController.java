package com.businessAds.integration.controller;

import com.businessAds.integration.services.GoogleAdsService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ads")
public class AdsController {

	@Autowired
	private GoogleAdsService googleAdsService;

}
