package com.muarine.common.mapper;

import java.util.List;

import com.muarine.common.entity.UserRole;


public interface UserRoleMapper {
    
	List<UserRole> selectByRoleId(Long role_id);
	List<UserRole> selectByUserId(Long user_id);
	
	int deleteByRoleId(Long role_id);
	int deleteByUserId(Long user_id);
	
	Long insert(UserRole role);
}