package com.muarine.common.mapper;

import java.util.Map;

import com.muarine.common.entity.User;


public interface UserMapper {
    
	User selectById(Long id);
	User selectByMap(Map<String,Object> map);
	
	Long insert(User role);
	
	int update(User role);
	
}