package com.muarine.common.vo;


public class Result {

	/**
	 * 
	 */
	private String rstcode;//请求成功200  请求失败400 签名验证失败300
	private String rsttext;
	private Object obj;
	private int total = 0;
	
	public String getRstcode() {
		return rstcode;
	}
	public void setRstcode(String rstcode) {
		this.rstcode = rstcode;
	}
	public String getRsttext() {
		return rsttext;
	}
	public void setRsttext(String rsttext) {
		this.rsttext = rsttext;
	}
	/**
	 * @return the obj
	 */
	public Object getObj() {
		return obj;
	}
	/**
	 * @param obj the obj to set
	 */
	public void setObj(Object obj) {
		this.obj = obj;
	}
	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	
}
