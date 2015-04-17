package xyz.fourcolor.oa.service;

import xyz.fourcolor.oa.base.DaoSupport;
import xyz.fourcolor.oa.domain.Forum;

public interface ForumService extends DaoSupport<Forum>{

	void moveDown(Long id);

	void moveUp(Long id);

}
