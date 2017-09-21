package org.smart4j.framework.helper;

import java.util.HashSet;
import java.util.Set;

import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.util.ClassUtil;

/**
 * 类操作助手
 * @author arthur.liang 
 *
 */
public class ClassHelper {
	/**
	 * 存放所有所加载的类
	 */
	private static final Set<Class<?>> CLASS_SET;
	
	static {
		String basePackage = ConfigHelper.getAppBasePackage();
		CLASS_SET = ClassUtil.getClassSet(basePackage);
	}
	
	/**
	 * 获取所有加载类
	 * @return
	 */
	public static Set<Class<?>> getClssSet(){
		return CLASS_SET;
	}
	
	/**
	 * 获取所有Service注解类
	 * @return
	 */
	public static Set<Class<?>> getServiceClassSet(){
		HashSet<Class<?>> classSet = new HashSet<Class<?>>();
		for (Class<?> cls : CLASS_SET) {
			if (cls.isAnnotationPresent(Service.class)) {
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
	/**
	 * 获取所有Controller注解类
	 * @return
	 */
	public static Set<Class<?>> getControllerClassSet(){
		HashSet<Class<?>> classSet = new HashSet<Class<?>>();
		for (Class<?> cls : CLASS_SET) {
			if (cls.isAnnotationPresent(Controller.class)) {
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
	/**
	 * 获取所有Action注解类
	 * @return
	 */
	public static Set<Class<?>> getActionClassSet(){
		HashSet<Class<?>> classSet = new HashSet<Class<?>>();
		for (Class<?> cls : CLASS_SET) {
			if (cls.isAnnotationPresent(Action.class)) {
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
	/**
	 * 获取所有Inject注解类
	 * @return
	 */
	public static Set<Class<?>> getInjectClassSet(){
		HashSet<Class<?>> classSet = new HashSet<Class<?>>();
		for (Class<?> cls : CLASS_SET) {
			if (cls.isAnnotationPresent(Inject.class)) {
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
	
	/**
	 * 获取应用包名下所有bean类
	 * @return
	 */
	public static Set<Class<?>> getBeanClassSet(){
		HashSet<Class<?>> beanClassSet = new HashSet<Class<?>>();
		beanClassSet.addAll(getServiceClassSet());
		beanClassSet.addAll(getControllerClassSet());
		return beanClassSet;
	}
}
