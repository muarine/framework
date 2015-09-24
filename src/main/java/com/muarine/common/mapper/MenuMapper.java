package com.muarine.common.mapper;

import java.util.List;

import com.muarine.common.entity.Menu;


public interface MenuMapper {
    
	Menu selectById(Long id);
	List<String> selectMenusByRoleId(Long role_id);
	
	Long insert(Menu menu);
	
	int update(Menu menu);
	
	Menu selectByHref(String href);
	
	Menu selectByAction(String action);
	/**
	 * FIXME Comment this
	 * 
	 */
	void updateBFThread();
}