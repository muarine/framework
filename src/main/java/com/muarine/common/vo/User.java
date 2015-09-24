package com.muarine.common.vo;

import com.muarine.common.entity.BaseEntity;

/**
 * 
 * User. 系统用户
 * 
 * @author Muarine maoyun@rtmap.com
 * @date 2015年7月24日
 * @since 2.0
 */
public class User extends BaseEntity{
	
	private Long role_id;
    private String nickname;
    private String username;
    private String password;
    private String salting;
    private String access_token;
    
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	public String getSalting() {
		return salting;
	}
	public void setSalting(String salting) {
		this.salting = salting;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public Long getRole_id() {
		return role_id;
	}
	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}
	
}