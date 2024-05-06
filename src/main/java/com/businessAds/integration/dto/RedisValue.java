package com.businessAds.integration.dto;

import lombok.Data;

import java.util.concurrent.TimeUnit;

@Data
public class RedisValue<V> {

    private V value;

    private Long timeout;

    private TimeUnit timeUnit;

    private Boolean fetchedFromCache;

    public RedisValue() {
        super();
    }

    public RedisValue(V value, Long timeout, TimeUnit timeUnit, Boolean fetchedFromCache) {
        super();
        this.value = value;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.fetchedFromCache = fetchedFromCache;
    }
}
