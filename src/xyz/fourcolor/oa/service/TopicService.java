package xyz.fourcolor.oa.service;

import java.util.List;

import xyz.fourcolor.oa.base.DaoSupport;
import xyz.fourcolor.oa.domain.Forum;
import xyz.fourcolor.oa.domain.Topic;

public interface TopicService extends DaoSupport<Topic>{
	/**
	 * 查询指定版块中的所有主题，排序：所有置顶贴在最上面，并按照时间排序
	 */
	List<Topic> findByForum(Forum forum);

}
