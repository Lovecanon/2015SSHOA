package xyz.fourcolor.oa.test;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.opensymphony.xwork2.ActionSupport;

@Repository
//@Scope("prototype")
public class TestAction extends ActionSupport{
	
	@Resource
	private TestService testService;
	
	@Override
	public String execute() throws Exception {
System.out.println("---->TestAction.execution()");
		testService.saveTwoUser();
		return "success";
	}
}
