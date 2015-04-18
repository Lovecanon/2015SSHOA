package xyz.fourcolor.oa.service;

import java.util.List;

import xyz.fourcolor.oa.base.DaoSupport;
import xyz.fourcolor.oa.domain.Reply;
import xyz.fourcolor.oa.domain.Topic;

public interface ReplyService extends DaoSupport<Reply>{

	List<Reply> findByTopic(Topic topic);

}
