package xyz.fourcolor.oa.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SuppressWarnings("unchecked")
public abstract class DaoSupportImpl<T> implements DaoSupport<T> {

	@Resource
	private SessionFactory sessionFactory;
	private Class<T> clazz;

	public DaoSupportImpl() {
		// 使用发射得到T的真是类型
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
		System.out.println("BaseDaoImpl:----->" + clazz);
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void save(T entity) {
		this.getSession().save(entity);
	}

	public void update(T entity) {
		this.getSession().update(entity);
	}

	public void delete(Long id) {
		Object obj = this.getById(id);
		if (obj != null) {
			this.getSession().delete(obj);
		}
	}

	public T getById(Long id) {
		return (T) this.getSession().get(clazz, id);
	}

	public List<T> getByIds(Long[] ids) {
		return getSession().createQuery(//
				"FROM" + clazz.getSimpleName() + "WHERE id IN (:ids)")// ;
				.setParameterList("ids", ids)//
				.list();
	}

	public List<T> findAll() {
//		return this.getSession().createQuery(//
//				"FROM" + clazz.getSimpleName())//
//				.list();
		return getSession().createQuery(//
				"FROM " + clazz.getSimpleName())//
				.list();
	}
}
