package com.businessAds.integration.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client_information")
public class ClientInformation extends BaseEntity{

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String uniqueId;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String refreshToken;

}
