package org.dreamer.examination.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.dreamer.examination.sql.model.SqlActionType;
import org.dreamer.examination.sql.model.SqlQueryItem;
import org.dreamer.examination.sql.model.SqlSortItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.util.StringUtils;
@NoRepositoryBean
public class CommonRepositoryImpl<T, ID extends Serializable> extends
		SimpleJpaRepository<T, ID> implements CommonRepository<T, ID> {
	private static Logger log = LoggerFactory.getLogger(CommonRepositoryImpl.class);
	private EntityManager manager = null;
	private Class<?> cls = null;

	public CommonRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(JpaEntityInformationSupport.getMetadata(domainClass, em), em);
		manager = em;
		this.cls = domainClass;
	}

	protected EntityManager getManager() {
		return this.manager;
	}
	
	private Class<?> getCls()
	{
		return this.cls;
	}

	@Override
	public T find(String _jpql, Object[] _params) {
		// TODO Auto-generated method stub
		Query query = getManager().createQuery(_jpql);
		if (_params != null) {
			for (int i = 0; i < _params.length; i++) {
				query.setParameter(i + 1, _params[i]);
			}
		} else {
			log.info("find查询JPQL参数为空");
		}
		Object t = null;
		try {
			t = query.getSingleResult();
		} catch (NoResultException nre) {
			log.info("本查询条件没有查询到唯一的值");
			return null;
		}
		return (T) t;
	}

	@Override
	public List<T> findList(String _jpql, Object[] _params) {
		Query query = getManager().createQuery(_jpql);
		if (_params != null) {
			for (int i = 0; i < _params.length; i++) {
				query.setParameter(i + 1, _params[i]);
			}
		}
		return query.getResultList();
	}

	@Override
	public int excute(String _jpql, Object[] _params) {
		Query query = getManager().createQuery(_jpql);
		if (_params != null) {
			for (int i = 0; i < _params.length; i++) {
				query.setParameter(i + 1, _params[i]);
			}
		}
		return query.executeUpdate();
	}

	@Override
	public int excuteNativeSql(String _nativeSql, Object[] params) {
		Query query = getManager().createNativeQuery(_nativeSql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
		}
		return query.executeUpdate();
	}

	@Override
	public Object queryNativeSql(String _nativeSql, Object[] _params) {
		Query query = getManager().createNativeQuery(_nativeSql);
		if (_params != null) {
			for (int i = 0; i < _params.length; i++) {
				query.setParameter(i + 1, _params[i]);
			}
		}
		return query.getResultList();
	}

	@Override
	public T queryNativeSqlEntity(String _nativeSql, Object[] _params) {
		Query query = getManager().createNativeQuery(_nativeSql,getCls());
		if (_params != null) {
			for (int i = 0; i < _params.length; i++) {
				query.setParameter(i + 1, _params[i]);
			}
		}
		Object t = null;
		try {
			t = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (T)t;
	}

	@Override
	public List<?> queryNativeSqlList(String _nativeSql, Object[] _params) {
		Query query = getManager().createNativeQuery(_nativeSql);
		if (_params != null) {
			for (int i = 0; i < _params.length; i++) {
				query.setParameter(i + 1, _params[i]);
			}
		}
		return query.getResultList();
	}

	@Override
	public List<T> queryNativeSqlListEntity(String _nativeSql, Object[] _params) {
		Query query = getManager().createNativeQuery(_nativeSql,this.getCls() );
		if (_params != null) {
			for (int i = 0; i < _params.length; i++) {
				query.setParameter(i + 1, _params[i]);
			}
		}
		return query.getResultList();
	}

	@Override
	public List<T> queryResult(List<SqlQueryItem> _paramList,
			List<SqlSortItem> _sortList, Pageable _pageable) {
		String jpql = createQueryJpql(_paramList, _sortList);
		Query query = createQueryParam(jpql, _paramList, _pageable);
		log.info("执行Jpql Sql语句:" + jpql);
		return query.getResultList();
	}

	private String createQueryJpql(List<SqlQueryItem> _paramList,
			List<SqlSortItem> _sortList) {
		StringBuffer jpql = new StringBuffer("select t from ").append(
				this.cls.getSimpleName()).append(" t ");
		String filter = getFilter(_paramList);
		String order = getOrderBy(_sortList);
		if (!StringUtils.isEmpty(filter)) {
			jpql.append(filter);
		}
		if (!StringUtils.isEmpty(order)) {
			jpql.append(order);
		}
		return jpql.toString();
	}

	protected String getOrderBy(List<SqlSortItem> _sortList) {
		StringBuffer strOrderBy = new StringBuffer();
		if (_sortList != null && _sortList.size() > 0) {
			strOrderBy.append(" order by ");
			for (SqlSortItem item : _sortList) {
				strOrderBy.append(item.getPropertyName());
				strOrderBy.append(" ");
				strOrderBy.append(item.getDirection());
				strOrderBy.append(",");
			}
			strOrderBy.deleteCharAt(strOrderBy.length() - 1);
		}
		return strOrderBy.toString();
	}

	protected String getFilter(List<SqlQueryItem> _paramList) {
		if (_paramList == null || _paramList.size() == 0) {
			return "";
		}
		String result = "where ";
		for (SqlQueryItem item : _paramList) {
			result = result
					+ String.format(
							"t.%s %s :%s and ",
							new Object[] { item.getPropertyName(),
									item.getAction().getAction(),
									item.getPropertyName() });
		}
		result = result.substring(0, result.length() - 5);
		return result;
	}

	private Query createQueryParam(String _jpql, List<SqlQueryItem> _paramList,
			Pageable _pageable) {
		Query query = manager.createQuery(_jpql);
		if (_pageable != null) {
			int maxResult = _pageable.getPageNumber() * _pageable.getPageSize()
					+ _pageable.getOffset();
			query.setFirstResult(_pageable.getOffset());
			query.setMaxResults(maxResult);
		}
		if (_paramList != null) {
			for (SqlQueryItem param : _paramList) {
				Object value = param.getValue();
				if( param.getAction() == SqlActionType.LIKE )
				{
					String val = param.getValue().toString().trim();
					if( !val.startsWith("%") )
					{
						val = "%" + val;
					}
					if( !val.endsWith("%") )
					{
						val = val + "%";
					}
					value = val;
				}
				query.setParameter(param.getPropertyName(), value);
			}
		}
		return query;
	}

}
