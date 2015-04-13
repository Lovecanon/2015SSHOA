package xyz.fourcolor.oa.view.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xyz.fourcolor.oa.base.BaseAction;
import xyz.fourcolor.oa.domain.Role;
import xyz.fourcolor.oa.service.RoleService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{
	
	public String list() throws Exception {
System.out.println("1roleList-------->");
		List<Role> roleList = roleService.findAll();
System.out.println("2roleList-------->");
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
		//准备回显数据
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().put("role", role);
		return "saveUI";
	}
	public String edit() throws Exception {
		//1.从数据库中获取原对象
		Role role = roleService.getById(model.getId());
		//2.设置要修改的属性
		role.setName(model.getName());
		role.setDescription(model.getDescription());
		//3.更新到数据库中
		roleService.update(role);
		return "toList";
	}
	
}
