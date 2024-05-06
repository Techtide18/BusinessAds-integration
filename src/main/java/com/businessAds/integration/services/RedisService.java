package com.businessAds.integration.services;

import com.businessAds.integration.dto.RedisValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.convert.Converters;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public abstract class RedisService<V, HV> extends BaseService {

    @Autowired
    private RedisTemplate<String, V> redisTemplate;

    public RedisValue<V> getKeyValueFromRedis(String redisKey) {
        V value = null;
        Long expireTime = null;
        try {
            value = redisTemplate.opsForValue().get(redisKey);
            expireTime = getExpiry(redisKey, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error("Exception while getting value from redis for key {}", redisKey, e);
        }
        return new RedisValue<V>(value, expireTime, TimeUnit.SECONDS, true);
    }

    public Long getExpiry(String redisKey, TimeUnit timeUnit) {
        Long expireTime = redisTemplate.getExpire(redisKey);
        return null != expireTime ? Converters.secondsToTimeUnit(expireTime, timeUnit) : 0;
    }

    public void deleteKeys(Set<String> keys) {
        redisTemplate.delete(keys);
    }


    public void setOpsForValueWithExpiry(String redisKey, V value, Long timeout, TimeUnit unit) {
        logger.info("Setting value in redis for key {} with timeout {} and timeUnit {}", redisKey, timeout, unit);
        try {
            setOpsForValue(redisKey, value);
            if (timeout != null && timeout > 0 && unit != null) {
                redisTemplate.expire(redisKey, timeout, unit);
            }
        } catch (Exception e) {
            logger.error("Exception while setting value in redis for key {}", redisKey, e);
        }

    }

    public void setOpsForValue(String redisKey, V value) {
        logger.info("Setting value in redis for key {}", redisKey);
        try {
            redisTemplate.opsForValue().set(redisKey, value);
            logger.info("Saved value in redis for key {} successfully", redisKey);
        } catch (Exception e) {
            logger.error("Exception while setting value in redis for key {}", redisKey, e);
        }
    }
}
