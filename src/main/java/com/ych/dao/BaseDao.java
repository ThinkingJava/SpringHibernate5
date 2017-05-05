package com.ych.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDao<T> {
	//基本数据库操作方法
			public void save(Object obj);//保存数据
			public void saveOrUpdate(Object obj);//保存或修改数据
			public void update(Object obj);//修改数据
			public void delete(Object obj);//删除数据
			public void deleteById(Object entityId);//根据id删除数据
			public void delete(Object[] entityIds);  //删除多条数据
//			public int bulkDelete(List list);
			public T get(Serializable entityId);//加载实体对象
			public T load(Serializable entityId);//加载实体对象
			public Object uniqueResult(String hql, Object[] queryParams);//使用hql语句操作
			
			public Object getCount(String sql, Object[] queryParams);  //根据条件查询数量
			
			public long getCountByWhere(String hql, Object[] queryParams);  //根据条件查询数量
			
			public long getCount(String hql, String where, Object[] queryParams);  //根据sql查询
			/**
			* @Description: TODO(利用sql查詢多条数据) 
			 */
			public List createHqlQuery(String hql, Object[] queryParams);  //利用sql查詢多条数据
			
			public Object createSqlQuery(String sql);  //利用sql查询一条数据
			
			public Object createSqlQuery(String sql, Object[] queryParams);  //利用sql查询一条数据
			
			public <E>List<E> createSqlQuery(String sql, List list, Class<E> classz);  //利用sql查询一条数据
			
			public void createSqlQueryNotReturn(String sql, final Object[] queryParams);
			
			
		//	public Object createSqlQuery(String sql,Object[] queryParams,Class<E> classz);  //利用sql查询一条数据
			
			public <E>Object createSqlQuery(String hql, Object[] queryParams, Class<E> classz);  //利用sql查詢单条数据

			/**
			 * 
			* @Title: findSpecial 
			* @Description: TODO(自定义分页) 
			* @param @param pageNo
			* @param @param maxResult
			* @param @param hql
			* @param @param queryParams
			* @param @return    设定文件 
			* @return List<Map<String,Object>>    返回类型
			 */
			public List<Map<String, Object>> findSpecial(int pageNo, int maxResult, String hql, Object[] queryParams);  //自定义分页
			
			public List<T> findPage(int pageNo, int maxResult, String hql, Object[] queryParams);
			
			public List<Map<String, Object>> findSpecial(int pageNo, int maxResult, String hql, List queryParams);  //自定义分页
	        //sql返回实体
			public List<T> execSQLQuery(final String hql, final Object[] values);
			
			//根据条件查询
			public List<T> findByParam(final String hql, final Object[] values);
			//sql查询返回map
			public List<Map<String, Object>> execSQLQueryToMap(final String sql, final Object[] values);
			public List<Map<String, Object>> execSQLQueryToMap(final String sql, final List list);
			
}
