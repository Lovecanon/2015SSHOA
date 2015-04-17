package xyz.fourcolor.oa.view.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xyz.fourcolor.oa.base.BaseAction;
import xyz.fourcolor.oa.domain.Reply;

@Controller
@Scope("prototype")
public class ReplyAction extends BaseAction<Reply>{
	
	//回帖页面
	public String addUI() throws Exception {

		return "addUI";
	}
	//回帖
	public String add() throws Exception {

		return "toTopicShow";//重定向到新回复所在主题页面
	}
}
