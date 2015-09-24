/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2015 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.muarine.common.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.transcoders.IntegerTranscoder;

/**
 * MemCacheService.	获取缓存逻辑类
 * 
 * @author Muarine maoyun@rtmap.com
 * @date 2015年7月24日
 * @since 2.0
 */
@Service
public class MemCacheService {
	
//	private Logger logger = LoggerFactory.getLogger(MemCacheService.class);
	
	
	private MemcachedClient memcachedClient;
	
	private Map<String,String> commonMap;
	
	public MemcachedClient getMemcachedClient() {
		return memcachedClient;
	}

	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

	public Map<String, String> getCommonMap() {
		return commonMap;
	}

	public void setCommonMap(Map<String, String> commonMap) {
		this.commonMap = commonMap;
	}
	
	/**
	 * 获取异常记录次数
	 * 
	 * @param api
	 */
	public Integer getErrorLog(String api) {
		Integer num = memcachedClient.get("Exception_Url_" + api , new IntegerTranscoder());
		return  num == null ? 0 : num;
	}
	
	/**
	 * 
	 * 注入新的异常记录次数
	 * 
	 * @param api
	 */
	public void setErrorLog(String api , Integer num){
		memcachedClient.set("Exception_Url_"+api , 600 , num);
		
	}

}
