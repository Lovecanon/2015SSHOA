package xyz.fourcolor.oa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.fourcolor.oa.base.DaoSupportImpl;
import xyz.fourcolor.oa.domain.Role;
import xyz.fourcolor.oa.service.RoleService;



@Service("roleService")
@Transactional
public class RoleServiceImpl extends DaoSupportImpl<Role> implements RoleService{

//	@Resource
//	private RoleDao roleDao;
//	
//	public List<Role> findAll() {
//		return roleDao.findAll();
//	}
//
//	public void delete(Long id) {
//		roleDao.delete(id);
//	}
//
//	public void save(Role role) {
//		roleDao.save(role);
//	}
//
//	public Role getById(Long id) {
//		return roleDao.getById(id);
//	}
//
//	public void update(Role role) {
//		roleDao.update(role);
//	}

}
