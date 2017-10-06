package org.smart4j.framework.util;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件工具类
 * @author Administrator
 *
 */
public class FileUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
	
	/**
	 * 获取真实文件名(自动去掉路径)
	 * @param fileName
	 * @return
	 */
	public static String getRealFileName(String fileName) {
		return FilenameUtils.getName(fileName);
	}
	
	/**
	 * 创建文件
	 * @param filePath
	 * @return
	 */
	public static File craeteFile(String filePath) {
		File file;
		try {
			file = new File(filePath);
			File parentDir = file.getParentFile();
			if (!parentDir.exists()) {
				FileUtils.forceMkdir(parentDir);
			}
		} catch (Exception e) {
			LOGGER.error("create file failure", e);
			throw new RuntimeException(e);
		}
		
		return file;
	}
	
}
