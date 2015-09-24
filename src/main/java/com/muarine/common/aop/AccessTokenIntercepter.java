/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2015 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.muarine.common.aop;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.muarine.common.constants.Code;
import com.muarine.common.exception.ResultException;
import com.muarine.common.service.PermissionService;
import com.muarine.common.utils.StringUtils;
import com.muarine.common.vo.Channel;
import com.muarine.common.vo.Result;

/**
 * AccessTokenIntercepter.
 * 
 * @author Muarine maoyun@rtmap.com
 * @date 2015年8月13日
 * @since 2.0
 */
public class AccessTokenIntercepter extends HandlerInterceptorAdapter {
	
	private Logger log = LoggerFactory.getLogger(AccessTokenIntercepter.class);
	
	private Map<String,String> codeMap;
	private PermissionService permissionService;
	
	public void setCodeMap(Map<String, String> codeMap) {
		this.codeMap = codeMap;
	}

	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	/**
	 * 拦截器
	 * @throws IOException 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws IOException {
		System.out.println("--------------拦截器生效----------------");
		Result result = new Result();
		boolean isSuccess = true;
		if(handler instanceof HandlerMethod){
			// 加了@AccessRequired注解的才进行权限校验
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			AccessRequired annotation = method.getAnnotation(AccessRequired.class);
			if (annotation != null) {
				try {
					String access_token = request.getParameter("access_token");
					StringUtils.isNotEmpty(access_token);
					Channel channel = new Channel();
					this.injectAttr(handlerMethod, method, annotation, channel);
					
					channel.setAccessToken(access_token);
					
					permissionService.checkPermission(channel);
					
					
				} catch (ResultException e) {
					isSuccess = false;
					result.setErrcode(e.getCode());
					result.setErrmsg(codeMap.get(e.getCode()));
				} catch (Exception e) {
					isSuccess = false;
					log.error(e.getMessage(),e);
					result.setErrcode(Code.ERROR);
					result.setErrmsg(codeMap.get(Code.ERROR));
				}
			}
		}
		if(!isSuccess) {
			response.setHeader("Content-type", "application/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(JSON.toJSONString(result));
		}
		return isSuccess;
	}

	/**
	 * 注入属性
	 * 
	 * @param handlerMethod
	 * @param method
	 * @param annotation
	 * @param access_token
	 * @param channel
	 */
	private void injectAttr(HandlerMethod handlerMethod, Method method,
			AccessRequired methodAccess,Channel channel) {
		String actionName = handlerMethod.getBean().getClass().getName().toLowerCase();
		// 类名
		actionName = actionName.substring(actionName.lastIndexOf(".")+1,actionName.length());
		// Method函数名
		String methodName = method.getName().toLowerCase();
		// 类中RequestMapping值
		String actionRequestMapping = method.getDeclaringClass().getAnnotation(RequestMapping.class).value()[0];
		// 类中AccessRequired值
		AccessRequired actionAccess = method.getDeclaringClass().getAnnotation(AccessRequired.class);
		// Method中请求url
		String methodRequestMapping = method.getAnnotation(RequestMapping.class).value()[0];
		System.out.println();
		System.out.println();
		System.out.println(actionRequestMapping);
		System.out.println(methodRequestMapping);
		channel.setMethod(methodName);
		channel.setMemo(methodAccess.memo());
		channel.setMenuName(methodAccess.name());
		channel.setModuleName(actionAccess.name());
		channel.setShow(methodAccess.show());
		channel.setSort(methodAccess.sort());
		channel.setHref(actionName+"/"+methodName);	// 此处不是url地址,而是class/method	形如：admin/index
		channel.setAction(actionName);
	}
	
	/**
	 * 成功响应后调用此函数
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// FIXME implement me
		System.out.println("--------------postHandle----------------");
		super.postHandle(request, response, handler, modelAndView);
	}
	
	/**
	 * 在postHandler函数之后调用
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// FIXME implement me
		System.out.println("--------------afterCompletion----------------");
		super.afterCompletion(request, response, handler, ex);
	}
	
	
	/**
	 * 
	 */
	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// FIXME implement me
		System.out.println("--------------afterConcurrentHandlingStarted----------------");
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

}
