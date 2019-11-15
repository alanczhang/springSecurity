/**
 * 
 */
package com.imooc.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @author zhailiang
 *
 */
@Component
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PasswordEncoder passwordEncoder;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */


//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		logger.info("表单登录用户名:" + username);
//		return buildUser(username);
//	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		logger.info("登录用户名" + username);
		// TODO Auto-generated method stub


		String password = passwordEncoder.encode("123456");
		logger.info("用户库密码是：" + password);


		//Reason: 用户帐号已被锁定
		return new User("jojo",
				password,
				AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

//        return new User(username,
//                //passwordEncoder.encode("123456"),
//                password,
//                true,
//                true,
//                true,
//                true,
//                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));



	}

	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		logger.info("设计登录用户Id:" + userId);
		return buildUser(userId);
	}

	private SocialUserDetails buildUser(String userId) {
		// 根据用户名查找用户信息
		//根据查找到的用户信息判断用户是否被冻结
		String password = passwordEncoder.encode("123456");
		logger.info("数据库密码是:"+password);
		return new SocialUser(userId,
				password,  //encode方法是加密的时候用的
				//true,true,true,true,//依次代表 可用，没过期，密码没过期，没有被锁定为false
				true, true, true, true,
				//AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
				AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
	}

}
