package com.businessAds.integration.services.impl;

import com.businessAds.integration.controller.AuthController;
import com.businessAds.integration.dto.AccessToken;
import com.businessAds.integration.dto.RedisValue;
import com.businessAds.integration.services.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class AccessTokenService extends RedisService<String, Object> {

    Logger logger = LoggerFactory.getLogger(AccessTokenService.class);

    public AccessToken getAccessTokenFromRedis(String uniqueId) {
        try {
            RedisValue<String> redisValue = getKeyValueFromRedis(uniqueId);
            return StringUtils.isNotBlank(redisValue.getValue()) ? new AccessToken(redisValue.getValue()) : null;
        } catch (Exception e) {
            logger.error("Exception while getting value from redis", e);
            return null;
        }
    }

    public void setAccessTokenInRedis(String uniqueId, String value, Long expiry, TimeUnit timeUnit){
        setOpsForValueWithExpiry(uniqueId, value, expiry, timeUnit);
    }

}
