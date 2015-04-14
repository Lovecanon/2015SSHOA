package xyz.fourcolor.oa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.fourcolor.oa.base.DaoSupportImpl;
import xyz.fourcolor.oa.domain.User;
import xyz.fourcolor.oa.service.UserService;

@Service
@Transactional
public class UserServiceImpl extends DaoSupportImpl<User> implements UserService{

}
