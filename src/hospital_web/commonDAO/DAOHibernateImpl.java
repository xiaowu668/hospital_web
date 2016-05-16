package hospital_web.commonDAO;

import java.util.List;

public abstract class DAOHibernateImpl<T> extends BaseDAOHibernateImpl {
	public abstract T getT();

	public void delete(long id) {
		super.delete(getT().getClass(), id);
	}

	public List findAll(T obj, int pageSize, int pageNo, String field,
			String order) {
		return super.findByAll(obj, pageSize, pageNo, field, order);
	}

	public int findAllCount(T obj) {
		return super.findCount(obj);
	}

	public T findById(long id) {
		return (T) super.findById(getT().getClass(), id);
	}

	public void save(T obj) {
		super.saves(obj);
	}

	public void update(T obj) {
		super.updates(obj);
	}
}
