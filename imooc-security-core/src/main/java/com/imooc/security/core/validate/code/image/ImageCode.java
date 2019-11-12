/**
 * 
 */
package com.imooc.security.core.validate.code.image;

import com.imooc.security.core.validate.code.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author zhailiang
 *
 */

public class ImageCode extends ValidateCode {

	private BufferedImage image;

	public ImageCode(BufferedImage image, String code, int expireIn){
		super(code, expireIn);
		this.image = image;
	}

	public ImageCode(BufferedImage image, String code, LocalDateTime expireTime){
		super(code, expireTime);
		this.image = image;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

}

//public class ImageCode extends ValidateCode {
//
//	private BufferedImage image;//展示的图片
//	private String code;//生成的随机数，Session
//	private LocalDateTime expireTime;//过期时间
//
//	public BufferedImage getImage() {
//		return image;
//	}
//	public void setImage(BufferedImage image) {
//		this.image = image;
//	}
//	public String getCode() {
//		return code;
//	}
//	public void setCode(String code) {
//		this.code = code;
//	}
//	public LocalDateTime getExpireTime() {
//		return expireTime;
//	}
//	public void setExpireTime(LocalDateTime expireTime) {
//		this.expireTime = expireTime;
//	}
//
//	//是否过期
//	public boolean isExpried() {
//		return LocalDateTime.now().isAfter(expireTime);
//	}
//
//	public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
//		this.image = image;
//		this.code = code;
//		this.expireTime = expireTime;
//	}
//
//	//多少秒过期（60秒）
//	public ImageCode(BufferedImage image, String code, int expireIn) {
//		this.image = image;
//		this.code = code;
//		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
//	}
//}