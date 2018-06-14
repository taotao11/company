package com.example.company.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 拦截
 */
public class ShiroFilter extends AuthenticatingFilter {
    //创建token
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return null;
    }
    /**
     * 是否允许被访问,跟下面的onAccessDenied，当访问被拒绝时联合使用
     * @return
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return false;
    }
    /**
     * 登陆失败处理
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        return super.onLoginFailure(token, e, request, response);
    }

    /**
     * 访问拒绝时是否自己处理
     * 主要实现该方法，拦截我们的请求，处理onAccessDenied（）方法，接收到请求的参数，
     * 组装成StatelessAuthenticationToken，然后委托为Realm进行处理
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return super.onAccessDenied(request, response, mappedValue);
    }
}
