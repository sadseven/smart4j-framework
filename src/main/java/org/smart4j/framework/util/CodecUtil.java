package org.smart4j.framework.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 编码工具类
 * @author Arthur.liang
 *
 */
public final class CodecUtil {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CodecUtil.class);
	
	/**
	 * 将URL编码
	 * @param source
	 * @return
	 */
	public static String encodeURL(String source) {
		
		String target = null;
		
		try {
			URLEncoder.encode(source, "UTF-8");
		} catch (Exception e) {
			LOGGER.error("encode url failure",e);
			throw new RuntimeException(e);
		}
		return target;
	}
	
	/**
	 * 将URL解码
	 * @param source
	 * @return
	 */
	public static String decodeURL(String source) {
		String target = null;
		try {
			URLDecoder.decode(source, "UTF-8");
		} catch (Exception e) {
			LOGGER.error("decode url failure",e);
			throw new RuntimeException(e);
		}
		return target;
	}
	
}
