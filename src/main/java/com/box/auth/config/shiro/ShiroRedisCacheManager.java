package com.box.auth.config.shiro;

import javax.annotation.Resource;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component("shiroRedisCacheManager")
public class ShiroRedisCacheManager implements CacheManager {

	@Resource
    private ShiroRedisCache shiroRedisCache;

    @SuppressWarnings("unchecked")
    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return (Cache<K, V>) shiroRedisCache;
    }
}