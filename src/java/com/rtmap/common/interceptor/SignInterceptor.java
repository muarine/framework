/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2015 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.common.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.rtmap.common.utils.SignUtil;

/**
 * SignInterceptor.
 * 
 * @author Muarine maoyun@rtmap.com
 * @date 2015年7月6日
 * @since 2.0
 */
public class SignInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Map<String,String> map = SignUtil.parameterstoMap(request.getParameterMap());
		String sign = map.get("Sign");
		String parseSign = SignUtil.getSign(map);
		if(null == sign || !sign.equals(parseSign)){
			String path = request.getContextPath();
			response.sendRedirect(path + "/rest/signerror");
			return false;
		}else{
			return true;
		}
	}
	
	
	
	
}
