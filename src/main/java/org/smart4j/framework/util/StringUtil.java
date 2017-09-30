package org.smart4j.framework.util;

/**
 * 字符串工具类
 * @author Arthur.liang
 *
 */
public class StringUtil {
	
	/**
	 * 字符串分隔符
	 */
	public static final String SEPARATOR = String.valueOf((char) 29);
	
	/**
	 * 判断字符串是否为空
	 */
	public static boolean isEmpty(String string) {
		String str = string.toString();
		if (str != null && str.length() > 0) {
			return false;
		}
		return true;
	} 
	
	/**
	 * 判断字符串是否为不空
	 * @param string
	 * @return
	 */
	public static boolean isNotEmpty(String string) {
		return !isEmpty(string);
	} 
	
}
