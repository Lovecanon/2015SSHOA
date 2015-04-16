package xyz.fourcolor.oa.util;

import xyz.fourcolor.oa.domain.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckPrivilegeInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
//		System.out.println("--------->之前");
//		String result = ai.invoke();//放行
//		System.out.println("---------->之后");
		
		
		User user = (User) ActionContext.getContext().getSession().get("user");
		String nameSpace = ai.getProxy().getNamespace();
		String actionName = ai.getProxy().getActionName();
		String privUrl = nameSpace + actionName;
		//如果未登录，转到登录页面
		if(user == null) {
			if(privUrl.startsWith("/user_login")) {
				return ai.invoke();
			} else {
			return "loginUI";
			}
		}//如果已登录，判断权限
		else {//如果有权限，放行
			if(user.hasPrivilegeByUrl(privUrl)) {
				return ai.invoke();
			} else {//如果没权限，就跳转到提示没权限页面
				return "noPrivilegeError";
				
			}
		}
		     
		     
	}

}
