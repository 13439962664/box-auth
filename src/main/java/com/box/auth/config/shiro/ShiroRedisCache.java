package com.box.auth.config.shiro;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class ShiroRedisCache implements Cache<Object, Object> {

	@Autowired
	private RedisTemplate<Object, Object> client;

    private String keyPrefix;
    private long ttl;

    public ShiroRedisCache(ShiroRedisCacheProperties redisCacheProperties) {
        this.keyPrefix = redisCacheProperties.getKeyPrefix();
        this.ttl = redisCacheProperties.getValueCacheExpire();
    }

    private Object getKey(Object k) {
        return this.keyPrefix + (k == null ? "*" : k);
    }

    @Override
    public Object get(Object o) throws CacheException {
        return client.opsForValue().get(getKey(o));
    }

    @Override
    public Object put(Object o, Object o2) throws CacheException {
        if (ttl >= 0) {
            client.opsForValue().set(getKey(o), o2, ttl, TimeUnit.MILLISECONDS);
        } else {
            client.opsForValue().set(getKey(o), o2);
        }
        return o2;
    }

    @Override
    public Object remove(Object o) throws CacheException {
        Object result = get(o);
        client.delete(getKey(o));
        return result;
    }

    @Override
    public void clear() throws CacheException {
        client.delete(getKey("*"));
    }

    @Override
    public int size() {
        return keys().size();
    }

    @Override
    public Set<Object> keys() {
        return client.keys(getKey("*"));
    }

    @Override
    public Collection<Object> values() {
        return client.opsForValue().multiGet(keys());
    }

}