package com.businessAds.integration.controller;

import com.businessAds.integration.services.impl.GoogleAdsService;
import com.businessAds.integration.services.impl.RedisServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private GoogleAdsService googleAdsService;

	@Autowired
	private RedisServiceImpl<String,String> redisServiceImpl;

	Logger logger = LoggerFactory.getLogger(AuthController.class);

	@GetMapping(value = "/google")
	public ModelAndView authenticateUser() {
		logger.info("Received webhook to authenticate the user");
		return googleAdsService.redirectUserToAuthenticationUrl();
	}

	@GetMapping("/google/callback")
	public ResponseEntity<?> authenticateUserCallback(@RequestParam("code") String authorizationCode) {
		logger.info("Received callback webhook to get the tokens for the user");
		googleAdsService.exchangeAuthorizationCodeForTokensAndSaveUserRefreshTokenInDB(authorizationCode);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
