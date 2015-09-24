package com.muarine.common.vo;

import com.muarine.common.entity.BaseEntity;

/**
 * 
 * UserRole. 用户角色中间表
 * 
 * @author Muarine maoyun@rtmap.com
 * @date 2015年7月24日
 * @since 2.0
 */
public class UserRole extends BaseEntity{

    private String role_id;
    private String user_id;
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
    
	
}