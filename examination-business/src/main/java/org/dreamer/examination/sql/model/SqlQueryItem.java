package org.dreamer.examination.sql.model;

import javax.persistence.criteria.Order;

/**
 * 动态查询定义，代表了一个查询条件
 * @author xwang
 *
 */
public class SqlQueryItem {
	private SqlActionType action;
	private Object value;
	private String propertyName;
	public SqlActionType getAction() {
		return action;
	}
	public void setAction(SqlActionType action) {
		this.action = action;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
}
