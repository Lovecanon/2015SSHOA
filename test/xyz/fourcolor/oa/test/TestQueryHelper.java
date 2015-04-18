package xyz.fourcolor.oa.test;

import org.junit.Test;

import xyz.fourcolor.oa.domain.Forum;
import xyz.fourcolor.oa.domain.Topic;
import xyz.fourcolor.oa.util.QueryHelper;

public class TestQueryHelper {

	/**
	 * 0 表示查看全部主题<br>
	 * 1 表示只看精华帖
	 */
	private int viewType = 1;

	/**
	 * 0 表示默认排序(所有置顶帖在前面，并按最后更新时间降序排列)<br>
	 * 1 表示只按最后更新时间排序<br>
	 * 2 表示只按主题发表时间排序<br>
	 * 3 表示只按回复数量排序
	 */
	private int orderBy = 0;

	/**
	 * true 表示升序<br>
	 * false 表示降序
	 */
	private boolean asc = false;

	private Forum forum = new Forum();

	// // 准备分页信息 v2
	// String hql =
	// "FROM Topic t WHERE t.forum=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC, t.lastUpdateTime DESC";
	// List<Object> parameters = new ArrayList<Object>();
	// parameters.add(forum);
	// PageBean pageBean = replyService.getPageBean(pageNum, pageSize, hql,
	// parameters);
	// ActionContext.getContext().getValueStack().push(pageBean);

	@Test
	public void testQueryHelper() {
		QueryHelper queryHelper = new QueryHelper(Topic.class, "t")//
						//过滤条件
						.addCondition("t.forum=?", forum)//
						.addCondition((viewType == 1), "t.type=?", Topic.TYPE_BEST)//只看精华贴
						//排序条件
					//	.addOrderProperty((orderBy == 0), "t.lastUpdateTime", false)//默认排序按照最近更新时间
						.addOrderProperty((orderBy == 0), "(CASE t.type WHEN 2 THEN 2 ELSE 0 END)", false)//
						.addOrderProperty((orderBy == 1), "t.lastUpdateTime", asc)//
						.addOrderProperty((orderBy == 2), "t.psotTime", asc)//
						.addOrderProperty((orderBy == 3), "t.replyCount", asc);//
	
		System.out.println(queryHelper.getListQueryHql());
		System.out.println(queryHelper.getCountQueryHql());
		System.out.println(queryHelper.getParams());
	
	}

}
