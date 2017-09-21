package org.smart4j.framework.helper;

import java.lang.reflect.Field;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.util.ReflectionUtil;


/**
 * 依赖注入助手类
 * @author Administrator
 *
 */
public class IocHelper {
	
	static {
		Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
		if (MapUtils.isNotEmpty(beanMap)) {
			for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
				//从BeanMap中获取Bean类与Bean实例
				Class<?> beanClass = beanEntry.getKey();
				Object beanInstance = beanEntry.getValue();
				//获取Bean类定义的所有成员变量
				Field[] beanFields = beanClass.getDeclaredFields();
				if (ArrayUtils.isNotEmpty(beanFields)) {
					
					for (Field beanField : beanFields) {
						//判断当前字段是否带有Inject注解
						if (beanField.isAnnotationPresent(Inject.class)) {
							Class<?> beanFieldClass = beanField.getType();
							Object beanFieldInstance = beanMap.get(beanFieldClass);
							if (beanFieldInstance != null) {
								ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
							}
						}
					}
					
				}
				
				
			}
		}
	}
	
}
