package com.businessAds.integration.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;

@Data
public class GoogleTokenDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("access_token")
	private String accessToken;

	@JsonProperty("expires_in")
	private Long expiresIn;

	@JsonProperty("refresh_token")
	private String refreshToken;

	private String scope;

	@JsonProperty("token_type")
	private String tokenType;

	@JsonProperty("id_token")
	private String idToken;

}
