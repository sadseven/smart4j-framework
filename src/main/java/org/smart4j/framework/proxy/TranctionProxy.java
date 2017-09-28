package org.smart4j.framework.proxy;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Transaction;
import org.smart4j.framework.helper.DatabaseHelper;

/**
 * 事务代理
 * @author Administrator
 *
 */
public class TranctionProxy implements Proxy{

	private static final Logger LOGGER = LoggerFactory.getLogger(TranctionProxy.class);
	
	private static final ThreadLocal<Boolean> FLAG_HOLDER = new ThreadLocal<Boolean>() {
		@Override
		protected Boolean initialValue() {
			return false;
		}
	};
	
	@Override
	public Object doProxy(ProxyChain proxyChain) throws Throwable {
		Object result;
		boolean flag = FLAG_HOLDER.get();
		Method targetMethod = proxyChain.getTargetMethod();
		if (!flag && targetMethod.isAnnotationPresent(Transaction.class)) {
			FLAG_HOLDER.set(true);
			try {
				DatabaseHelper.beginTransaction();
				LOGGER.debug("begin transcation");
				result = proxyChain.doProxyChain();
				DatabaseHelper.commitTranction();
				LOGGER.debug("commit transcation");
			} catch (Exception e) {
				DatabaseHelper.rollbackTranction();
				LOGGER.debug("rollback transcation");
				throw e;
			} finally {
				FLAG_HOLDER.remove();
			}
		} else {
			result = proxyChain.doProxyChain();
		}
		return result;
	}
}
