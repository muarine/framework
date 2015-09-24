package com.muarine.common.mapper;

import com.muarine.common.entity.Role;

public interface RoleMapper {
    
	Role selectById(Long id);
	
	Long insert(Role role);
	
	int update(Role role);
}