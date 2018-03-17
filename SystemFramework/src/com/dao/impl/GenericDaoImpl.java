package com.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import com.commons.db.hibernate.HibernateTemplate;
import com.commons.db.jdbc.JdbcTemplate;
import com.commons.util.Pager;
import com.dao.GenericDao;

/**
 * Dao基类
 * 
 * @author dougang
 * 
 * @param <T>
 */
@SuppressWarnings("all")
public class GenericDaoImpl<T> implements GenericDao<T> {
	private JdbcTemplate<T> jdbcTemplate;
	private HibernateTemplate<T> hibernateTemplate;

	public GenericDaoImpl() {
		Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.jdbcTemplate = new JdbcTemplate<T>(clazz);
		this.hibernateTemplate = new HibernateTemplate<T>(clazz);
	}

	public JdbcTemplate<T> getJdbcTemplate() {
		return this.jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate<T> jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * 分页查询
	 * 
	 * @param sql
	 *            原始SQL查询语句
	 * @param pager
	 *            封装分页信息的实体对象
	 * @return 封装查询结果的集合
	 */
	protected List<T> findForPages(String sql, Pager pager) throws SQLException {
		if (pager != null) {
			Object result = this.getJdbcTemplate().queryForObject("SELECT COUNT(*) FROM (" + sql + ")");
			pager.setRowCount(((BigDecimal) result).longValue());
			sql = "SELECT * FROM (SELECT rownum AS r, t.* FROM (" + sql + ") t" + ") WHERE r>" + pager.getFirstRow()
					+ " AND r<=" + (pager.getFirstRow() + pager.getRowPerPage());
		}
		return this.getJdbcTemplate().queryForBeanList(sql);
	}

}
