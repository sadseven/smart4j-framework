package org.smart4j.framework.util;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类操作工具类
 * @author Arthur.liang
 *
 */
public final class ClassUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);	
	
	/**
	 * 获取类加载器
	 * @return
	 */
	public static ClassLoader getClassLoader() {
	
		return Thread.currentThread().getContextClassLoader();
		
	}
	
	/**
	 * 加载类
	 * @param className
	 * @param isInitialized
	 * @return
	 */
	public static Class<?> loadClass(String className, boolean isInitialized){

		Class<?> cls;
		try {
			cls = Class.forName(className, isInitialized, getClassLoader());
		} catch (ClassNotFoundException e) {
			LOGGER.error("load class failure", e);
			throw new RuntimeException();
		}
		return cls;
	}
	
	/**
	 * 获取制定包名下的所有类
	 * @param packageName
	 * @return
	 */
	public static Set<Class<?>> getClassSet(String packageName){
		return null;
		
	}
	
}
