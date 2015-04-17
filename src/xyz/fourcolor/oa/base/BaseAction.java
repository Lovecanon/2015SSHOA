package xyz.fourcolor.oa.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import xyz.fourcolor.oa.domain.User;
import xyz.fourcolor.oa.service.DepartmentService;
import xyz.fourcolor.oa.service.ForumService;
import xyz.fourcolor.oa.service.PrivilegeService;
import xyz.fourcolor.oa.service.ReplyService;
import xyz.fourcolor.oa.service.RoleService;
import xyz.fourcolor.oa.service.TopicService;
import xyz.fourcolor.oa.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T>{
	//ModelDriven的支持
	protected T model;
	
	public BaseAction() {
		try {
		//通过反射获取model的真是类型
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
		//通过反射创建model实例
		model = clazz.newInstance();
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public T getModel() {
		return model;
	}
	
	//所有Service实例的声明
	
	@Resource
	protected RoleService roleService;
	@Resource
	protected DepartmentService departmentService;
	@Resource
	protected UserService userService;
	@Resource
	protected PrivilegeService privilegeService;
	@Resource
	protected ForumService forumService;
	
	@Resource
	protected TopicService topicService;
	@Resource
	protected ReplyService replyService;

	/*
	 * 获取当前登录用户
	 */
	protected User getCurrentUser() {
		return (User) ActionContext.getContext().getSession().get("user");
	}
}
