/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2015 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.common.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rtmap.common.constants.Code;
import com.rtmap.common.vo.Result;

/**
 * IndexController.
 * 
 * @author Muarine maoyun@rtmap.com
 * @date 2015年7月6日
 * @since 2.0
 */
@RestController
@RequestMapping("/")
public class IndexController extends BaseController{
	
	@RequestMapping(value = "/signerror")
	public Result signError(){
		Result result = new Result();
		result.setRstcode(Code.ERROR_SIGN);
		result.setRsttext(codeMap.get(Code.ERROR_SIGN));
		return result;
	}
	
	
}
