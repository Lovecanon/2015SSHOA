package xyz.fourcolor.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.fourcolor.oa.base.DaoSupportImpl;
import xyz.fourcolor.oa.domain.Department;
import xyz.fourcolor.oa.service.DepartmentService;

@Service("departmentService")
@Transactional
@SuppressWarnings("unchecked")
public class DepartmentServiceImpl extends DaoSupportImpl<Department> implements
		DepartmentService {

	@Resource
	private SessionFactory sessionFactory;
	
	public List<Department> findTopList() {
		return sessionFactory.getCurrentSession().createQuery(//
				"FROM Department d WHERE d.parent IS NULL")//
				.list();
	}
	public List<Department> findChildren(Long parentId) {
		return sessionFactory.getCurrentSession().createQuery(//
				"FROM Department d where d.parent.id=?")//
				.setParameter(0, parentId)//
				.list();
	}
	

//	public List<Department> findAll() {
//		return departmentDao.findAll();
//	}
//
//	public void delete(Long id) {
//		departmentDao.delete(id);
//	}
//
//	public void save(Department model) {
//		departmentDao.save(model);
//	}
//
//	public Department getById(Long id) {
//		return departmentDao.getById(id);
//	}
//
//	public void update(Department department) {
//		departmentDao.update(department);
//
//	}

}
