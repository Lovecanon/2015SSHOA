package xyz.fourcolor.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.fourcolor.oa.base.DaoSupportImpl;
import xyz.fourcolor.oa.domain.Privilege;
import xyz.fourcolor.oa.service.PrivilegeService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class PrivilegeServiceImpl extends DaoSupportImpl<Privilege> implements PrivilegeService{

	public List<Privilege> findTopList() {
		return getSession().createQuery(//
				"FROM Privilege p WHERE p.parent IS NULL")//
				.list();
	}

}
