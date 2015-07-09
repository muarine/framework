package com.muarine.common.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * User.
 * 
 * @author Muarine maoyun@rtmap.com
 * @since 0.1
 */
public class User implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7055618891413145479L;

	/**
	 * 主键ID
	 */
	private Long id;
	/**
	 * 真是姓名
	 */
	private String realname;
	/**
	 * 登录名
	 */
	private String username;
	/**
	 * 登录名
	 */
	private String mobile;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	

}