package com.box.auth.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.box.auth.config.shiro.ShiroRedisCacheManager;
import com.box.auth.config.shiro.ShiroRedisCacheProperties;
import com.box.auth.config.shiro.ShiroRedisSessionDao;
import com.box.auth.custom.ShiroCustomRealm;

@Configuration
public class ShiroConfig {

	// Filter工厂，设置对应的过滤条件和跳转条件
	@Bean
	public ShiroFilterFactoryBean shiroFilter() {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		shiroFilterFactoryBean.setSecurityManager(securityManager());
		// 登录
		shiroFilterFactoryBean.setLoginUrl("/loginPage");
		// 首页
		shiroFilterFactoryBean.setSuccessUrl("/loginSuccess");
		// 错误页面，认证不通过跳转
		shiroFilterFactoryBean.setUnauthorizedUrl("/error");
		// 当运行一个Web应用程序时,Shiro将会创建一些有用的默认Filter实例,并自动地在[main]项中将它们置为可用自动地可用的默认的Filter实例是被DefaultFilter枚举类定义的,枚举的名称字段就是可供配置的名称
		// Shiro验证URL时,URL匹配成功便不再继续匹配查找(所以要注意配置文件中的URL顺序,尤其在使用通配符时)
		// 配置不会被拦截的链接 顺序判断
		Map<String, String> filterMap = new HashMap<>();
		// 登出
		filterMap.put("/logout", "logout");
		// 对所有用户认证
		filterMap.put("/favicon.ico", "anon");
		filterMap.put("/login", "anon");
		filterMap.put("/index", "anon");
		filterMap.put("/**/*.html", "anon");
		filterMap.put("/**/*.js", "anon");
		filterMap.put("/**/*.css", "anon");
		filterMap.put("/**/chat/**", "anon");
		
		// 所有请求需要oauth2认证
		filterMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
		return shiroFilterFactoryBean;
	}
	
	@Autowired
	private ShiroRedisCacheManager shiroRedisCacheManager;
	
	/**
	 * description: 权限管理，配置主要是Realm的管理认证
	 * 
	 * @return SecurityManager
	 */
	@Bean(name = "securityManager")
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(shiroCustomRealm());
		// 自定义session管理 使用redis
		securityManager.setSessionManager(sessionManager());
		// 自定义缓存实现 使用redis
		securityManager.setCacheManager(shiroRedisCacheManager);
		return securityManager;
	}

	@Autowired
	private ShiroRedisSessionDao shiroRedisSessionDao;
	
	/**
	 * session 管理对象
	 */
	private DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setGlobalSessionTimeout(ShiroRedisCacheProperties.MILLIS_PER_MINUTE*30);
		shiroRedisSessionDao.setSessionIdGenerator(new JavaUuidSessionIdGenerator());
		sessionManager.setSessionDAO(shiroRedisSessionDao);
		return sessionManager;
	}

	/**
	 * 将自己的验证方式加入容器
	 */
	@Bean
	public ShiroCustomRealm shiroCustomRealm() {
		ShiroCustomRealm customRealm = new ShiroCustomRealm();
		customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return customRealm;
	}

	/**
	 * 自定义sessionManager
	 */
//	@Bean
//	public SessionManager sessionManager() {
//		BoxSessionManager sessionManager = new BoxSessionManager();
//		sessionManager.setSessionDAO(redisSessionDAO());
//		return sessionManager;
//	}

	/**
	 * cacheManager 缓存 redis实现, 使用的是shiro-redis开源插件
	 */
//	@Bean
//	public RedisCacheManager redisCacheManager() {
//		RedisCacheManager redisCacheManager = new RedisCacheManager();
//		redisCacheManager.setRedisManager(redisManager());
//		// 必须要设置主键名称，shiro-redis 插件用过这个缓存用户信息
//		redisCacheManager.setPrincipalIdFieldName("id");
//		return redisCacheManager;
//	}

	/**
	 * 凭证匹配器（由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了）
	 * 
	 * @return HashedCredentialsMatcher
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		// 散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashAlgorithmName("md5");
		// 散列的次数，比如散列两次，相当于 md5(md5(""));
		hashedCredentialsMatcher.setHashIterations(10);
		return hashedCredentialsMatcher;
	}

	@Value("${server.servlet.session.timeout}")
	public int session_timeout;

	/**
	 * RedisSessionDAO shiro sessionDao层的实现 通过redis, 使用的是shiro-redis开源插件
	 * 
	 * @return RedisSessionDAO
	 */
//	@Bean
//	public RedisSessionDAO redisSessionDAO() {
//		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
//		redisSessionDAO.setRedisManager(redisManager());
//		redisSessionDAO.setSessionIdGenerator(sessionIdGenerator());
//		redisSessionDAO.setExpire(session_timeout);
//		return redisSessionDAO;
//	}

	/**
	 * Session ID 生成器
	 * 
	 * @return JavaUuidSessionIdGenerator
	 */
	@Bean
	public JavaUuidSessionIdGenerator sessionIdGenerator() {
		return new JavaUuidSessionIdGenerator();
	}

	/**
	 * 配置shiro redisManager, 使用的是shiro-redis开源插件
	 * 
	 * @return RedisManager
	 */
//	private RedisManager redisManager() {
//		RedisManager redisManager = new RedisManager();
//		redisManager.setHost( host+ ":" + port);
//		redisManager.setTimeout(timeout);
//		redisManager.setPassword(password);
//		return redisManager;
//	}

	/*
	 * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,
	 * 并在必要时进行安全逻辑验证 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)
	 * 和AuthorizationAttributeSourceAdvisor)即可实现此功能
	 */
	@Bean
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		advisorAutoProxyCreator.setProxyTargetClass(true);
		return advisorAutoProxyCreator;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
		return authorizationAttributeSourceAdvisor;
	}

	@Bean
	public SimpleCookie cookie() {
		// cookie的name,对应的默认是 JSESSIONID
		SimpleCookie cookie = new SimpleCookie("SHARE_JSESSIONID");
		cookie.setHttpOnly(true);
		// path为 / 用于多个系统共享 JSESSIONID
		cookie.setPath("/");
		return cookie;
	}

	/* 此项目使用 shiro 场景为前后端分离项目，这里先注释掉，统一异常处理已在 GlobalExceptionHand.java 中实现 */
	/**
	 * description: 异常处理, 详见：https://www.cnblogs.com/libra0920/p/6289848.html
	 * 
	 * @return SimpleMappingExceptionResolver
	 */
//    @Bean(name = "simpleMappingExceptionResolver")
//    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
//        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
//        Properties mappings = new Properties();
//        mappings.setProperty("DatabaseException", "databaseError");//数据库异常处理
//        mappings.setProperty("UnauthorizedException", "/user/403");
//        r.setExceptionMappings(mappings);  // None by default
//        r.setDefaultErrorView("error");    // No default
//        r.setExceptionAttribute("exception");     // Default is "exception"
//        //r.setWarnLogCategory("example.MvcLogger");     // No default
//        return r;
//    }
}
