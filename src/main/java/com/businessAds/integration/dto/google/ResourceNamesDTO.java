package com.businessAds.integration.dto.google;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ResourceNamesDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("resourceNames")
	private List<String> resourceNames;

}
