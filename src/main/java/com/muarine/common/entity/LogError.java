package com.muarine.common.entity;

/**
 * 
 * LogError. 系统异常监控日志
 * 
 * @author Muarine maoyun@rtmap.com
 * @date 2015年7月24日
 * @since 2.0
 */
public class LogError extends BaseEntity{

    private String name;
    private String api;
    private String message;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getApi() {
		return api;
	}
	public void setApi(String api) {
		this.api = api;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}