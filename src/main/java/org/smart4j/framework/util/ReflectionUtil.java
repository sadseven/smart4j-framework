package org.smart4j.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 反射工具类
 * @author Arthur.liang
 *
 */
public class ReflectionUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);
	
	
	/**
	 * 创建实例
	 * @param cls
	 * @return
	 */
	public static Object getInstance(Class<?> cls) {
		Object newInstance;
		try {
			newInstance = cls.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			LOGGER.error("new instance failure", e);
			throw new RuntimeException();
		}
		return newInstance;
	}
	
	/**
	 * 调用方法
	 * @param object
	 * @param method
	 * @param args
	 * @return
	 */
	public static Object invokeMethod(Object object, Method method, Object...args) {
		Object result;
		method.setAccessible(true);
		try {
			result = method.invoke(object, args);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			LOGGER.error("invoke method failure", e);
			throw new RuntimeException();
		}
		return result;
	}
	
	/**
	 * 变量设置值
	 * @param object
	 * @param field
	 * @param value
	 */
	public static void setField(Object object, Field field, Object value) {
		field.setAccessible(true);
		try {
			field.set(object, value);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			LOGGER.error("set field failure", e);
			throw new RuntimeException();
		}
		
	}
}
