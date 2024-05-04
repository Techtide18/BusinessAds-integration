package com.businessAds.integration.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_added")
	@CreatedDate
	protected Date dateAdded;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_modified")
	@LastModifiedDate
	protected Date dateModified;

}
