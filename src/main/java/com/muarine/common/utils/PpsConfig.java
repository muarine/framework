package com.muarine.common.utils;

import java.io.IOException;
import java.util.Properties;

public class PpsConfig {
	
	private static Properties properties = new Properties();
	//配置文件名称
	private static final String conifgName = "/app.properties";
	private static PpsConfig instance;

	/**
	 * 构造方法 加载配置文件
	 */
	private PpsConfig() {
		try {
			properties.load(getClass().getResourceAsStream(conifgName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取单例对象
	 * @return EmpConfig
	 */
	private synchronized static PpsConfig getInstance() {
		if (null == instance) {
			instance = new PpsConfig();
		}
		return instance;
	}
	
	/**
	 * 将属性值获取为int型
	 * @param str 属性名
	 * @return
	 */
	public static int getint( String str){
		try {
			if (null == instance) {
				getInstance();
			}
			return Integer.parseInt(properties.getProperty( str ));
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 将属性值获取为long型
	 * @param str 属性名
	 * @return
	 */
	public static long getlong( String str){
		try {
			if (null == instance) {
				getInstance();
			}
			return Long.parseLong( properties.getProperty( str ) );
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 将属性值获取为double型
	 * @param str 属性名
	 * @return
	 */
	public static double getdouble( String str){
		try {
			if (null == instance) {
				getInstance();
			}
			return Double.parseDouble(properties.getProperty( str ));
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 将属性值获取为String型
	 * @param str 属性名
	 * @return
	 */
	public static String getString( String str){
		try {
			if (null == instance) {
				getInstance();
			}
			return properties.getProperty( str );
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 将属性值获取为boolean型
	 * @param str 属性名
	 * @return
	 */
	public static boolean getBoolean( String str){
		try {
			if (null == instance) {
				getInstance();
			}
			return Boolean.parseBoolean( properties.getProperty( str ));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
