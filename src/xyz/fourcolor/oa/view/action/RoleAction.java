package xyz.fourcolor.oa.view.action;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xyz.fourcolor.oa.base.BaseAction;
import xyz.fourcolor.oa.domain.Privilege;
import xyz.fourcolor.oa.domain.Role;
import xyz.fourcolor.oa.service.RoleService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
	
	private Long[] privilegeIds;

	public String list() throws Exception {
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "list";
	}

	public String delete() throws Exception {
		roleService.delete(model.getId());
		return "toList";
	}

	public String addUI() throws Exception {
		return "saveUI";
	}

	public String add() throws Exception {
		roleService.save(model);
		return "toList";
	}

	public String editUI() throws Exception {
		// 准备回显数据
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);
		return "saveUI";
	}

	public String edit() throws Exception {
		// 1.从数据库中获取原对象
		Role role = roleService.getById(model.getId());
		// 2.设置要修改的属性
		role.setName(model.getName());
		role.setDescription(model.getDescription());
		// 3.更新到数据库中
		roleService.update(role);
		return "toList";
	}

	// 设置权限页面
	public String setPrivilegeUI() throws Exception {
		// 准备回显数据
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);
		
		if(role.getPrivileges() != null) {
			privilegeIds = new Long[role.getPrivileges().size()];
			int index = 0;
			for(Privilege privilege : role.getPrivileges()) {
				privilegeIds[index++] = privilege.getId();
			}
		}
		//准备数据 所有权限列表
		List<Privilege> privilegeList = privilegeService.findAll();
		ActionContext.getContext().put("privilegeList", privilegeList);
		
		
		return "setPrivilegeUI";
	}

	// 设置权限
	public String setPrivilege() throws Exception {
		// 1.从数据库中获取原对象
		Role role = roleService.getById(model.getId());
		// 2.设置要修改的属性
		List<Privilege> privilegeList = privilegeService.getByIds(privilegeIds);
		role.setPrivileges(new HashSet<Privilege>(privilegeList));
		// 3.更新到数据库中
		roleService.update(role);
		return "toList";
	}

	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}
	

}
