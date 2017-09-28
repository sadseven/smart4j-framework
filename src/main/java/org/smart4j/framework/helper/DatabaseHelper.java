package org.smart4j.framework.helper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.util.PropsUtil;


/**
 * 数据库操作助手类
 * @author Administrator
 *
 */
public class DatabaseHelper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);
	
	private static final QueryRunner QUERY_RUNNER = new QueryRunner();
	private static final ThreadLocal<Connection> CONNECTION_HOLDER = new ThreadLocal<Connection>();
	private static final BasicDataSource DATA_SOURCE;

	
	static {
		String driver = ConfigHelper.getJdbcDriver();
		String url = ConfigHelper.getJdbcUrl();
		String username = ConfigHelper.getJdbcUsername();
		String password = ConfigHelper.getJdbcPassword();
		
		DATA_SOURCE = new BasicDataSource();
		DATA_SOURCE.setDriverClassName(driver);
		DATA_SOURCE.setUrl(url);
		DATA_SOURCE.setUsername(username);
		DATA_SOURCE.setPassword(password);
	}
	
	/**
	 * 获取数据库连接
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = CONNECTION_HOLDER.get();
		if (conn == null) {
			try {
				conn = DATA_SOURCE.getConnection(); 
			} catch (SQLException e) {
				LOGGER.error("get connection failure", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.set(conn);
			}
		}
		return conn;
	}
	
	
	
	/**
	 * 开启事务
	 */
	public static void beginTransaction() {
		Connection conn = getConnection();
		if (conn != null) {
			try {
				conn.setAutoCommit(false);
			} catch (SQLException e) {
				LOGGER.error("begin transaction failue", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.set(conn);
			}
		}
	}
	
	/**
	 * 提交事务
	 */
	public static void commitTranction() {
		Connection conn = getConnection();
		if (conn != null) {
			try {
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				LOGGER.error("commit tranction failure", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.remove();
			}
		}
	}
	
	
	/**
	 * 回滚事务      
	 */
	public static void rollbackTranction() {
		Connection conn = getConnection();
		if (conn != null) {
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e) {
				LOGGER.error("rollback tranction failuer", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.remove();
			}
		}
	}
	
	
	
}
