/**
 * 
 */
package com.imooc.security.browser;

import com.imooc.security.browser.authentication.ImoocAuthenctiationFailureHandler;
import com.imooc.security.browser.authentication.ImoocAuthenticationSuccessHandler;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author zhailiang
 *
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

	private final static String loginPage = "/imooc-signIn.html";

	@Autowired
	private SecurityProperties securityProperties;


	//让系统使用我们自定义 而不是系统默认的配置
	@Autowired
	private ImoocAuthenticationSuccessHandler imoocAuthenticationSuccessHandler;

	@Autowired
	private ImoocAuthenctiationFailureHandler imoocAuthenctiationFailureHandler;




	@Override
	protected void configure(HttpSecurity http) throws Exception {



		ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
		validateCodeFilter.setAuthenticationFailureHandler(imoocAuthenctiationFailureHandler);
		//传递参数
		validateCodeFilter.setSecurityProperties(securityProperties);
		validateCodeFilter.afterPropertiesSet();

		//实现的效果：让它去表单登录，而不是alert框
		//http.httpBasic()
		http.formLogin()
				.loginPage(loginPage)//设置登录页面
				.loginProcessingUrl("/authentication/form") //遇到该请求则进行user password认证
				.successHandler(imoocAuthenticationSuccessHandler)//成功后 使用我们自己的处理器处理
				.failureHandler(imoocAuthenctiationFailureHandler )
				.and()
			.authorizeRequests()//对请求进行授权
				//当访问这个路径的时候不需要身份认证 除了它其他的是需要身份认证
				.antMatchers(loginPage).permitAll()
				.antMatchers(securityProperties.getBrowser().getLoginPage()).permitAll()
				.antMatchers("/code/image").permitAll()
				.antMatchers( "/user" ).hasRole( "ADMIN" )
			.anyRequest()//任何请求
				.authenticated()
				.and()
				.csrf().disable();      //关闭csrf;
	}

	//密码加密
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
