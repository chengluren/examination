package org.dreamer.examination.sql.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dreamer.examination.exception.BaseException;
import org.dreamer.examination.exception.RepositoryException;
import org.dreamer.examination.repository.CommonRepositoryImpl;
import org.dreamer.examination.sql.model.SqlActionType;
import org.dreamer.examination.sql.model.SqlQueryItem;
import org.dreamer.examination.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 通过界面上传过来的动态查询参数构建动态查询对象模型
 * @author xwang
 *
 */
public class SqlQueryModelBuilder {
	private static Logger log = LoggerFactory.getLogger(CommonRepositoryImpl.class);
	public List<SqlQueryItem> builder( Map<String,? extends Object > _params )
	{
		 List<SqlQueryItem> items = buildQueryItems(_params);
		 return items;
	}

	
	protected List<SqlQueryItem> buildQueryItems( Map<String,? extends Object> _params )
	{
		List<SqlQueryItem> items = new ArrayList<SqlQueryItem>();
		Set<String> keySet = _params.keySet();
		for( String key : keySet )
		{
			SqlQueryItem qItem = createQueryItem( key , _params.get( key ) );
			if( qItem != null )
				items.add( qItem );
		}
		return items;
	}
		
	protected SqlQueryItem createQueryItem( String _properyName , Object _value )
	{
		SqlQueryItem sqlItem = new SqlQueryItem();
		List<String> pStack = StringUtil.splitStringToList( _properyName , "-");
		sqlItem.setPropertyName( pStack.get(0) );
		sqlItem.setValue( _value );
		if( pStack.size() > 1 )
		{
			SqlActionType type = SqlActionType.getSqlActionType( pStack.get(1) );
			if( type == null )
			{
				String tip = String.format("动态条件查询过程中，属性名[%s]，sql动作标识[%s]系统不识别", _properyName , pStack.get(1) );
				log.error(tip);
				new RepositoryException( tip );
			}
			sqlItem.setAction( type );
		}
		else
		{
			sqlItem.setAction( SqlActionType.getDefault() );
		}
		return sqlItem;
	}

	
	
}
