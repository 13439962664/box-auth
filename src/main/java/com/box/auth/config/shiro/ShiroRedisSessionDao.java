package com.box.auth.config.shiro;

import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.box.utils.RedisUtil;

@Component
public class ShiroRedisSessionDao extends EnterpriseCacheSessionDAO {
	@Autowired
	private ShiroRedisCacheProperties shiroRedisCacheProperties;
	@Autowired
	private RedisUtil redisUtil;

	private String getKey(Object k) {
		return shiroRedisCacheProperties.getSessionPrefix() + (k == null ? "*" : k);
	}
	
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        String sessionIdStr = getKey(sessionId.toString());
        redisUtil.set(sessionIdStr,session);
        return sessionId;
    }
    
    @Override
    public Session doReadSession(Serializable sessionId) {
    	Session session = super.doReadSession(sessionId);
    	String sessionIdStr = getKey(sessionId.toString());
    	if(session==null) {
            Object obj = redisUtil.get(sessionIdStr);
            session = (Session)obj;
    	}
        return session;
    }

    //设置session的最后一次访问时间
    @Override
    protected void doUpdate(Session session) {
        super.doUpdate(session);
        String sessionIdStr = getKey(session.getId().toString());
        redisUtil.set(sessionIdStr,session);
    }
    

    // 删除session
    @Override
    protected void doDelete(Session session) {
        super.doDelete(session);
        String sessionIdStr = getKey(session.getId().toString());
        redisUtil.del(sessionIdStr);
    }

}
