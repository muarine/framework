/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2015 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.common.exception;

/**
 * ResultException.		返回异常
 * 
 * @author Muarine maoyun@rtmap.com
 * @date 2015年6月30日
 * @since 2.0
 */
public class ResultException extends Exception{


	   /** The serialVersionUID */
	private static final long serialVersionUID = 6683319324664455117L;
	private String code;
	   
   public ResultException(String code)
   {
      super(code);
      this.code=code;
   }

   public String getCode()
   {
      return code;
   }
	
}
