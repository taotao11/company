package com.example.company.config;


import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置文件
 * 1.配置安全管理器 securityManager
 * 2.自定义realm
 * 3.配置会话管理器
 *4.配置过滤链
 */
@Configuration
public class ShiroConfiguration {
    /**
     * 1.配置安全管理器 securityManager
     * @param shiroRealm
     * @param sessionManager
     * @return
     */
    @Bean("securityManager")
    public SecurityManager securityManager(ShiroRealm shiroRealm, SessionManager sessionManager){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm);//设置授权管理
        securityManager.setSessionManager(sessionManager);//设置会话管理
        return securityManager;
    }

    /**
     * 自定义realm
     *
     * @return
     */
    @Bean
    public ShiroRealm shiroRealm(){

        return new ShiroRealm();
    }

    /**
     * 配置会话管理
     * @return
     */
    @Bean
    public SessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //开启会话调度器
        sessionManager.setSessionValidationSchedulerEnabled(true);
        //禁用将JSESSIONID附加到URL
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }

    /**
     * 配置过滤工厂
     * @return
     */
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){

        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        //shiroFilter 过滤
        Map<String,Filter> shiroFilterMap = new HashMap<>();
        shiroFilterMap.put("oauth2",new ShiroFilter());

        shiroFilter.setFilters(shiroFilterMap);

        Map<String,String> filterMap = new LinkedHashMap<>();
        filterMap.put("","");
        filterMap.put("","");
        filterMap.put("","");
        filterMap.put("","");

        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
    }
    //管理shiro bean生命周期
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    //实现spring的自动代理
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
