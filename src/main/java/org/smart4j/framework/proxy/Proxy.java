package org.smart4j.framework.proxy;


/**
 * 代理借口
 * @author Administrator
 *
 */
public interface Proxy {

	
	/**
	 * 执行链式代理
	 * @param proxyChain
	 * @return
	 */
	Object doProxy(ProxyChain proxyChain) throws Throwable;
	
}
