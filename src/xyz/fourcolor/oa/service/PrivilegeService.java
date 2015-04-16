package xyz.fourcolor.oa.service;

import java.util.Collection;
import java.util.List;

import xyz.fourcolor.oa.base.DaoSupport;
import xyz.fourcolor.oa.domain.Privilege;

public interface PrivilegeService extends DaoSupport<Privilege>{

	List<Privilege> findTopList();

	Collection<String> getAllPrivilegeUrls();

}
