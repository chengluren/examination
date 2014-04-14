package org.dreamer.examination.sql.model;


/**
 * 动态查询排序样式，定义了排序字段和排序方式
 * @author xwang
 *
 */
public class SqlSortItem {
	private SqlSortType direction;
	private String propertyName;
	
	public SqlSortItem()
	{
		
	}
	
	public SqlSortItem( String _propString , SqlSortType _sortDirection )
	{
		this.setPropertyName(_propString);
	    this.setDirection( _sortDirection );
	}
	
	public SqlSortType getDirection() {
		return direction;
	}
	public void setDirection(SqlSortType direction) {
		this.direction = direction;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	
}
