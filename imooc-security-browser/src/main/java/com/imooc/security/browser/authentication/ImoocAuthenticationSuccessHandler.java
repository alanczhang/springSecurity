/**
 * 
 */
package com.imooc.security.browser.authentication;

/**
 * @author zhailiang
 *
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.security.core.properties.LoginResponseType;
import com.imooc.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 35-pxiaodong
 *
 */
@Component("imoocAuthenticationSuccessHandler")
//SavedRequestAwareAuthenticationSuccessHandler  spring默认的成功处理器
public class ImoocAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private SecurityProperties securityProperties;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.security.web.authentication.
	 * AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
										Authentication authentication) throws IOException, ServletException {

		logger.info("登录成功");

		//想要实现一个通用安全框架,把登录成功和失败写死也是不合适的,因为有些应用前端就是jsp或者其他前端语言,
		// 登录就是表单提交方式,这样之前spring security登录成功跳转更适合这种同步方式,代码改造,同时支持这两种方式,
		// 跳转和返回json让用户自己配置
		// 配置的登录方式如果是json，就返回json;否则调用父类的方法，跳转

		if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(authentication));
		} else {
			super.onAuthenticationSuccess(request, response, authentication);
		}

	}

}
