package com.muarine.common.mapper;

import java.util.List;

import com.muarine.common.entity.RoleMenu;


public interface RoleMenuMapper {
    
	List<RoleMenu> selectByRoleId(Long role_id);
	
	List<RoleMenu> selectByMenuId(Long menu_id);
	
	Long insert(RoleMenu role);
	
	Integer deleteByRoleId(Long role_id);

	/**
	 * 批量插入角色-权限 关联关系
	 * 
	 * @param menus
	 */
	void insertBatchRecord(List<RoleMenu> menus);
}