/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2015 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.muarine.common.aop;

import java.lang.reflect.Method;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.muarine.common.constants.Code;
import com.muarine.common.service.MonitorService;
import com.muarine.common.vo.MonitorLog;
import com.muarine.common.vo.Result;
@Aspect
@Component
public class ApiAspect {
	
	private static final Logger log = LoggerFactory.getLogger(ApiAspect.class);
	
	@Autowired
	private MonitorService monitorService;
	
	private Map<String,String> codeMap;
	
	public void setCodeMap(Map<String, String> codeMap) {
		this.codeMap = codeMap;
	}

	/**
	 * 
	 * Pointcut to execute on all the methods of classes in a package
	 * 扫描并监控制定package下的Controller、method
	 *
	 */
    @Pointcut("execution(public * com.rtmap.*.controller..*.*(..))")
    public void allMethodsPointcut(){}
	
	/**
	 * 
	 * 表达式Around
	 * @throws Throwable 
	 *
	 */
	@Around("allMethodsPointcut()")
	public Object aroundAllMethod(ProceedingJoinPoint pjp) throws Throwable{
		
		// start stopwatch
        Object retVal = null;
        Long start = System.currentTimeMillis();
        MonitorLog monitorLog = new MonitorLog();
        this.injectMonitorLog(monitorLog,pjp);
        try {
        	retVal = pjp.proceed();
        	
			Long running = System.currentTimeMillis() - start;
			monitorLog.setRunning(running);
			monitorLog.setIp("null");
			monitorService.insertLogApi(monitorLog);
        } catch (Exception e) {
        	monitorLog.setMessage(e.getMessage());
        	// 记录异常
        	monitorService.insertError(monitorLog);
        	// 监控异常,发送邮件
        	this.monitorService.monitorError(monitorLog);
        	if(retVal instanceof Result){
	        	// 返回结果
	        	Result result = new Result();
	        	log.error(e.getMessage(),e);
	    		result.setErrcode(Code.ERROR);
	    		result.setErrmsg(codeMap.get(Code.ERROR));
	    		return result;
        	}
        	// 需继续往springmvc框架上抛,走异常处理流程,否则出白页
        	throw new Exception(e);
        }
        // stop stopwatch
        return retVal;
	}

	/**
	 * 注入监控的日志信息
	 * 
	 * @param log2
	 * @param pjp
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	private void injectMonitorLog(MonitorLog monitorLog, ProceedingJoinPoint pjp) throws NoSuchMethodException, SecurityException {
		Class<?> clazz = pjp.getTarget().getClass();
		String classAnnonation = clazz.getAnnotation(RequestMapping.class).value()[0];
    	String method = pjp.getSignature().getName();
    	Method[] methods = clazz.getDeclaredMethods();
    	Method m = null;
    	for (Method method2 : methods) {
			if(method2.getName().equals(method)){
				m = method2;
				break;
			}
		}
    	String methodAnnonation = m.getAnnotation(RequestMapping.class).value()[0];
    	String action = pjp.getTarget().getClass().getSimpleName();
    	monitorLog.setParam(pjp.getArgs());		// 此处的Args[]参数，相对于访问method(xxx xxx)，并非请求的参数列表
		monitorLog.setAction(action);
		monitorLog.setMethod(method);
		monitorLog.setApi(classAnnonation+methodAnnonation);
	}
	
	/**
	 * 
	 * 抛异常后处理
	 * 
	 * @param joinPoint
	 * @param e
	 */
//	@AfterThrowing(pointcut = "allMethodsPointcut()", throwing = "e")
//	public void AfterThrowing(JoinPoint joinPoint, Exception e) {
//		System.out.println("AfterThrowing 监控到异常");
//	}
	
}
