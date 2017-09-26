package org.smart4j.framework.aspect;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Aspect;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.proxy.AspectProxy;

/**
 * 拦截所有Controller
 * @author Administrator
 *
 */
@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy{

	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);
	
	private long begin;
	
	@Override
	public void before(Class<?> cls, Method method, Object[] params) {
		// TODO Auto-generated method stub
		LOGGER.debug("------------begin--------------");
		LOGGER.debug(String.format("class: %s", cls.getName()));
		LOGGER.debug(String.format("method: %s", method.getName()));
		begin = System.currentTimeMillis();
		
	}
	
	@Override
	public void after(Class<?> cls, Method method, Object[] params, Object result) {
		// TODO Auto-generated method stub
		LOGGER.debug(String.format("time: %dms", System.currentTimeMillis() - begin));
		LOGGER.debug("-------------end-----------------");
	}
	
}