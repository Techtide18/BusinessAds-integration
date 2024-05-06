package com.businessAds.integration.services;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Transactional
public class BaseService {
    Logger logger = LoggerFactory.getLogger(BaseService.class);
}
