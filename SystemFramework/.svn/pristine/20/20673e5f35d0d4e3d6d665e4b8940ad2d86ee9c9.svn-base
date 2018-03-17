package com.commons.db.jdbc;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据库事务管理工具类
 * 
 * @author dougang
 * 
 */
public class TransactionManager {
	/**
	 * 开启数据库事务
	 */
	public static void beginTransaction() {
		Connection con = DBConnectionUtil.getConnection();
		try {
			if (con != null) {
				con.setAutoCommit(false);
			}
		} catch (SQLException e) {
			System.err.println("开启事务出现异常！#TransactionManager#public static void beginTransaction()");
			e.printStackTrace();
		}

	}

	/**
	 * 事务提交
	 */
	public static void commitTransaction() {
		Connection con = DBConnectionUtil.getConnection();
		try {
			if (con != null) {
				con.commit();
				con.setAutoCommit(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("事务提交失败!#TransactionManager#public static void commitTransaction()");
		}

	}

	/**
	 * 事务回滚
	 */
	public static void rollbackTransaction() {
		Connection con = DBConnectionUtil.getConnection();
		try {
			if (con != null) {
				con.rollback();
				con.setAutoCommit(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("事务回滚失败!#TransactionManager#public static void rollbackTransaction()");
		}

	}
}
