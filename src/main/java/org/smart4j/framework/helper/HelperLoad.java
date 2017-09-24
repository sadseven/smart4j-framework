package org.smart4j.framework.helper;

import org.smart4j.framework.util.ClassUtil;

public class HelperLoad {

	public	static void init() {
		Class<?>[] classList = {
				ClassHelper.class,
				BeanHelper.class,
				IocHelper.class,
				ControllerHelper.class
		};
		for (Class<?> cls : classList) {
			ClassUtil.loadClass(cls.getName(),false);
		}
	}
	
	
}