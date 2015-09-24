package com.muarine.common.mapper;

import java.util.List;

import com.muarine.common.entity.LogError;


public interface LogErrorMapper {
    
	Long insert(LogError menu);
	
	List<LogError> select();
	
}