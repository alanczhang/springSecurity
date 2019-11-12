/**
 * 
 */
package com.imooc.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhailiang
 *
 */
//读取配置文件以imooc.security开头的配置信息    SecurityCoreConfig使配置文件SecurityProperties生效
@ConfigurationProperties(prefix = "imooc.security")
public class SecurityProperties {

	private BrowserProperties browser = new BrowserProperties();

	private ValidateCodeProperties validate = new ValidateCodeProperties();

	public ValidateCodeProperties getValidate() {
		return validate;
	}

	public void setValidate(ValidateCodeProperties validate) {
		this.validate = validate;
	}

	public BrowserProperties getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}

}


