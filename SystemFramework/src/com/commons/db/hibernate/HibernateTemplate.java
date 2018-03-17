package com.commons.db.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 * Hibernate增删改查操作封装类
 * 
 * @author dougang
 * 
 */
@SuppressWarnings("all")
public class HibernateTemplate<T> {

	private Class<T> clazz;

	public HibernateTemplate(Class<T> clazz) {
		this.clazz = clazz;
	}

	/**
	 * 插入数据
	 * 
	 * @param object
	 *            封装新数据的对象
	 */
	public void save(T object) {
		Session session = HibernateSessionUtil.getSession();
		session.save(object);
	}

	/**
	 * 更新数据
	 * 
	 * @param object
	 *            封装新数据的对象
	 */
	public void update(T object) {
		Session session = HibernateSessionUtil.getSession();
		session.merge(object);
	}

	/**
	 * 插入或更新数据
	 * 
	 * @param object
	 *            封装新数据的对象
	 */
	public void saveOrUpdate(T object) {
		Session session = HibernateSessionUtil.getSession();
		session.saveOrUpdate(object);
	}

	/**
	 * 删除数据
	 * 
	 * @param object
	 *            封装数据的对象
	 */
	public void delete(T object) {
		Session session = HibernateSessionUtil.getSession();
		session.delete(object);
	}

	/**
	 * 批量更新
	 * 
	 * @param hql
	 *            更新语句
	 * @param params
	 *            封装条件参数的数组
	 * @return 更新行数
	 */
	public int executeUpdate(String hql, Object[] params) {
		Session session = HibernateSessionUtil.getSession();
		Query query = session.createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++)
				query.setParameter(i, params[i]);
		}
		int cnt = query.executeUpdate();
		return cnt;
	}

	/**
	 * 批量更新
	 * 
	 * @param hql
	 *            更新语句
	 * @return 更新行数
	 */
	public int executeUpdate(String hql) {
		return executeUpdate(hql, null);
	}

	/**
	 * 根据ID查询唯一Bean对象返回
	 * 
	 * @param clazz
	 *            类对象
	 * @param id
	 *            主键值
	 * @return 封装查询结果的对象
	 */
	public T getBeanById(Serializable id) {
		Session session = HibernateSessionUtil.getSession();
		T result = (T) session.get(this.clazz, id);
		return result;
	}

	/**
	 * 根据hql语句查询数据，并将查询结果以Bean List形式返回
	 * 
	 * @param hql
	 *            查询语句
	 * @param params
	 *            封装查询参数的数组
	 * @return 封装查询结果的集合
	 */
	public List<T> queryForBeanList(String hql, Object[] params) {
		Session session = HibernateSessionUtil.getSession();
		Query query = session.createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++)
				query.setParameter(i, params[i]);
		}
		List<T> result = query.list();
		return result;
	}

	/**
	 * 根据hql语句查询数据，并将查询结果以Bean List形式返回
	 * 
	 * @param hql
	 *            查询语句
	 * @return 封装查询结果的集合
	 */
	public List<T> queryForBeanList(String hql) {
		return queryForBeanList(hql, null);
	}

	/**
	 * 根据hql语句查询数据，并将查询结果以Bean List形式返回
	 * 
	 * @param hql
	 *            查询语句
	 * @param params
	 *            封装查询条件的对象
	 * @return 封装查询结果的集合
	 */
	public List<T> queryForBeanList(String hql, Object param) {
		Session session = HibernateSessionUtil.getSession();
		Query query = session.createQuery(hql);
		query.setProperties(param);
		List<T> result = query.list();
		return result;
	}

	/**
	 * 根据hql语句查询数据，并将查询结果以Array List形式返回
	 * 
	 * @param hql
	 *            查询语句
	 * @param params
	 *            封装查询条件的对象
	 * @return 封装查询结果的集合
	 */
	public List<Object[]> queryForArrayList(String hql, Object[] params) {
		Session session = HibernateSessionUtil.getSession();
		Query query = session.createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++)
				query.setParameter(i, params[i]);
		}
		List<Object[]> result = query.list();
		return result;
	}

	/**
	 * 根据hql语句查询数据，并将查询结果以Array List形式返回
	 * 
	 * @param hql
	 *            查询语句
	 * @return 封装查询结果的集合
	 */
	public List<Object[]> queryForArrayList(String hql) {
		return this.queryForArrayList(hql, null);
	}

	/**
	 * 根据hql语句查询数据，并将查询结果以Array形式返回
	 * 
	 * @param hql
	 *            查询语句
	 * @param params
	 *            封装查询条件的对象
	 * @return 封装查询结果的数组
	 */
	public Object[] queryForArray(String hql, Object[] params) {
		Session session = HibernateSessionUtil.getSession();
		Query query = session.createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++)
				query.setParameter(i, params[i]);
		}
		Object[] result = (Object[]) query.uniqueResult();
		return result;
	}

	/**
	 * 根据hql语句查询数据，并将查询结果以Array形式返回
	 * 
	 * @param hql
	 *            查询语句
	 * @return 封装查询结果的数组
	 */
	public Object[] queryForArray(String hql) {
		return this.queryForArray(hql, null);
	}

	/**
	 * 聚合函数统计
	 * 
	 * @param hql
	 *            查询语句
	 * @param params
	 *            封装查询参数的数组
	 * @return 统计结果
	 */
	public Object queryForAggregate(String hql, Object[] params) {
		Session session = HibernateSessionUtil.getSession();
		Query query = session.createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++)
				query.setParameter(i, params[i]);
		}
		Object result = query.uniqueResult();
		return result;
	}

	/**
	 * 聚合函数统计
	 * 
	 * @param hql
	 *            查询语句
	 * @return 统计结果
	 */
	public Object queryForAggregate(String hql) {
		return queryForAggregate(hql, null);
	}

	/**
	 * 根据hql语句查询数据，并将查询结果以Bean对象形式返回
	 * 
	 * @param hql
	 *            查询语句
	 * @param params
	 *            封装查询参数的数组
	 * @return 封装查询结果的对象
	 */
	public T queryForBean(String hql, Object[] params) {
		Session session = HibernateSessionUtil.getSession();
		Query query = session.createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++)
				query.setParameter(i, params[i]);
		}
		T result = (T) query.uniqueResult();
		return result;
	}

	/**
	 * 根据hql语句查询数据，并将查询结果以Bean对象形式返回
	 * 
	 * @param hql
	 *            查询语句
	 * @param params
	 *            封装查询参数的数组
	 * @return 封装查询结果的对象
	 */
	public T queryForBean(String hql) {
		return this.queryForBean(hql, null);
	}

}
