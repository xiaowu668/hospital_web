package hospital_web.commonDAO;

import hospital_web.util.EliminateNULLData;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.*;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BaseDAOHibernateImpl extends HibernateDaoSupport {
	public void delete(final Class clz, final long id) {
		this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "delete " + clz.getSimpleName() + " where id=?";
				Query q = session.createQuery(hql);
				q.setLong(0, id);
				q.executeUpdate();
				return null;
			}
		});
	}

	public void updates(Object obj) {
		obj = (Serializable) EliminateNULLData.eliminate(obj);
		this.getHibernateTemplate().update(obj);
	}

	public void saves(Object obj) {
		obj = (Serializable) EliminateNULLData.eliminate(obj);
		this.getHibernateTemplate().save(obj);
	}

	public Object findById(Class clz, long id) {
		return this.getHibernateTemplate().get(clz, id);
	}

	public List findByAll(final Object obj, final int pageSize,
			final int pageNo, final String field, final String order) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException,
					SQLException {
				Criteria c = getCriteria(obj, s);
				if (order.equals("asc")) {
					c.addOrder(Order.asc(field));
				} else {
					c.addOrder(Order.desc(field));
				}
				c.setFirstResult(pageSize * (pageNo - 1));
				c.setMaxResults(pageSize);
				return c.list();
			}
		});
	}
	
	public List findByAll(final Object obj,final List<Object> list, final int pageSize,
			final int pageNo, final String field, final String order) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException,
					SQLException {
				Criteria c = getCriteria(obj,list,s);
				if (order.equals("asc")) {
					c.addOrder(Order.asc(field));
				} else {
					c.addOrder(Order.desc(field));
				}
				c.setFirstResult(pageSize * (pageNo - 1));
				c.setMaxResults(pageSize);
				return c.list();
			}
		});
	}
	
	protected Criteria getCriteria(Object ser,List<Object> list, Session s) {
		Criteria c = s.createCriteria(ser.getClass());
		Class<? extends Object> obj = ser.getClass();
		Method[] mh = obj.getMethods();
		int keyCount=0;
		for (Method method : mh) {
			if (method.getName().indexOf("get") != -1
					&& !method.getName().equals("getClass")) {
				if (method.getReturnType() != java.util.Set.class) {
					try {
						String name = method.getName().substring(3);
						String q = name.substring(0, 1);
						name = q.toLowerCase() + name.substring(1);
						Object value = method.invoke(ser);
						if (isException(method.getName(), value)) {
							if(list.get(keyCount).equals("eq")){
								c.add(Restrictions.eq(name, value));
							}else if(list.get(keyCount).equals("isNull")){
								c.add(Restrictions.isNull(name));
							}else if(list.get(keyCount).equals("like")){
								c.add(Restrictions.like(name, value));
							}else if(list.get(keyCount).equals("le")){
								c.add(Restrictions.le(name, value));
							}else if(list.get(keyCount).equals("gt")){
								c.add(Restrictions.gt(name, value));
							}else if(list.get(keyCount).equals("isNotNull")){
								c.add(Restrictions.isNotNull(name));
							}
							keyCount++;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return c;
	}
	
	protected Criteria getCriteria(Object ser, Session s) {
		Criteria c = s.createCriteria(ser.getClass());
		Class<? extends Object> obj = ser.getClass();
		Method[] mh = obj.getMethods();
		for (Method method : mh) {
			if (method.getName().indexOf("get") != -1
					&& !method.getName().equals("getClass")) {
				if (method.getReturnType() != java.util.Set.class) {
					try {
						String name = method.getName().substring(3);
						String q = name.substring(0, 1);
						name = q.toLowerCase() + name.substring(1);
						Object value = method.invoke(ser);
						if (isException(method.getName(), value)) {
							c.add(Restrictions.eq(name, value));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return c;
	}

	private boolean isException(String mh, Object value) {
		if (value == null || value.toString().equals("")) {
			return false;
		}
		return true;
	}

	public int findCount(Object obj) {
		Criteria c = getCriteria(obj, this.getSession());
		c.setProjection(Projections.rowCount());
		return Integer.parseInt(c.uniqueResult().toString());
	}
	
	public int findCount(Object obj,List<Object> list) {
		Criteria c = getCriteria(obj,list, this.getSession());
		c.setProjection(Projections.rowCount());
		return Integer.parseInt(c.uniqueResult().toString());
	}
}
