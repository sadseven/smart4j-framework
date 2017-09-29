package org.smart4j.framework.bean;

import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

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
	
		
		public boolean isEmpty() {
			return MapUtils.isEmpty(paramMap);
		}
	
}
