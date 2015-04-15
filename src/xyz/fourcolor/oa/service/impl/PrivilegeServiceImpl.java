package xyz.fourcolor.oa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.fourcolor.oa.base.DaoSupportImpl;
import xyz.fourcolor.oa.domain.Privilege;
import xyz.fourcolor.oa.service.PrivilegeService;

@Service
@Transactional
public class PrivilegeServiceImpl extends DaoSupportImpl<Privilege> implements PrivilegeService{

}
