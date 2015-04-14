package xyz.fourcolor.oa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import xyz.fourcolor.oa.domain.Department;

public class DepartmentUtils {
	public static List<Department> getAllDepartments(List<Department> topList) {
		List<Department> list = new ArrayList<Department>();
		walkDepartmentTreeList(topList,  "┣", list);
		return list;
	}

	private static void walkDepartmentTreeList(Collection<Department> topList,
			String prefix, List<Department> list) {
		for(Department top : topList) {
			//顶点
			Department copy = new Department();
			copy.setId(top.getId());
			copy.setName(prefix + top.getName());
			list.add(copy);
			//子树
			walkDepartmentTreeList(top.getChildren(), "　　" + prefix, list);
			
		}
		
	}
	
}
