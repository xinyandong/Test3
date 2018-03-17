package com.dao;

import java.util.List;

import com.commons.exception.DaoException;
import com.model.Customer;

/**
 * CustomerDao接口
 * 
 * @author dougang
 *
 */
public interface CustomerDao {
	/**
	 * 插入记录
	 * 
	 * @param obj
	 *            封装新记录的对象
	 * @throws DaoException
	 */
	public void insert(Customer obj) throws DaoException;

	/**
	 * 查询全部记录
	 * 
	 * @return 查询结果
	 * @throws DaoException
	 */
	public List<Customer> findAll() throws DaoException;
}
