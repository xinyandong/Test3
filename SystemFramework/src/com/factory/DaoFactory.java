package com.factory;

import com.dao.CustomerDao;
import com.dao.impl.CustomerDaoImpl;

/**
 * Dao工厂类
 * 
 * @author dougang
 * 
 */
public class DaoFactory {
	public static CustomerDao getCustomerDaoInstance() {
		return new CustomerDaoImpl();
	}
}
