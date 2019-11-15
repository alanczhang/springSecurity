/**
 * 
 */
package com.imooc.security.core.properties;

/**
 * @author zhailiang
 *
 */
//生成二维码默认配置
public class ImageCodeProperties {

	private int width = 67;    //图片长度
	private int height = 23;   //图片高度
	private int length = 4;    //验证码长度
	private int expireIn = 60; //失效时间



	private String url;        //多个请求需要验证；逗号隔开

	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getExpireIn() {
		return expireIn;
	}
	public void setExpireIn(int expireIn) {
		this.expireIn = expireIn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}



//public class ImageCodeProperties extends SmsCodeProperties {
//
//	public ImageCodeProperties() {
//		setLength(4);
//	}
//
//	private int width = 67;
//	private int height = 23;
//
//	public int getWidth() {
//		return width;
//	}
//	public void setWidth(int width) {
//		this.width = width;
//	}
//	public int getHeight() {
//		return height;
//	}
//	public void setHeight(int height) {
//		this.height = height;
//	}
//
//}
