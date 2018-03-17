package com.commons.db.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate session工具类
 * 
 * @author dougang
 */
public class HibernateSessionUtil {

	private static Configuration configuration;
	private static SessionFactory sessionFactory;

	static {
		try {
			configuration = new Configuration().configure();
			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			System.err.println("Error Creating SessionFactory");
			e.printStackTrace();
		}
	}

	private HibernateSessionUtil() {
	}

	/**
	 * 返回绑定在当前线程上的session实例
	 */
	public static Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 关闭session
	 */
	public static void closeSession() {
		Session session = sessionFactory.getCurrentSession();
		if (session != null) {
			session.close();
		}
	}

	/**
	 * 开启事务控制
	 */
	public static void beginTransaction() {
		Session session = sessionFactory.getCurrentSession();
		if (session != null) {
			session.beginTransaction();
		}
	}

	/**
	 * 提交事务
	 */
	public static void commitTransaction() {
		Session session = sessionFactory.getCurrentSession();
		if (session != null) {
			session.getTransaction().commit();
		}
	}

	/**
	 * 回退事务
	 */
	public static void rollbackTransaction() {
		Session session = sessionFactory.getCurrentSession();
		if (session != null) {
			session.getTransaction().rollback();
		}
	}

}