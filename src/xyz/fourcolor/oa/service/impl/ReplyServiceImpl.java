package xyz.fourcolor.oa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.fourcolor.oa.base.DaoSupportImpl;
import xyz.fourcolor.oa.domain.Reply;
import xyz.fourcolor.oa.service.ReplyService;

@Service
@Transactional
public class ReplyServiceImpl extends DaoSupportImpl<Reply> implements ReplyService{

}
