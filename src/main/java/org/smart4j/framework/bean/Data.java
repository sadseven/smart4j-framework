package org.smart4j.framework.bean;

/**
 * 返回数据对象
 * @author Arthur.liang
 *
 */
public class Data {
	
	/**
	 * 模型数据
	 */
	private Object model;
	
	public Data(Object model) {
		this.model = model;
	}
	
	public Object getModel() {
		return model;
	}
}
