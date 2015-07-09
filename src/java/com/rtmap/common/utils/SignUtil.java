package com.rtmap.common.utils;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.rtmap.common.vo.Parameter;

/**
 * Created by liyan on 14-12-15.
 */
public class SignUtil {

	private static String key = "Rtmap2015";

	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String SHA1(String str) {
		StringBuffer sb = new StringBuffer();
		MessageDigest md;
		try {

			md = MessageDigest.getInstance("sha-1");
			md.update(str.getBytes("UTF-8"));

			byte[] result = md.digest();

			for (byte b : result) {
				int i = b & 0xff;
				if (i < 0xf) {
					sb.append(0);
				}
				sb.append(Integer.toHexString(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String getSign(Map<String, String> paramsMap) {
		String sign = "";
		if (paramsMap == null || paramsMap.isEmpty()) {
			return null;
		}
		paramsMap.remove("Sign");
		Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());
		sortMap.putAll(paramsMap);
		String entryValue = null;
		for (Map.Entry<String, String> entry : sortMap.entrySet()) {
			entryValue = entry.getValue();
			if(entryValue != null && !entryValue.trim().equals("")){
				sign += entry.getKey() + "_" + entryValue+"_"; 
			}
        }
		sign += "Rtmap2015";
		sign=sign.toUpperCase();
		sign = MD5(sign);
		return sign;
	}
	
	public static String getSign(Parameter o) throws Exception{
		ArrayList<String> list = new ArrayList<String>();
        Class<?> cls = o.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            if (f.get(o) != null && f.get(o) != "" && !f.getName().equals("Sign")) {
                list.add(f.getName() + "=" + f.get(o) + "&");
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key=" + key;
        result = MD5Utils.getMD5String(result).toUpperCase();
        return result;
	}

	/**
	 * 获取request对象中key和value,利用反射赋值给指定的对象
	 * 
	 * @param parameterMap
	 * @throws Exception 
	 */
	public static Parameter paraseMapToParameter(Map<String, String[]> parameterMap) throws Exception {
		Parameter para = new Parameter();
		Iterator<Entry<String,String[]>> iterators = parameterMap.entrySet().iterator();
		Class<?> clazz = para.getClass();
		Field[] fields = clazz.getDeclaredFields();
		while (iterators.hasNext()) {
			Entry<String, String[]> entry = iterators.next();
			String key = entry.getKey();
			String value = entry.getValue()[0];
			for (Field f : fields) {
				f.setAccessible(true);
				if(f.getName().equals(key)){
					if(f.getType() == int.class){
						f.setInt(para, Integer.valueOf(value));
					}else if(f.getType() == long.class){
						f.setLong(para, Long.valueOf(value));
					}else if(f.getType() == double.class){
						f.setDouble(para, Double.valueOf(value));
					}else{
						f.set(para, value);
					}
				}
			}
		}
		
		return para;
	}

	/**
	 * FIXME Comment this
	 * 
	 * @param parameterMap
	 */
	public static Map<String, String> parameterstoMap(Map<String, String[]> parameterMap) {
		Map<String,String> map = new HashMap<String, String>();
		Iterator<Entry<String,String[]>> iterators = parameterMap.entrySet().iterator();
		while (iterators.hasNext()) {
			Entry<String, String[]> entry = iterators.next();
			String key = entry.getKey();
			String value = entry.getValue()[0];
			map.put(key, value);
		}
		
		return map;
	}

}

