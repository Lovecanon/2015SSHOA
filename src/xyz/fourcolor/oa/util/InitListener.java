package xyz.fourcolor.oa.util;

import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import xyz.fourcolor.oa.domain.Privilege;
import xyz.fourcolor.oa.service.PrivilegeService;

public class InitListener implements ServletContextListener {

	// 初始化左侧边栏顶级菜单
	public void contextInitialized(ServletContextEvent sce) {
		/*
		 * 获取容器与相关的Service对象
		 *  >用的是同一个容器对象
		 */

		ApplicationContext ac = WebApplicationContextUtils
				.getWebApplicationContext(sce.getServletContext());
		PrivilegeService privilegeService = (PrivilegeService) ac
				.getBean("privilegeServiceImpl");

		// 准备数据
		List<Privilege> topPrivilegeList = privilegeService.findTopList();
		sce.getServletContext().setAttribute("topPrivilegeList",
				topPrivilegeList);
		System.out.println("-----左侧边栏顶级菜单已准备-----");
		
		//准备数据：allPrivilegeUrls
		Collection<String> allPrivilegeUrls = privilegeService.getAllPrivilegeUrls();
		sce.getServletContext().setAttribute("allPrivilegeUrls",
				allPrivilegeUrls);
		System.out.println("-----不要要验证的URl已准备-----");
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
