/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2015 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.muarine.common.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * BaseController.
 * 
 * @author Muarine maoyun@rtmap.com
 * @date 2015年6月30日
 * @since 2.0
 */
public class BaseController extends AbstractJsonpResponseBodyAdvice{
	 
	protected Logger log = LoggerFactory.getLogger(BaseController.class);
	
	/**
	 * Create a new BaseController.
	 * 
	 * @param queryParamNames
	 */
	public BaseController(String... queryParamNames) {
		super("callback");
	}
	
	/**
	 * 全局返回码
	 */
	protected Map<String,String> codeMap ;
	/**
	 * 全局常量Map
	 */
	protected Map<String,String> commonMap ;
	
	
	
	
	
	
	
	
	public Map<String, String> getCodeMap() {
		return codeMap;
	}
	public void setCodeMap(Map<String, String> codeMap) {
		this.codeMap = codeMap;
	}
	public Map<String, String> getCommonMap() {
		return commonMap;
	}
	public void setCommonMap(Map<String, String> commonMap) {
		this.commonMap = commonMap;
	}
	
}
