/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2015 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.common.vo;

import java.io.Serializable;

/**
 * Parameter. 请求参数
 * 
 * @author Muarine maoyun@rtmap.com
 * @date 2015年6月30日
 * @since 0.1
 */
public class Parameter implements Serializable{
	
	/** The serialVersionUID */
	private static final long serialVersionUID = -1461827728646896339L;

	private long memberSid;
	private long shopId;
	private long marketId;
	private long userId;
	private long prizeId;
	private int limit = 20;
	private int page = 0;
	/**
	 * 商户账户相关 / 支付宝账户
	 */
	private String realName;
	private String alipayCount;
	private double money;
	
	private int type;
	/**
	 * 核销    操作方式
	 */
	private int operateType;
	/**
	 * 二维码
	 */
	private String qrCode;
	
	/**
	 * 用户名或者手机号
	 */
	private String username;	
	
	private String password;
	private String newPassword;
	/**
	 * 签名
	 */
	private String Sign;
	/**
	 * 会员登录token
	 */
	private String token;

	public long getMemberSid() {
		return memberSid;
	}

	public void setMemberSid(long memberSid) {
		this.memberSid = memberSid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

	public long getMarketId() {
		return marketId;
	}

	public void setMarketId(long marketId) {
		this.marketId = marketId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getOperateType() {
		return operateType;
	}

	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public long getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(long prizeId) {
		this.prizeId = prizeId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getAlipayCount() {
		return alipayCount;
	}

	public void setAlipayCount(String alipayCount) {
		this.alipayCount = alipayCount;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getSign() {
		return Sign;
	}

	public void setSign(String sign) {
		Sign = sign;
	}

	@Override
	public String toString() {
		return "Parameter [memberSid=" + memberSid + ", shopId=" + shopId
				+ ", marketId=" + marketId + ", userId=" + userId
				+ ", prizeId=" + prizeId + ", limit=" + limit + ", page="
				+ page + ", realName=" + realName + ", alipayCount="
				+ alipayCount + ", money=" + money + ", type=" + type
				+ ", operateType=" + operateType + ", qrCode=" + qrCode
				+ ", username=" + username + ", password=" + password
				+ ", newPassword=" + newPassword + ", Sign=" + Sign
				+ ", token=" + token + "]";
	}

}
