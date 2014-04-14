package org.dreamer.examination.sql.model;

import javax.persistence.criteria.Order;

import org.dreamer.examination.utils.StringUtil;


/**
 * 拼装动态查询时每个字段所对应的查询动作
 * 
 * @author xwang
 * 
 */
public enum SqlActionType {
	LIKE("like", "li"), EQUAL("=", "eq"), NOTEQUAL("<>", "no"), LESSTHAN("<",
			"lt"), GREATERTHAN("<", "gt"), LESSTHANEQ("<=","le"),GREATERTHANEQ(">=", "ge");

	/**
	 * 查询动作
	 */
	private String action;

	/**
	 * 页面表示
	 */
	private String flage;

	private SqlActionType(String _action, String _flage) {
		this.action = _action;
		this.flage = _flage;
	}

	public String getFlage() {
		return this.flage;
	}

	public String getAction() {
		return this.action;
	}

	public static SqlActionType getDefault()
	{
		return EQUAL;
	}
	
	public static SqlActionType getSqlActionType(String _flage) {
		for (SqlActionType type : SqlActionType.values()) {
			if ( StringUtil.isEqual(type.getFlage(), _flage) ) {
				return type;
			}
		}
		return null;
	}
	
	public static void main(String args[])
	{
		SqlActionType type = SqlActionType.getSqlActionType("lt");
		System.out.println( type.action );
		System.out.println( type.flage );
	}
}
