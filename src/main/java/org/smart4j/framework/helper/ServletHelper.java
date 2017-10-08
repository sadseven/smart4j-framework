package org.smart4j.framework.helper;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet助手类
 * @author Administrator
 *
 */
public final class ServletHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServletHelper.class);
	
	private static final ThreadLocal<ServletHelper> SERVLET_HELPER_HOLDER = new ThreadLocal<ServletHelper>();
	
	private HttpServletRequest request;
	
	private HttpServletResponse response;
	
	private ServletHelper(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	/**
	 * 初始化
	 * @param request
	 * @param response
	 */
	public static void init(HttpServletRequest request, HttpServletResponse response) {
		SERVLET_HELPER_HOLDER.set(new ServletHelper(request, response));
	}
	
	/**
	 * 销毁
	 */
	public static void destory() {
		SERVLET_HELPER_HOLDER.remove();
	}
	
	/**
	 * 获取Request对象
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		return SERVLET_HELPER_HOLDER.get().request;
	}
	
	/**
	 * 获取response对象
	 * @return
	 */
	public static HttpServletResponse getResponse() {
		return SERVLET_HELPER_HOLDER.get().response;
	}
	
	/**
	 * 获取session对象
	 */
	public static HttpSession getSession() {
		return getRequest().getSession();
	}
	
	/**
	 * 获取ServletContext对象
	 * @return
	 */
	public static ServletContext getServletContext() {
		return getRequest().getServletContext();
	}
	
	/**
	 * 将属性放入Request中
	 * @param key
	 * @param value
	 */
	public static void setRequestAttribute(String key, Object value) {
		getRequest().setAttribute(key, value);
	}
	
	/**
	 * 从Request中获取属性
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getRequestAttribute(String key) {
		return (T) getRequest().getAttribute(key);
	}
	
	/**
	 * 从Request中移除属性
	 */
	public static void removeRequestAttribute(String key) {
		getRequest().removeAttribute(key);
	}
	
	
	/**
	 * 发送重定向响应
	 */
	public static void sendRedirect(String localtion) {
		
		try {
			getResponse().sendRedirect(getRequest().getContextPath() + localtion);
		} catch (IOException e) {
			LOGGER.error("redirect failure", e);
		}
		
	}
	
	/**
	 * 将属性放入Session中
	 */
	public static void setSessionAttribute(String key, Object value) {
		getSession().setAttribute(key, value);
	}
	
	/**
	 * 从Session中获取属性
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getSessionAttribute(String key) {
		return (T) getSession().getAttribute(key);
	}
	
	/**
	 * 从Session中移除属性
	 */
	public static void removeSessionAttribute(String key) {
		getSession().removeAttribute(key);
	}
	
	/**
	 * 使Session失效
	 */
	public static void invalidateSession() {
		getSession().invalidate();
	}
	
	
}

