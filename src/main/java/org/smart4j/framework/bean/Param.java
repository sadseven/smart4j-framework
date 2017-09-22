package org.smart4j.framework.bean;

import java.util.Map;

/**
 * 请求参数对象
 * @author Arthur.liang
 *
 */
public class Param {

		private Map<String, Object> paramMap;
		
		public Param(Map<String, Object> paramMap) {
			this.paramMap = paramMap;
		}
		
		
		/**
		 * 获取所有字段信息
		 * @return
		 */
		public Map<String, Object> getMap(){
			return paramMap;
		}
	
}
