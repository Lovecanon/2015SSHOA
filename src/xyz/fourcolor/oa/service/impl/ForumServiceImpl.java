package xyz.fourcolor.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.fourcolor.oa.base.DaoSupportImpl;
import xyz.fourcolor.oa.domain.Forum;
import xyz.fourcolor.oa.service.ForumService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class ForumServiceImpl extends DaoSupportImpl<Forum> implements
		ForumService {

	@Override
	public List<Forum> findAll() {
		return this.getSession().createQuery(//
				"FROM Forum f ORDER BY f.position")//
				.list();
	};

	@Override
	public void save(Forum forum) {
		// 保存,保存完之后就会生成它的主键，
		super.save(forum);
		// 设置position的值，可以不用手动更新到数据库中去
		forum.setPosition(forum.getId().intValue());
	};

	public void moveUp(Long id) {
		// 找出相关的Forum
		Forum forum = getById(id);
		Forum other = (Forum) getSession().createQuery(//
				"FROM Forum f WHERE f.position<? ORDER BY position DESC")//
				.setParameter(0, forum.getPosition())//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();
		// 最上面的不能上移
		if (other == null) {
			return;
		}

		// 交换position
		int temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);

		/*
		 * 更新到数据库中(这是update()方法可以不用调用。 因为在这个moveDown方法中数据库中的session对象是打开的
		 * >>并且方法内部事务还没有提交，forum和other是刚从session查出来还是持久化状态的对象，
		 * >>也就是说我这两个对象改变后会自动同步到数据库中)
		 */
		this.getSession().update(forum);
		this.getSession().update(other);

	}

	public void moveDown(Long id) {
		// 找出相关的Forum
		Forum forum = getById(id);
		Forum other = (Forum) getSession().createQuery(//
				"FROM Forum f WHERE f.position>? ORDER BY position ASC")//
				.setParameter(0, forum.getPosition())//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();
		// 最下面的不能下移
		if (other == null) {
			return;
		}
		// 交换position
		int temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);

		/*
		 * 更新到数据库中(这是update()方法可以不用调用。 因为在这个moveDown方法中数据库中的session对象是打开的
		 * >>并且方法内部事务还没有提交，forum和other是刚从session查出来还是持久化状态的对象，
		 * >>也就是说我这两个对象改变后会自动同步到数据库中)
		 */
		this.getSession().update(forum);
		this.getSession().update(other);

	}
}
