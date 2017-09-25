package org.smart4j.framework.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.smart4j.framework.util.ReflectionUtil;

/**
 * Bean助手类
 * @author Arthur.liang
 *
 */
public class BeanHelper {
	
	public static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();
	
	static {
		Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
		for (Class<?> beanClass : beanClassSet) {
			Object instance = ReflectionUtil.getInstance(beanClass);
			BEAN_MAP.put(beanClass, instance);
		}
	}
	
	/**
	 * 获取bean的映射
	 * @return
	 */
	public static Map<Class<?>, Object> getBeanMap(){
		return BEAN_MAP;
	}
	
	/**
	 * 获取实例
	 * @param cls
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> cls) {
		if (!BEAN_MAP.containsKey(cls)) {
			throw new RuntimeException("can not get bean by class: " + cls);
		}
		return (T)BEAN_MAP.get(cls);
	}
	
	/**
	 * 设置Bean实例
	 */
	public static void setBean(Class<?> cls, Object obj) {
		BEAN_MAP.put(cls, obj);
	}
}
