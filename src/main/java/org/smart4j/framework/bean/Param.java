package org.smart4j.framework.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.smart4j.framework.util.CastUtil;
import org.smart4j.framework.util.StringUtil;

import com.mysql.jdbc.ConnectionLifecycleInterceptor;

/**
 * 请求参数对象
 * @author Arthur.liang
 *
 */
public class Param {

		private List<FormParam> formParamList;
		
		private List<FileParam> fileParamList;
	
		
		public Param(List<FormParam> formParamList) {
			this.formParamList = formParamList;
		}
		
		public Param(List<FormParam> formParamList, List<FileParam> fileParamList) {
			super();
			this.formParamList = formParamList;
			this.fileParamList = fileParamList;
		}

		/**
		 * 获取请求参数的映射
		 * @return
		 */
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
		 * 获取上传文件映射
		 * @return
		 */
		public Map<String, List<FileParam>> getFileMap(){
			Map<String, List<FileParam>> fileMap = new HashMap<String, List<FileParam>>();
			if (CollectionUtils.isNotEmpty(fileParamList)) {
				for (FileParam fileParam : fileParamList) {
					String fieldName = fileParam.getFieldName();
					List<FileParam> fileParamList;
					if (fileMap.containsKey(fieldName)) {
						fileParamList = fileMap.get(fieldName);
					} else {
						fileParamList = new ArrayList<FileParam>();
					}
					fileParamList.add(fileParam);
					fileMap.put(fieldName, fileParamList);
				}
			}
			return fileMap;
			
		}

		/**
		 * 获取所有上传文件
		 * @param fileName
		 * @return
		 */
		public List<FileParam> getFileList(String fileName){
			return getFileMap().get(fileName);
		}
		
		/**
		 * 获取唯一上传文件
		 * @param fieldName
		 * @return
		 */
		public FileParam getFile(String fieldName) {
			List<FileParam> fileParamList = getFileList(fieldName);
			if (CollectionUtils.isNotEmpty(fileParamList) && fileParamList.size() == 1) {
				return fileParamList.get(0);
			}
			return null;
		}
		
		/**
		 * 校验参数是否为空
		 * @return
		 */
		public boolean isEmpty() {
			return CollectionUtils.isEmpty(formParamList) && CollectionUtils.isEmpty(fileParamList);
 		}
		
		/**
		 * 根据参数名获取String型参数值
		 * @param name
		 * @return
		 */
		public String getString(String name) {
			return CastUtil.castString(getFieldMap().get(name));
 		}
		
		/**
		 * 根据参数名获取double型参数值
		 * @param name
		 * @return
		 */
		public double getDouble(String name) {
			return CastUtil.castDouble(getFieldMap().get(name));
 		}
		
		/**
		 * 根据参数名获取long型参数值
		 * @param name
		 * @return
		 */
		public long getLong(String name) {
			return CastUtil.castLong(getFieldMap().get(name));
 		}
		
		/**
		 * 根据参数名获取int型参数值
		 * @param name
		 * @return
		 */
		public int getInt(String name) {
			return CastUtil.castInt(getFieldMap().get(name));
 		}
		
		/**
		 * 根据参数名获取Boolean型参数值
		 * @param name
		 * @return
		 */
		public Boolean getBoolean(String name) {
			return CastUtil.castBoolean(getFieldMap().get(name));
 		}
		

}
