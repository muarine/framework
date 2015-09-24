/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2015 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.muarine.common.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muarine.common.entity.LogApi;
import com.muarine.common.entity.LogError;
import com.muarine.common.mapper.LogApiMapper;
import com.muarine.common.mapper.LogErrorMapper;
import com.muarine.common.vo.MonitorLog;

/**
 * MonitorService.	日志、异常监控逻辑类
 * 
 * @author Muarine maoyun@rtmap.com
 * @date 2015年8月17日
 * @since 2.0
 */
@Service
public class MonitorService {
	
	@Autowired
	private LogApiMapper logApiMapper;
	@Autowired
	private LogErrorMapper logErrorMapper;
	@Autowired
	private TemplateMail templateMail;
	
	private MemCacheService memCacheService;
	
	public void setMemCacheService(MemCacheService memCacheService) {
		this.memCacheService = memCacheService;
	}

	// TODO 日志记录
	/**
	 * 
	 * 日志记录
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 *
	 */
	public void insertLogApi(MonitorLog log) throws NoSuchMethodException, SecurityException{
        
		LogApi logApi = new LogApi();
		logApi.setAction(log.getAction());
		logApi.setApi(log.getApi());
		logApi.setCreate_time(new Date());
		logApi.setMethod(log.getMethod());
		logApi.setRunning_time(log.getRunning());
		logApi.setIp(log.getIp());
		logApiMapper.insert(logApi);
		
	}
	
	/**
	 * 
	 * 错误日志监控
	 * @return 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws UnsupportedEncodingException 
	 *
	 */
	public void insertError(MonitorLog log) throws NoSuchMethodException, SecurityException, UnsupportedEncodingException{
		
		LogError logError = new LogError();
		logError.setApi(log.getApi());
		logError.setCreate_time(new Date());
		logError.setMessage(URLEncoder.encode(log.getMessage(),"UTF-8"));
		logErrorMapper.insert(logError);
		
	}
	
	/**
	 * 
	 * 监控错误日志,超过20次则发送邮件
	 *
	 */
	public void monitorError(MonitorLog log){
		
		Integer num = this.memCacheService.getErrorLog(log.getApi());
		
		if(num > 20){
			// TODO 同一api在十分钟内错误超过10次则发送邮件
			Map<String,Object> root = new HashMap<String,Object>();  
	        root.put("errormsg", log.getMessage());
	        root.put("api", log.getApi());
	        root.put("className", log.getAction());
	        root.put("methodName", log.getMethod());
	        templateMail.sendTemplateMail(root, 
	        				new String[]{
	        								"maoyun@rtmap.com",
	        								"zhaijia@rtmap.com",
	        								"wangxin@rtmap.com",
	        								"hexuan@rtmap.com",
	        							},
	        				"[智慧图公众号第三方平台]-系统监控到异常:" + log.getAction() + "-" + log.getMethod(),
	        				"system_mail.ftl");
	        
			this.memCacheService.setErrorLog(log.getApi() , 0);
		}else{
			this.memCacheService.setErrorLog(log.getApi() , num+1);
		}
		
	}
	
}
