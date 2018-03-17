package com.dao.impl;

import java.util.List;

import com.commons.exception.DaoException;
import com.dao.CustomerDao;
import com.model.Customer;

/**
 * CustomerDao实现类
 * 
 * @author dougang
 *
 */
public class CustomerDaoImpl extends GenericDaoImpl<Customer> implements CustomerDao {
	/**
	 * 插入记录
	 * 
	 * @param obj
	 *            封装新记录的对象
	 * @throws DaoException
	 */
	public void insert(Customer obj) throws DaoException {
		try {
			this.getHibernateTemplate().save(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DaoException("CustomerDaoImpl#public void insert(Customer obj)#发生异常");
		}
	}

	/**
	 * 查询全部记录
	 * 
	 * @return 查询结果
	 * @throws DaoException
	 */
	public List<Customer> findAll() throws DaoException {
		String hql = "from Customer";
		try {
			return this.getHibernateTemplate().queryForBeanList(hql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DaoException("CustomerDaoImpl#public List<Customer> findAll()#发生异常");
		}
	}
}
