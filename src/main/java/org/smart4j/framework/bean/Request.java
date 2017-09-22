package org.smart4j.framework.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 封装请求
 * @author Arthur.liang
 *
 */
public class Request {

	private String requestMethod;
	
	private String requestPath;
	
	public  Request(String requestMethod, String requestPath) {
		
		this.requestMethod = requestMethod;
		this.requestPath = requestPath;
		
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public String getRequestPath() {
		return requestPath;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	
	
}
