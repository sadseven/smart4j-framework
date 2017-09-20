package org.smart4j.framework.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * propertis工具类
 * @author Arthur.liang
 *
 */
public class PropsUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);
	
	/**
	 * 加载properties
	 * @param fileName
	 * @return
	 */
	public static Properties loadProps(String fileName) {
		Properties props = null;
		InputStream is = null;
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
			if (is == null) {
				throw new FileNotFoundException(fileName + "file is not found");
			}
			props = new Properties();
			props.load(is);
		} catch (IOException e) {
			LOGGER.error("load properties file failure",e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO: handle exception
					LOGGER.error("close input stream failure",e);
				}
			}
		}
		return props;
	}
	
	/**
	 * 获取字符型属性,默认空字符串
	 * @param props
	 * @param key
	 * @return
	 */
	public static String getString(Properties props, String key) {
		return getString(props, key, "");
	}
	
	/**
	 * 获取字符型属性,指定key
	 * @param props
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getString(Properties props, String key, String defaultValue) {
		String value = defaultValue;
		if(props.containsKey(key)) {
			value = props.getProperty(key);
		}
		return value;
	}
	
}
