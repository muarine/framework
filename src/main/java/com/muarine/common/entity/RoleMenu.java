package com.muarine.common.entity;


/**
 * 
 * UserRole. 角色菜单中间表
 * 
 * @author Muarine maoyun@rtmap.com
 * @date 2015年7月24日
 * @since 2.0
 */
public class RoleMenu extends BaseEntity{

    private Long role_id;
    private Long menu_id;
    
	public Long getRole_id() {
		return role_id;
	}
	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}
	public Long getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(Long menu_id) {
		this.menu_id = menu_id;
	}
    
}