package com.box.auth.config.shiro;

import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class ShiroRedisSessionDao extends EnterpriseCacheSessionDAO {
	@Autowired
	private ShiroRedisCacheProperties shiroRedisCacheProperties;
	@Autowired
	private RedisTemplate<Object,Object> redisTemplate;

	private String getKey(String key) {
		return shiroRedisCacheProperties.getSessionPrefix() + key;
	}
	
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        String sessionIdStr = getKey(sessionId.toString());
        redisTemplate.opsForValue().set(sessionIdStr,session);
        return sessionId;
    }
    
    @Override
    protected Session doReadSession(Serializable sessionId) {
    	Session session = super.doReadSession(sessionId);
    	String sessionIdStr = getKey(sessionId.toString());
    	if(session==null) {
            session = (Session)redisTemplate.opsForValue().get(sessionIdStr);
    	}
    	
        return session;
    }

    //设置session的最后一次访问时间
    @Override
    protected void doUpdate(Session session) {
        super.doUpdate(session);
        String sessionIdStr = getKey(session.getId().toString());
        redisTemplate.opsForValue().set(sessionIdStr,session);
    }
    

    // 删除session
    @Override
    protected void doDelete(Session session) {
        super.doDelete(session);
        String sessionIdStr = getKey(session.getId().toString());
        redisTemplate.delete(sessionIdStr);
    }

}
