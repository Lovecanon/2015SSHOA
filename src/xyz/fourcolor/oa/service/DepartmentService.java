package xyz.fourcolor.oa.service;

import java.util.List;

import xyz.fourcolor.oa.base.DaoSupport;
import xyz.fourcolor.oa.domain.Department;

public interface DepartmentService extends DaoSupport<Department>{

	List<Department> findTopList();
	
	List<Department> findChildren(Long parentId);
	
//	List<Department> findAll();
//
//	void delete(Long id);
//
//	void save(Department model);
//
//	Department getById(Long id);
//
//	void update(Department department);
}
