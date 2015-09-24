package com.muarine.common.mapper;

import java.util.List;

import com.muarine.common.entity.LogApi;


public interface LogApiMapper {
    
	List<LogApi> select();
	
	Long insert(LogApi logApi);
	
}