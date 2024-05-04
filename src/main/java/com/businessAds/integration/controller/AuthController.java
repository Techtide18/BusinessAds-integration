package com.businessAds.integration.controller;

import com.businessAds.integration.service.GoogleAdsService;
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

	// @shubham add logger here
	// private Logger logger = new logger();

	@GetMapping(value = "/google")
	public ModelAndView authenticateUser() {
		logger.info("Recieved webhook to authenticate the user");
		return googleAdsService.redirectUserToAuthenticationUrl();
	}

	@GetMapping("/google/callback")
	public ResponseEntity<?> authenticateUserCallback(@RequestParam("code") String authorizationCode) {
		logger.info("Recieved callback webhook to get the tokens for the user");
		googleAdsService.exchangeAuthorizationCodeForTokensAndSaveUserRefreshTokenInDB(authorizationCode);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
