package com.businessAds.integration.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@GetMapping("/googleAuth")
	public String googleAuthRedirect(@AuthenticationPrincipal OAuth2User principal) {
		if (principal != null) {
			return "Authenticated successfully with attributes: " + principal.getAttributes();
		}
		return "Authentication failed";
	}

}
