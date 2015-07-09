package com.rtmap.common.utils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rtmap.common.constants.Code;
import com.rtmap.common.exception.ResultException;


public class StringUtils{
	
	public static boolean hasSpecialCharacters(String str){
		
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);

		return m.find();
	}
	/**
	* @Description: TODO 检测字符串是否为空
	* @return boolean
	 */
	public static boolean isNotBlank(String... strs){
		boolean b = true;
		
		for (int i = 0; i < strs.length; i++) {
			if (org.apache.commons.lang.StringUtils.isBlank(strs[i])) {
				b = false;
				break;
			}
		}
		
		return b;
	}
	
	/**
	 * 此方法只适用于对String、Integer、Long<BR/>
	 * 1. Integer null/<=0  <BR/>
	 * 2. Long    null/<=0  <BR/>
	 * 3. String  null/"" <BR/>
	 * @throws ResultException 
	 */
	public static void isNotEmpty(Object... obj) throws ResultException{
		for (int i = 0; i < obj.length; i++) {
			if (obj[i]==null) {
				throw new ResultException(Code.NULL_PARAM);
			}
			if(obj[i] instanceof String && obj[i].equals("")){
				throw new ResultException(Code.NULL_PARAM);
			}else if(obj[i] instanceof Integer && Integer.valueOf(obj[i].toString()) <= 0){
				throw new ResultException(Code.NULL_PARAM);
			}else if(obj[i] instanceof Long && Long.valueOf(obj[i].toString()) <= 0L){
				throw new ResultException(Code.NULL_PARAM);
			}
		}
	}
	
	/**
	 * 判断字符串是否为超链接
	 */
	public static boolean isUrl(String str){
		Pattern pattern=Pattern.compile("http://(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*");
		Matcher matcher=pattern.matcher(str);
		return matcher.find();
	}
	/**
	 * 生成邀请码
	 * 
	 * @param rankingNum 排名
	 *            rankingNum
	 * @return String
	 */
	public static String getInviteCode(long rankingNum) {
		StringBuffer sb = new StringBuffer();
		String endStr = Long.toHexString(rankingNum + 1000).toUpperCase();
		int tempLength = 6;
		if (endStr.length() < tempLength) {
			int forC = tempLength - endStr.length();
			for (int i = 0; i < forC; i++) {
//				sb.append('0');
				sb.append(new Random().nextInt(10));
			}
		}
		sb.append(endStr);
		return sb.toString();
	}
	public static void main(String[] args){
		for (int i = 10000000; i < 10001000; i++) {
			System.out.println(getInviteCode(i));
		}
//		String str = "http://www.sina.com.cn";
//		System.out.println(hasSpecialCharacters(str));
//		System.out.println(new Float("100.1")%100==new Float(0));
	}

	/** 
	* @Description: TODO 根据商户ID生成三位数
	* @return
	* @return String    
	*/
	public static String genertorShopCode(String shopId) {
		if(null == shopId || shopId.trim().equals("")){
			return "";
		}
		int len = shopId.length();
		StringBuffer sb = new StringBuffer("P");
		switch (len) {
		case 1:
			sb.append("00").append(shopId);
			break;
		case 2:
			sb.append("0").append(shopId);
			break;
		case 3:
			sb.append(shopId);
			break;
		default:
			break;
		}
		sb.append("T");
		return sb.toString();
	}
	/** 
	* @Description: TODO 解析URI成Map对象
	* @param content=	identifier=fl3651&rand_code=595632
	* @return void    
	*/
	public static Map<String,String> parseURI(String content) {
		if(null == content || content.trim().equals("")){
			return null;
		}
		Map<String, String> map = new LinkedHashMap<String, String>();
		content = content.replace("?", "");
		String[] pairs = content.split("\\&");
		for (String pair : pairs) {
			int idx = pair.indexOf("=");
			map.put(pair.substring(0, idx), pair.substring(idx + 1));
		}
		
		return map ;
	}
}
