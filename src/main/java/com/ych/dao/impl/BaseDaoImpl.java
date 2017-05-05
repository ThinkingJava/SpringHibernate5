package com.ych.dao.impl;

import com.ych.dao.BaseDao;
import com.ych.util.GenericsUtils;
import org.hibernate.*;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Administrator on 2017-05-03.
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
    // 存储泛型的实际参数
    private Class<T> clazz;

    public BaseDaoImpl() {
        // 谁实现该类，这就是谁的类字节码
        Class c = this.getClass();
        // 返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type
        Type type = c.getGenericSuperclass();
        // 将类型强转为参数化类型
        ParameterizedType pType = (ParameterizedType) type;
        // 获取该类的父类的所有实际类型参数，也就是泛型的实际参数
        // 这里也就是获取BaseDaoImpl的实际类型参数
        Type[] actualTypeArguments = pType.getActualTypeArguments();
        // 将实际类型参数赋值给成员变量
        clazz = (Class<T>) (actualTypeArguments[0]);
    }

    @Resource(name = "sessionFactory")
    public void setMySessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public void save(Object obj) {
        // TODO Auto-generated method stub
        this.getHibernateTemplate().save(obj);
    }

    public void HibernateTemplateflush(){
        this.getHibernateTemplate().flush();
    }

    @Override
    public void saveOrUpdate(Object obj) {
        // TODO Auto-generated method stub
        this.getHibernateTemplate().saveOrUpdate(obj);
    }

    @Override
    public void update(Object obj) {
        // TODO Auto-generated method stub
        this.getHibernateTemplate().update(obj);
    }

    @Override
    public void delete(Object obj) {
        // TODO Auto-generated method stub
        this.getHibernateTemplate().delete(obj);
    }

    @Override
    public void deleteById(Object entityId) {
        // TODO Auto-generated method stub
        this.getHibernateTemplate().delete(this.getHibernateTemplate().get(clazz, (Serializable)entityId));
    }

    @Override
    public void delete(Object[] entityIds) {
        // TODO Auto-generated method stub

    }

    @Override
    public T get(Serializable entityId) {
        // TODO Auto-generated method stub
        return (T) this.getHibernateTemplate().get(this.clazz, entityId);
    }



    @Override
    public T load(Serializable entityId) {
        // TODO Auto-generated method stub
        return (T)this.getHibernateTemplate().get(this.clazz, entityId);
    }



    @Override
    public Object createSqlQuery(final String sql) {
        // TODO Auto-generated method stub
        return this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                // TODO Auto-generated method stub
                return session.createSQLQuery(sql).uniqueResult();
            }
        });

    }



//	@Override
//	public Object createSqlQuery(String sql, Object[] queryParams,,Class<E> classz) {
//		// TODO Auto-generated method stub
//	  Query query =	getSession().createSQLQuery(sql).;
//	  setQueryParams(query, queryParams);//设置查询参数
//	  return query.uniqueResult();
//	}


    @Override
    public Object createSqlQuery(final String sql, final Object[] queryParams) {
        // TODO Auto-generated method stub
        return this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                // TODO Auto-generated method stub
                Query query = session.createSQLQuery(sql);//执行查询
                setQueryParams(query, queryParams);//设置查询参数
                return query.uniqueResult();
            }
        });
    }


    @Override
    public void createSqlQueryNotReturn(final String sql, final Object[] queryParams) {
        // TODO Auto-generated method stub
        this.getHibernateTemplate().execute(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session) throws HibernateException {

                Query query = session.createSQLQuery(sql);
                setQueryParams(query, queryParams);//设置查询参数
                query.executeUpdate();
                return null;
            }
        });
    }



    @Override
    public <E> List<E> createSqlQuery(final String sql, final List list, final Class<E> classz) {
        // TODO Auto-generated method stub
        return this.getHibernateTemplate().execute(new HibernateCallback<List<E>>() {

            @Override
            public List<E> doInHibernate(Session session) throws HibernateException {
                // TODO Auto-generated method stub
                Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(classz));
                setQueryParams(query, list);//设置查询参数
                return query.list();
            }
        });
    }


    /**
     * 利用hql语句查找单条信息
     */
    @Override
    @Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
    public Object uniqueResult(final String hql, final Object[] queryParams) {
        // TODO Auto-generated method stub
        return this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                // TODO Auto-generated method stub
                Query query = session.createQuery(hql);//执行查询
                setQueryParams(query, queryParams);//设置查询参数
                return query.uniqueResult();
            }
        });

    }



    @Override
    public <E>Object createSqlQuery(final String hql,final Object[] queryParams,final Class<E> classz) {
        // TODO Auto-generated method stub
        return this.getHibernateTemplate().execute(new HibernateCallback<E>() {

            @Override
            public E doInHibernate(Session session) throws HibernateException {
                // TODO Auto-generated method stub
                Query query = session.createSQLQuery(hql).setResultTransformer(Transformers.aliasToBean(classz));
                setQueryParams(query, queryParams);//设置查询参数
                return (E) query.uniqueResult();
            }
        });
    }




    @Override
    public Object getCount(final String sql, final Object[] queryParams) {
        // TODO Auto-generated method stub
        return this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                // TODO Auto-generated method stub
                Query query = session.createSQLQuery(sql);
                setQueryParams(query, queryParams);//设置查询参数
                return query.uniqueResult();
            }

        });
    }


    @Override
    public long getCountByWhere(String where, Object[] queryParams) {
        // TODO Auto-generated method stub
        String	hql = new StringBuffer().append("select count(*) from ")//添加hql语句
                .append(GenericsUtils.getGenericName(this.clazz))//添加对象类型
                .append(" ")//添加空格
                .append(where == null ? "" : where)//如果where为null就添加空格,反之添加where
                .toString();//转化为字符串
        return (Long)uniqueResult(hql,queryParams);
    }


    @Override
    public long getCount(String hql, String where, Object[] queryParams) {
        // TODO Auto-generated method stub
        String	sql = new StringBuffer().append(hql)//添加hql语句
                .append(GenericsUtils.getGenericName(this.clazz))//添加对象类型
                .append(where == null ? "" : where)//如果where为null就添加空格,反之添加where
                .toString();//转化为字符串
        return (Long)uniqueResult(sql,queryParams);
    }


    @Override
    public List<T> findPage(final int pageNo, final int maxResult, final String hql,
                            final Object[] queryParams) {
        // TODO Auto-generated method stub
        return this.getHibernateTemplate().execute(new HibernateCallback<List<T>>() {

            @Override
            public List<T> doInHibernate(Session session) throws HibernateException {
                // TODO Auto-generated method stub

                Query query = session.createSQLQuery(hql).setResultTransformer(Transformers.aliasToBean(clazz));
                setQueryParams(query, queryParams);// 为参数赋值
                List list = null;// 定义List对象
                // 如果maxResult<0，则查询所有
                if (maxResult < 0 && pageNo < 0) {
                    list = query.list();// 将查询结果转化为List对象
                }else{
                    list = query.setFirstResult(getFirstResult(pageNo, maxResult))// 设置分页起始位置
                            .setMaxResults(maxResult)// 设置每页显示的记录数
                            .list();// 将查询结果转化为List对象
                }

                return list;
            }
        });
    }


    /**
     * 创建排序hql语句
     * @param orderby
     * @return 排序字符串
     */
    protected String createOrderBy(Map<String, String> orderby){
        StringBuffer sb = new StringBuffer("");
        if(orderby != null && orderby.size() > 0){
            sb.append(" order by ");
            for(String key : orderby.keySet()){
                sb.append(key).append(" ").append(orderby.get(key)).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 获取分页查询中结果集的起始位置
     * @param pageNo 第几页
     * @param maxResult 页面显示的记录数
     * @return 起始位置
     */
    protected int getFirstResult(int pageNo,int maxResult){
        int firstResult = (pageNo-1) * maxResult;
        return firstResult < 0 ? 0 : firstResult;
    }

    /**
     * 对query中的参数赋值
     * @param query
     * @param queryParams
     */
    protected void setQueryParams(Query query, Object[] queryParams){
        if(queryParams!=null && queryParams.length>0){
            for(int i=0; i<queryParams.length; i++){
                query.setParameter(i, queryParams[i]);
            }
        }
    }

    protected void setQueryParams(Query query, List queryParams){
        if(queryParams!=null && queryParams.size()>0){
            for(int i=0; i<queryParams.size(); i++){
                query.setParameter(i, queryParams.get(i));
            }
        }
    }

    @Override
    public List createHqlQuery(final String hql,final Object[] queryParams){
        return getHibernateTemplate().execute(new HibernateCallback<List>() {

            @Override
            public List doInHibernate(Session session) throws HibernateException {
                // TODO Auto-generated method stub
                Query query = session.createSQLQuery(hql);// 执行查询
                setQueryParams(query, queryParams);// 为参数赋值
                return query.list();
            }
        });


    }


    @Override
    public List<T> execSQLQuery(final String hql, final Object[] values) {

        return this.getHibernateTemplate().execute(new HibernateCallback<List<T>>() {

            @Override
            public List<T> doInHibernate(Session session) throws HibernateException {
                // TODO Auto-generated method stub
                SQLQuery query = session.createSQLQuery(hql);

                if (values != null && values.length > 0) {
                    for (int i = 0; i < values.length; i++) {
                        query.setParameter(i, values[i]);
                    }
                }

                query.addEntity(clazz);
                return query.list();
            }
        });



    }


    @Override
    public List<T> findByParam(final String hql, final Object[] queryParams) {
        // TODO Auto-generated method stub
        return this.getHibernateTemplate().execute(new HibernateCallback<List<T>>() {

            @Override
            public List<T> doInHibernate(Session session) throws HibernateException {
                // TODO Auto-generated method stub
                Query query = session.createQuery(hql);// 执行查询
                setQueryParams(query, queryParams);// 为参数赋值
                List<T> list = query.list();// 将查询结果转化为List对象
                return list;
            }
        });

    }


    @Override
    public List<Map<String, Object>> execSQLQueryToMap(final String sql,
                                                       final Object[] queryParams) {
        // TODO Auto-generated method stub
        return (List<Map<String, Object>>) this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                SQLQuery query = session.createSQLQuery(sql);
                if(queryParams!=null && queryParams.length>0){
                    for (int i = 0; i < queryParams.length; i++) {
                        query.setParameter(i, queryParams[i]);
                    }
                }

                query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

                return query.list();
            }
        });
    }

    @Override
    public List<Map<String, Object>> execSQLQueryToMap(final String sql, final List list) {
        // TODO Auto-generated method stub
        return  (List<Map<String, Object>>) this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                SQLQuery query = session.createSQLQuery(sql);
                if(list!=null && list.size()>0){
                    for (int i = 0; i < list.size(); i++) {
                        query.setParameter(i, list.get(i));
                    }
                }

                query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

                return query.list();
            }
        });
    }


    @Override
    public List<Map<String, Object>> findSpecial(final int pageNo, final int maxResult, final String hql,
                                                 final Object[] queryParams) {
        // TODO Auto-generated method stub
        return this.getHibernateTemplate().execute(new HibernateCallback<List<Map<String, Object>>>() {

            @Override
            public List<Map<String, Object>> doInHibernate(Session session) throws HibernateException {
                // TODO Auto-generated method stub

                Query query = session.createSQLQuery(hql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                setQueryParams(query, queryParams);// 为参数赋值
                List list = null;// 定义List对象
                // 如果maxResult<0，则查询所有
                if (maxResult < 0 && pageNo < 0) {
                    list = query.list();// 将查询结果转化为List对象
                }else{
                    list = query.setFirstResult(getFirstResult(pageNo, maxResult))// 设置分页起始位置
                            .setMaxResults(maxResult)// 设置每页显示的记录数
                            .list();// 将查询结果转化为List对象
                }

                return list;
            }
        });
    }


    @Override
    public List<Map<String, Object>> findSpecial(final int pageNo, final int maxResult,
                                                 final String hql, final List queryParams) {
        // TODO Auto-generated method stub
        return this.getHibernateTemplate().execute(new HibernateCallback<List<Map<String, Object>>>() {

            @Override
            public List<Map<String, Object>> doInHibernate(Session session) throws HibernateException {
                // TODO Auto-generated method stub

                Query query = session.createSQLQuery(hql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                setQueryParams(query, queryParams);// 为参数赋值
                List list = null;// 定义List对象
                // 如果maxResult<0，则查询所有
                if (maxResult < 0 && pageNo < 0) {
                    list = query.list();// 将查询结果转化为List对象
                }else{
                    list = query.setFirstResult(getFirstResult(pageNo, maxResult))// 设置分页起始位置
                            .setMaxResults(maxResult)// 设置每页显示的记录数
                            .list();// 将查询结果转化为List对象
                }

                return list;
            }
        });
    }
}
