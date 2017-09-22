package org.smart4j.framework.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Request;

/**
 * 控制器助手类
 * @author Arthur.liang
 *
 */
public class ControllerHelper {
	
	public static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();
	
	static {
		//获取所有的Controller类
		Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
		if (CollectionUtils.isNotEmpty(controllerClassSet)) {
			//遍历这些Controller类
			for (Class<?> controllerClass : controllerClassSet) {
				//获取Controlle类中定义的方法
				Method[] methods = controllerClass.getMethods();
				if (ArrayUtils.isNotEmpty(methods)) {
					//遍历这些方法
					for (Method method : methods) {
						//判断当前方法是否带有Aciton注释
						if (method.isAnnotationPresent(Action.class)) {}
						//从Action注释中获取URL映射规则
						Action action = method.getAnnotation(Action.class);
						String mapping = action.value();
						//验证URL映射规则
						if (mapping.matches("\\w+:/\\w*")) {
							String[] array = mapping.split(":");
							if (ArrayUtils.isNotEmpty(array) && array.length == 2) {
								//获取请求方法与请求路径
								String requestMethod = array[0];
								String requestPatn = array[1];
								Request request = new Request(requestMethod, requestPatn);
								Handler handler = new Handler(Controller.class, method);
								//初始化Aciton Map
								ACTION_MAP.put(request, handler);
							}
						}
					}
				}
			}
		}
		
		
	}
	
}
