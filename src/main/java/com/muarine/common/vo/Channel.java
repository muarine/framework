/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2015 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.muarine.common.vo;


/**
 * Channel.  权限检测渠道
 * 
 * @author Muarine maoyun@rtmap.com
 * @date 2015年8月14日
 * @since 2.0
 */
public class Channel {
	
	/**
	 * 权限检测token
	 */
	private String accessToken;
	/**
	 * 菜单名称
	 */
	private String moduleName;
	/**
	 * 菜单名称
	 */
	private String menuName;
	/**
	 * 访问Action+method路径
	 */
	private String href;
	/**
	 * 模块
	 */
	private String action;
	/**
	 * 函数
	 */
	private String method;
	/**
	 * 菜单显示		1. 显示  2.不显示
	 */
	private Integer show;
	/**
	 * 菜单排序
	 */
	private Integer sort;
	/**
	 * 菜单备注
	 */
	private String memo;
	
	
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Integer getShow() {
		return show;
	}
	public void setShow(Integer show) {
		this.show = show;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
}
