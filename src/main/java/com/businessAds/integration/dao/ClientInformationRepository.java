package com.businessAds.integration.dao;

import com.businessAds.integration.pojo.ClientInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientInformationRepository extends JpaRepository<ClientInformation, String> {
}
