package xyz.fourcolor.oa.util;

import java.util.ArrayList;
import java.util.List;

public class QueryHelper {
	
	private String fromClause;//from子句
	private String whereClause = "";//where子句，第一个条件前加where，第二个条件前加and
	private String orderClause= "";//order by 子句
	
	private List paramList = new ArrayList<Object>(); 
	
	/**
	 * 生成From子句， alias为别名。
	 * @param clazz
	 * @param alias
	 */
	public QueryHelper(Class clazz, String alias) {
		fromClause = "FROM " + clazz.getSimpleName() + " " + alias;
	}
	/**
	 * 拼接Where子句
	 */
	public QueryHelper addCondition(String condition, Object...params) {
		if(whereClause.length() == 0) {
			whereClause = " WHERE " + condition;
		} else {
			whereClause += " AND " + condition;
		}
		
		if(params != null) {
			for(Object p : params) {
				paramList.add(p);
			}
		}
		return this;
	}
	/*
	 * 先判断，如果条件appen成立则拼接where子句
	 */
	public QueryHelper addCondition(boolean appen, String condition, Object...params) {
		if(appen) {
			this.addCondition(condition, params);
		}
		return this;
	}
	/**
	 * 拼接Order By子句
	 * @param property
	 * @param asc
	 * @return
	 */
	public QueryHelper addOrderProperty(String property, boolean asc) {
		if(orderClause.length() == 0) {
			orderClause = " ORDER BY " + property + (asc ? " ASC" : " DESC");
		} else {
			orderClause += ", " + property + (asc ? " ASC" : " DESC");
		}
		return this;
	}
	/*
	 *  先判断，如果条件appen成立则拼接Order By子句
	 */
	public QueryHelper addOrderProperty(boolean appen, String property, boolean asc) {
		if(appen) {
			this.addOrderProperty(property, asc);
		}
		return this;
	}
	
	
	public String getListQueryHql() {
		return fromClause + whereClause + orderClause;
	}
	
	public String getCountQueryHql() {
		return "SELECT COUNT(*) " + fromClause + whereClause;
	}
	
	public List getParams() {
		return paramList;
	}
	
}
