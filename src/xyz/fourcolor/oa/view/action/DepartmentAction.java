package xyz.fourcolor.oa.view.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import xyz.fourcolor.oa.base.BaseAction;
import xyz.fourcolor.oa.domain.Department;
import xyz.fourcolor.oa.service.DepartmentService;
import xyz.fourcolor.oa.util.DepartmentUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
public class DepartmentAction extends BaseAction<Department> {

	private Long parentId;

	/** 列表 */
	public String list() throws Exception {
		List<Department> departmentList = null;
		if (parentId == null) {// 顶级部门列表
			departmentList = departmentService.findTopList();
		} else {// 子部门列表
			departmentList = departmentService.findChildren(parentId);
			Department parent = departmentService.getById(parentId);
			ActionContext.getContext().put("parent", parent);
		}
		ActionContext.getContext().put("departmentList", departmentList);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		departmentService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		// 准备树状列表
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		//封装信息到对象中
		// Department department = new Department();
		// department.setName(name);
		// department.setDescription(description)
		Department parent = departmentService.getById(parentId);
		model.setParent(parent);
		// 保存
		departmentService.save(model);

		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 准备数据
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		
		// 准备回显的数据
		Department department = departmentService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(department);
		if(department.getParent() != null) {
			parentId = department.getParent().getId();
		}
		
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1，从数据库取出原对象
		Department department = departmentService.getById(model.getId());

		// 2，设置要修改的属性
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		department.setParent(departmentService.getById(parentId));
		
		// 3，更新到数据库中
		departmentService.update(department);

		return "toList";
	}

	// --------------------------------
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
