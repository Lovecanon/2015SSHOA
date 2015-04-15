package xyz.fourcolor.oa.view.action;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import xyz.fourcolor.oa.base.BaseAction;
import xyz.fourcolor.oa.domain.Department;
import xyz.fourcolor.oa.domain.Role;
import xyz.fourcolor.oa.domain.User;
import xyz.fourcolor.oa.util.DepartmentUtils;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	private Long departmentId;
	private Long[] roleIds;

	/**
	 * 六大基本方法
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		List<User> userList = userService.findAll();
		ActionContext.getContext().put("userList", userList);

		return "list";
	}

	public String delete() throws Exception {
		userService.delete(model.getId());
		return "toList";
	}

	public String addUI() throws Exception {
		// 准备树状列表 departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);

		// 准备回显数据 roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		return "saveUI";
	}

	public String add() throws Exception {
		// 封装到对象中
		// >设置所属部门
		model.setDepartment(departmentService.getById(departmentId));
		// >设置关联的岗位
		List<Role> roleList = roleService.getByIds(roleIds);
		model.setRoles(new HashSet<Role>(roleList));
		// >设置默认密码为1234（使用MD5摘要）
		String md5Digest = DigestUtils.md5Hex("1234");
		model.setPassword(md5Digest);
		// 保存到数据库
		userService.save(model);

		return "toList";
	}

	public String editUI() throws Exception {
		// 准备树状列表 departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);

		// 准备回显数据 roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		// 准备回显数据
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		if (user.getDepartment() != null) {
			departmentId = user.getDepartment().getId();
		}
		if (user.getRoles() != null) {// 通过拿到用户的所有岗位遍历得到所有岗位的id
			roleIds = new Long[user.getRoles().size()];
			int index = 0;
			for (Role role : user.getRoles()) {
				roleIds[index++] = role.getId();
			}
		}

		return "saveUI";
	}

	public String edit() throws Exception {
		// 1.从数据库中取出原对象
		User user = userService.getById(model.getId());
		// 2.设置要修改的属性
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setGender(model.getGender());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setEmail(model.getEmail());
		user.setDescription(model.getDescription());
		// >设置所属部门
		user.setDepartment(departmentService.getById(departmentId));
		// >设置关联的岗位
		List<Role> roleList = roleService.getByIds(roleIds);
		user.setRoles(new HashSet<Role>(roleList));

		// 3，更新到数据库
		userService.update(user);

		return "toList";
	}

	// 初始化密码
	public String initPassword() throws Exception {
		// 1.从数据库中取出原对象
		User user = userService.getById(model.getId());
		// 2.设置要修改的属性
		user.setPassword("1234");

		// 3，更新到数据库
		userService.update(user);
		return "toList";
	}
	
	//登录页面
	public String loginUI() throws Exception {
		return "loginUI";
		
	}
	//登录
	public String login() throws Exception {
		User user = userService.findByLoginNameAndPassword(model.getLoginName(), model.getPassword());
		if(user == null) {
			addFieldError("login", "用户名或密码错误！");
			return "loginUI";
		} else {//把user对象放到session中
			ActionContext.getContext().getSession().put("user", user);
			return "toIndex";
		}
	}
	//注销
	public String logout() throws Exception {
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}
	

	// -----------------------
	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

}
