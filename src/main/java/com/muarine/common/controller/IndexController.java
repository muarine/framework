/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2015 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.muarine.common.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.muarine.common.constants.Code;
import com.muarine.common.vo.Result;

/**
 * IndexController.
 * 
 * @author Muarine maoyun@rtmap.com
 * @date 2015年7月6日
 * @since 2.0
 */
@ControllerAdvice
@RestController
@RequestMapping("/")
public class IndexController extends BaseController{
	
	@RequestMapping(value = "/signerror")
	public Result signError(){
		Result result = new Result();
		result.setErrcode(Code.ERROR_SIGN);
		result.setErrmsg(codeMap.get(Code.ERROR_SIGN));
		return result;
	}
	
	
}
