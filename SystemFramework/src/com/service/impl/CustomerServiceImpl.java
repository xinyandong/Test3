package com.service.impl;

import java.util.List;

import com.commons.db.hibernate.HibernateSessionUtil;
import com.commons.exception.DaoException;
import com.commons.exception.ServiceException;
import com.dao.CustomerDao;
import com.factory.DaoFactory;
import com.model.Customer;
import com.service.CustomerService;

public class CustomerServiceImpl extends GenericServiceImpl implements CustomerService {
	private CustomerDao customerDao = DaoFactory.getCustomerDaoInstance();

	/**
	 * 插入客户记录
	 * 
	 * @param lists
	 *            封装客户信息的集合
	 * @return 操作结果
	 * @throws ServiceException
	 */
	public boolean insert(List<Customer> lists) throws ServiceException {
		boolean result = true;
		try {
			HibernateSessionUtil.beginTransaction();
			for (Customer item : lists) {
				this.customerDao.insert(item);
			}
			HibernateSessionUtil.commitTransaction();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			result = false;
			e.printStackTrace();
			HibernateSessionUtil.rollbackTransaction();
			throw new ServiceException("CustomerServiceImpl#public List<Customer> findAll()#发生异常");
		}
		return result;
	}

	/**
	 * 查询全部客户记录
	 * 
	 * @return 查询结果
	 * @throws ServiceException
	 */
	public List<Customer> findAll() throws ServiceException {
		try {
			HibernateSessionUtil.beginTransaction();
			List<Customer> result = this.customerDao.findAll();
			HibernateSessionUtil.commitTransaction();
			return result;
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			HibernateSessionUtil.rollbackTransaction();
			throw new ServiceException("CustomerServiceImpl#public List<Customer> findAll()#发生异常");
		}
	}
}
