package org.smart4j.framework.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.smart4j.framework.util.StringUtil;

/**
 * 请求参数对象
 * @author Arthur.liang
 *
 */
public class Param {

		private List<FormParam> formParamList;
		
		private List<FileParam> fileParamList;
	
		private Map<String, Object> paramMap;
		
		public Param(List<FormParam> formParamList) {
			this.formParamList = formParamList;
		}
		
		public Param(List<FormParam> formParamList, List<FileParam> fileParamList) {
			super();
			this.formParamList = formParamList;
			this.fileParamList = fileParamList;
		}

		public Map<String, Object> getFieldMap(){
			Map<String, Object> fileMap = new HashMap<String, Object>();
			if (CollectionUtils.isNotEmpty(formParamList)) {
				for (FormParam formParam : formParamList) {
					String fieldName = formParam.getFieldName();
					Object fieldValue = formParam.getFieldValue();
					if (fileMap.containsKey(fieldName)) {
						fieldValue = fileMap.get(fieldName) + StringUtil.SEPARATOR + fieldValue;
					}
					fileMap.put(fieldName, fieldValue);
				}
			}
			return fileMap;
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
