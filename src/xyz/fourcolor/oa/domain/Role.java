package xyz.fourcolor.oa.domain;

import java.util.HashSet;
import java.util.Set;

public class Role implements java.io.Serializable{
	private Long id;
	private String name;
	private String description;
	private Set<User> users = new HashSet<User>();
	
	//集合类型的属性先初始化，当一个方法返回集合时没有值要返回一个空的集合
	private Set<Privilege> privileges = new HashSet<Privilege>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public Set<Privilege> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}
	
	

}
