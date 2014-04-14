package org.dreamer.examination.repository;

import java.io.Serializable;
import java.util.List;

import org.dreamer.examination.sql.model.SqlQueryItem;
import org.dreamer.examination.sql.model.SqlSortItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 全局基础类库，提供了动态查询方法和自定义Sql（JPQL）的执行方法，对SpringData的JpaRepository类进行了扩展
 * @author xwang
 *
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface CommonRepository<T ,  ID extends Serializable> extends JpaRepository<T, ID> {
	 
	 /**
	  * 动态条件查询方法，提供根据条件动态拼装SQL执行查询的功能
	  * 主要用途是业务功能的动态条件输入搜索区域
	  * @param _paramList	  查询参数
	  * @param _sortList  需要排序的属性
	  * @param _pageable 结果记录开始位置、页数、每页条数
	  * @return	查询出的实体对象集合
	  */
	
	 public List<T> queryResult( List<SqlQueryItem> _paramList, List<SqlSortItem> _sortList, Pageable _pageable );
	 
	 /**
	  * 执行指定的JQPL,查询一个实体对象
	  * 如果查询条件不唯一将抛出异常
	  * SQL EXAMPLE: select t from Table t where t.name = ?1 and t.id = ?2
	  * @param _jpql 	执行的查询JPQL
	  * @param _params  JQPL所附带的参数，如果不带参数可以指定为Null
	  * @return 查询出的实体对象
	  */
	 public T find( String _jpql , Object[] _params );
	 
	 /**
	  * 执行指定的JQPL,查询结果对象集
	  * SQL EXAMPLE: select t from Table t where t.name = ?1 and t.id = ?2
	  * @param _jpql 	执行的查询JPQL
	  * @param _params  JQPL所附带的参数，如果不带参数可以指定为Null
	  * @return 查询出的实体对象集合
	  */
	 public List<T> findList( String _jpql , Object[] _params );
	 
	 /**
	  * 执行数据库更新动作，执行JPQL Sql
	  * @param _jpql   需要执行的JQPL动作
	  * @param _params JQPL所附带的参数，如果不带参数可以指定为Null
	  *  
	  * @return
	  */
	 public int excute(String _jpql, Object[] _params);
	 
	 /**
	  * 执行原生态sql语句，该SQL应该是对数据的操作类型SQL
	  * @param _nativeSql 原生态sql
	  * @param params 原生态所附带的参数，如果不带参数可以指定为Null
	  * @return 该sql语句执行后所影响的数据记录个数
	  */
	 public int excuteNativeSql(String _nativeSql, Object[] params);
	 
	 /**
	  * 执行原生态sql查询语句，查询一条记录
	  * 如果记过存在多条记录，将抛出异常
	  * @param _nativeSql  原生态sql
	  * @param _params	原生态所附带的参数，如果不带参数可以指定为Null
	  * @return 返回查询结果,Object[]，每个元素为记录的每个字段值
	  */
	 public Object queryNativeSql( String _nativeSql , Object[] _params );
	 
	 
	 /**
	  * 执行原生态sql查询语句,并将结果转换为数据对象
	  * @param _nativeSql	原生态sql
	  * @param _params	原生态所附带的参数，如果不带参数可以指定为Null
	  * @return 返回查询数据对象，对象类型为集成该接口的上层接口泛化时指定的具体操作实体类型
	  */
	 public T queryNativeSqlEntity( String _nativeSql , Object[] _params );
	 
	 /**
	  * 执行原生态sql查询语句，返回查询结果集
	  * @param _nativeSql  原生态sql
	  * @param _params	原生态所附带的参数，如果不带参数可以指定为Null
	  * @return 返回查询结果的集合数组，List<Object> ,其中Object为Object[]，每个元素为记录的每个字段值
	  */
	 public List<?> queryNativeSqlList( String _nativeSql , Object[] _params );

	 /**
	  * 执行原生态sql查询语句，返回查询结果对象集
	  * @param _nativeSql  原生态sql
	  * @param _params	原生态所附带的参数，如果不带参数可以指定为Null
	  * @return 返回查询结果的数据对象集合
	  */
	 public List<T> queryNativeSqlListEntity( String _nativeSql , Object[] _params );
	 

}
