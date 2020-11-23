package dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dao.interf.IBaseDAO;

@SuppressWarnings("unchecked")
public class BaseDAO<T> implements IBaseDAO<T> {
	private String TBLNAME = "";
	private String POJOCLASS = "";
	private Class clazz;
	protected final Log logger = LogFactory.getLog(getClass());

	@PersistenceContext(name = "rfapsPU", type = PersistenceContextType.TRANSACTION)
	public EntityManager entityManager;

	/**
	 * 关闭entityManager
	 * 
	 * @param em:要关闭的entitymanager
	 */
	public void closeEntityManager(EntityManager em) {
		return;
	}

	/**
	 * 打开entityManager 并 加入到transaction
	 * 
	 * @return 打开的entityManager
	 */
	public EntityManager openTransactionEntityManager() {
		return this.entityManager;
	}

	public BaseDAO() {
		// 获取泛型父类
		Type genType = getClass().getGenericSuperclass();
		// 获取对应的类数组
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (null == params || 0 == params.length)
			return;
		POJOCLASS = params[0].toString();
		POJOCLASS = POJOCLASS.substring(POJOCLASS.indexOf(' ') + 1);
		int x = POJOCLASS.lastIndexOf(".");
		if (x >= 0)
			TBLNAME = POJOCLASS.substring(x + 1);
		else
			TBLNAME = POJOCLASS;
	}

	/**
	 * select
	 * 
	 * @param queryString:需要查找的query串
	 * @return 返回的结果列表
	 */
	public List<T> find(String queryString) {
		EntityManager em = openTransactionEntityManager();
		Query query = em.createQuery(queryString);
		List lst = query.getResultList();
		closeEntityManager(em);
		return lst;
	}

	/**
	 * select
	 * 
	 * @param queryString:需要查找的query串
	 * @param pvalues:参数值数组
	 * @return 返回的结果列表
	 */
	public List<T> find(String queryString, Object[] pvalues) {
		EntityManager em = openTransactionEntityManager();
		Query query = em.createQuery(queryString);
		int le = pvalues.length;
		for (int i = 0; i < le; i++) {
			query.setParameter(i + 1, pvalues[i]);
		}

		List<T> lst = query.getResultList();
		closeEntityManager(em);
		return lst;
	}

	/**
	 * select
	 * 
	 * @param queryString:需要查找的query串
	 * @param map:参数map
	 * @return 返回的结果列表
	 */
	public List<T> find(String queryString, Map<String, Object> map) {
		EntityManager em = openTransactionEntityManager();
		Query query = em.createQuery(queryString);
		Set<Entry<String, Object>> set = map.entrySet();
		for (Entry<String, Object> en : set)
			query.setParameter(en.getKey(), en.getValue());
		List<T> lst = query.getResultList();
		closeEntityManager(em);
		return lst;
	}

	/**
	 * 保存（新增）
	 * 
	 * @param entity：要保存的实体
	 * @reutrn 返回已保存的实体
	 * @Exception throws RuntimeException
	 */
	public T save(T entity) throws RuntimeException {
		EntityManager em = openTransactionEntityManager();
		em.persist(entity);
		closeEntityManager(em);
		return entity;
	}

	/**
	 * 更新实体
	 * 
	 * @param entity：要更新的实体
	 * @return 返回更新后的实体
	 * @Exception throws RuntimeException
	 */
	public T update(T entity) throws RuntimeException {
		EntityManager em = openTransactionEntityManager();
		entity = em.merge(entity);
		closeEntityManager(em);
		return entity;
	}

	/**
	 * 删除实体
	 * 
	 * @param entity：要删除的实体
	 * @Exception throws RuntimeException
	 */
	public void delete(T entity) throws RuntimeException {
		if (null != entity) {
			EntityManager em = openTransactionEntityManager();
			entity = em.merge(entity);
			em.remove(entity);
			closeEntityManager(em);
		}
	}

	/**
	 * 批量添加
	 * 
	 * @param entity：要添加的实体列表
	 * @Exception throws RuntimeException
	 */
	public void batchAdd(List<T> lst) throws RuntimeException {
		EntityManager em = openTransactionEntityManager();
		for (T t : lst) {
			em.persist(t);
		}
		em.flush();
		em.clear();
		closeEntityManager(em);
	}

	/**
	 * 批量修改
	 * 
	 * @param lst：要修改的实体列表
	 * @Exception throws RuntimeException
	 */
	public void batchUpd(List<T> lst) throws RuntimeException {
		EntityManager em = openTransactionEntityManager();
		for (T t : lst) {
			em.merge(t);
		}
		em.flush();
		em.clear();
		closeEntityManager(em);
	}

	/**
	 * 批量删除
	 * 
	 * @param lst：要删除的实体列表
	 * @Exception throws RuntimeException
	 */
	public void batchDel(List<T> lst) throws RuntimeException {
		EntityManager em = openTransactionEntityManager();
		for (T t : lst) {
			em.remove(t);
		}
		em.flush();
		em.clear();
		closeEntityManager(em);
	}

	/**
	 * 通过编号删除实体
	 * 
	 * @param id：要删除的id
	 * @throws RuntimeException,
	 *             ClassNotFoundException
	 * @Exception throws RuntimeException, ClassNotFoundException
	 */
	public void deleteById(Object id) throws RuntimeException, ClassNotFoundException {
		delete(findById(id));
	}

	/**
	 * 删除所有实体
	 * 
	 * @throws RuntimeException
	 * @Exception throws RuntimeException
	 */
	public void deleteAll() throws RuntimeException {
		EntityManager em = openTransactionEntityManager();
		String queryString = "delete from " + TBLNAME;
		Query query = em.createQuery(queryString);
		query.executeUpdate();
		closeEntityManager(em);
	}

	/**
	 * 通过属性删除
	 * 
	 * @param map：值对映射
	 * @return 对象
	 * @Exception throws RuntimeException
	 */
	public int deleteByProperties(final Map<String, Object> map) throws RuntimeException {
		EntityManager em = openTransactionEntityManager();
		String queryString = "delete from " + TBLNAME + " model";
		Iterator<Entry<String, Object>> l = map.entrySet().iterator();
		Entry<String, Object> e = null;
		int index = 1;
		if (l.hasNext()) {
			e = l.next();
			queryString += " where model." + e.getKey() + " = ?" + index++;
		}
		int i = 2;
		for (; l.hasNext(); i++) {
			e = l.next();
			queryString += " and model." + e.getKey() + " = ?" + index++;
		}
		Query queryObject = em.createQuery(queryString);
		index = 1;
		for (l = map.entrySet().iterator(); l.hasNext();) {
			e = l.next();
			queryObject.setParameter(index++, e.getValue());
		}
		int x = queryObject.executeUpdate();
		closeEntityManager(em);
		return x;
	}

	/**
	 * 通过属性删除
	 * 
	 * @param pNames
	 *            参数名数组
	 * @param pValues
	 *            参数值数组ֵ
	 * @param relation
	 *            对应关系
	 * @return 成功 1
	 * @Exception throws RuntimeException
	 */
	public int deleteByProperties(final String[] pNames, final Object[] pValues, final String[] relation)
			throws RuntimeException {
		EntityManager em = openTransactionEntityManager();
		String queryString = "delete from " + TBLNAME + " model";
		if (null == relation)
			queryString += " where model." + pNames[0] + " = ?1";
		else
			queryString += " where model." + pNames[0] + " " + relation[0] + " ?1";

		for (int i = 1; i < pNames.length; i++) {
			if (null != relation)
				queryString += " and model." + pNames[i] + " " + relation[i] + " ?" + (i + 1);
			else
				queryString += " and model." + pNames[i] + " = ?" + (i + 1);
		}

		Query queryObject = em.createQuery(queryString);
		for (int i = 0; i < pValues.length; i++) {
			if (null != relation && "like".equals(relation[i]))
				queryObject.setParameter((i + 1), "%" + pValues[i] + "%");
			else
				queryObject.setParameter((i + 1), pValues[i]);
		}
		int x = queryObject.executeUpdate();
		closeEntityManager(em);
		return x;
	}

	/**
	 * 通过编号查找
	 * 
	 * @param id：对象id
	 * @return 查找到的对象
	 * @Exception throws RuntimeException, ClassNotFoundException
	 */
	public T findById(Object id) throws RuntimeException, ClassNotFoundException {
		if (null == id)
			return null;
		EntityManager em = openTransactionEntityManager();
		T x = (T) em.find(Class.forName(POJOCLASS), id);
		closeEntityManager(em);
		return x;
	}

	/**
	 * 获取所有
	 * 
	 * @param rule：排序规则
	 * @return 记录列表
	 * @Exception throws RuntimeException
	 */
	public List<T> findAll(String rule) throws RuntimeException {
		String qstr = "select model from " + TBLNAME + " model ";
		if (null != rule)
			qstr += rule;
		String queryString = qstr;
		return find(queryString);
	}

	/**
	 * 获取所有记录
	 * 
	 * @param start：开始记录
	 * @param max：最大记录数
	 * @param rule:
	 *            规则
	 * @return 记录列表
	 * @Exception throws RuntimeException
	 */
	public List<T> findAll(final Integer start, final Integer max, String rule) throws RuntimeException {
		String qstr = "select model from " + TBLNAME + " model ";
		if (null != rule)
			qstr += rule;
		final String queryString = qstr;
		EntityManager em = openTransactionEntityManager();
		Query queryObject = em.createQuery(queryString);
		if (null != start && null != max && start >= 0 && max > 0) {
			queryObject.setFirstResult(start);
			queryObject.setMaxResults(max);
		}
		List<T> lst = queryObject.getResultList();
		closeEntityManager(em);
		return lst;

	}

	/**
	 * 通过属性查找
	 * 
	 * @param map：键值映射
	 * @return 记录列表
	 * @Exception throws RuntimeException
	 */
	public List<T> findByProperties(final Map<String, Object> map) throws RuntimeException {
		if (null == map || map.isEmpty())
			return findAll(null);
		String queryString = "select model from " + TBLNAME + " model";
		Iterator<Entry<String, Object>> l = map.entrySet().iterator();
		Entry<String, Object> e = l.next();
		queryString += " where model." + e.getKey() + " = :" + e.getKey();
		int i = 2;
		for (; l.hasNext(); i++) {
			e = l.next();
			queryString += " and model." + e.getKey() + " = :" + e.getKey();
		}
		EntityManager em = openTransactionEntityManager();
		Query query = em.createQuery(queryString);
		Set<Entry<String, Object>> set = map.entrySet();
		for (Entry<String, Object> ent : set) {
			query.setParameter(ent.getKey(), ent.getValue());
		}
		List<T> lst = query.getResultList();
		closeEntityManager(em);
		return lst;
	}

	/**
	 * 通过属性查找
	 * 
	 * @param pNames：属性数组
	 * @param pValues：
	 *            值数组
	 * @return 记录列表
	 * @Exception throws RuntimeException
	 */
	public List<T> findByProperties(String[] pNames, Object[] pValues) throws RuntimeException {
		if (pNames.length == 0)
			return findAll(null);
		String queryString = "select model from " + TBLNAME + " model where model." + pNames[0] + "= ?1";
		for (int i = 1; i < pNames.length; i++) {
			queryString += " and model." + pNames[i] + "= ?" + (i + 1);
		}
		return find(queryString, pValues);
	}

	/**
	 * 通过属性查找
	 * 
	 * @param map：键值映射
	 * @param start:开始记录
	 * @param max:记录数
	 * @return 记录列表
	 * @Exception throws RuntimeException
	 */
	public List<T> findByProperties(final Map<String, Object> map, final String[] relation, final Integer start,
			final Integer max) throws RuntimeException {
		if (null == map || map.isEmpty())
			return findAll(start, max, null);
		String queryString = "select model from " + TBLNAME + " model ";
		Iterator<Entry<String, Object>> l = map.entrySet().iterator();
		Entry<String, Object> e = l.next();
		if (null == relation)
			queryString += " where model." + e.getKey() + " = ?1";
		else
			queryString += " where model." + e.getKey() + " " + relation[0] + " ?1";
		int i = 2;
		for (; l.hasNext(); i++) {
			e = l.next();
			if (null == relation)
				queryString += " and model." + e.getKey() + " = ?" + i;
			else
				queryString += " and model." + e.getKey() + " " + relation[i - 1] + " ?" + i;
		}
		EntityManager em = openTransactionEntityManager();

		Query queryObject = em.createQuery(queryString);
		i = 1;
		for (l = map.entrySet().iterator(); l.hasNext(); i++) {
			e = l.next();
			if (null != relation && "like".equals(relation[i - 1]))
				queryObject.setParameter(i, "%" + e.getValue() + "%");
			else
				queryObject.setParameter(i, e.getValue());
		}
		if (null != start && null != max && start >= 0 && max > 0) {
			queryObject.setFirstResult(start);
			queryObject.setMaxResults(max);
		}
		List<T> lst = queryObject.getResultList();
		closeEntityManager(em);
		return lst;
	}

	/**
	 * 通过属性查找（按规则排序）
	 * 
	 * @param map：键值映射
	 * @param start:
	 *            开始记录
	 * @param max:最大记录数
	 * @param rule:
	 *            规则
	 * @return 记录列表
	 * @Exception throws RuntimeException
	 */
	public List<T> findByPropertiesRule(Map<String, Object> map, String rule) throws RuntimeException {
		if (null == map || map.isEmpty())
			return findAll(rule);
		String queryString = "select model from " + TBLNAME + " model";
		Iterator<Entry<String, Object>> l = map.entrySet().iterator();
		Entry<String, Object> e = l.next();
		queryString += " where model." + e.getKey() + " = :" + e.getKey();
		for (; l.hasNext();) {
			e = l.next();
			queryString += " and model." + e.getKey() + " = :" + e.getKey();
		}
		if (null != rule)
			queryString += " " + rule;
		EntityManager em = openTransactionEntityManager();
		Query query = em.createQuery(queryString);
		Set<Entry<String, Object>> set = map.entrySet();
		for (Entry<String, Object> ent : set) {
			query.setParameter(ent.getKey(), ent.getValue());
		}
		List<T> lst = query.getResultList();
		closeEntityManager(em);
		return lst;
	}

	/**
	 * 通过属性查找（按规则排序）
	 * 
	 * @param pNames：参数数组
	 * @param pValues:值数组
	 * @param start：开始记录
	 * @param max：最大记录
	 * @param rule：排序规则
	 * @return 记录列表
	 * @Exception throws RuntimeException
	 */
	public List<T> findByPropertiesRule(String[] pNames, final Object[] pValues, String rule) throws RuntimeException {
		String queryString = "select model from " + TBLNAME + " model where model." + pNames[0] + "= ?1";
		for (int i = 1; i < pNames.length; i++) {
			queryString += " and model." + pNames[i] + "= ?" + (i + 1);
		}
		if (null != rule)
			queryString += " " + rule;
		return find(queryString, pValues);
	}

	/**
	 * 通过属性查找（按规则排序）
	 * 
	 * @param map：键值映射
	 * @param relation:键值关系
	 * @param rule：排序规则
	 * @param start：起始记录
	 * @param max：记录数
	 * @return 记录列表
	 * @Exception throws RuntimeException
	 */
	public List<T> findByPropertiesRule(final Map<String, Object> map, final String[] relation, final String rule,
			final Integer start, final Integer max) throws RuntimeException {
		if (null == map || map.isEmpty())
			return findAll(start, max, null);
		Iterator<Entry<String, Object>> l = map.entrySet().iterator();
		Entry<String, Object> e = l.next();
		String queryString = "select model from " + TBLNAME + " model";
		if (null == relation)
			queryString += " where model." + e.getKey() + " = ?1";
		else
			queryString += " where model." + e.getKey() + " " + relation[0] + " ?1";
		int i = 2;
		for (; l.hasNext(); i++) {
			e = l.next();
			if (null != relation)
				queryString += " and model." + e.getKey() + " " + relation[i - 1] + " ?" + i;
			else
				queryString += " and model." + e.getKey() + " = ?" + i;
		}
		if (null != rule)
			queryString += " " + rule;
		EntityManager em = openTransactionEntityManager();
		Query queryObject = em.createQuery(queryString);
		i = 1;
		for (l = map.entrySet().iterator(); l.hasNext(); i++) {
			e = l.next();
			if (null != relation && "like".equals(relation[i - 1]))
				queryObject.setParameter(i, "%" + e.getValue() + "%");
			else
				queryObject.setParameter(i, e.getValue());
		}
		if (null != start && null != max && start >= 0 && max > 0) {
			queryObject.setFirstResult(start);
			queryObject.setMaxResults(max);
		}
		List<T> lst = queryObject.getResultList();
		this.closeEntityManager(em);
		return lst;
	}

	/**
	 * 通过属性查找（按规则排序）
	 * 
	 * @param pNames：参数数组
	 * @param pValues：值数组
	 * @param relation：参数值关系
	 * @param rule：规则
	 * @param start：开始记录
	 * @param max：记录数
	 * @return 记录列表
	 * @Exception throws RuntimeException
	 */
	public List<T> findByPropertiesRule(final String[] pNames, Object[] pValues, final String[] relation,
			final String rule, final Integer start, final Integer max) throws RuntimeException {
		if (null == pNames)
			return findAll(start, max, rule);
		if (pNames.length == 0)
			return findAll(start, max, rule);

		String queryString = "select model from " + TBLNAME + " model";
		List<Object> lstValue = new ArrayList<Object>();
		if (pValues != null) {
			for (Object o : pValues) {
				lstValue.add(o);
			}
		}
		queryString += createWhere(pNames, lstValue, relation, null);
		pValues = lstValue.toArray();

		if (null != rule)
			queryString += " " + rule;

		EntityManager em = openTransactionEntityManager();
		Query queryObject = em.createQuery(queryString);
		setParameter(queryObject, pValues, relation);
		if (null != start && null != max && start >= 0 && max > 0) {
			queryObject.setFirstResult(start);
			queryObject.setMaxResults(max);
		}
		List<T> lst = queryObject.getResultList();
		this.closeEntityManager(em);
		return lst;
	}

	/**
	 * 通过属性查找并按要求返回结果
	 * 
	 * @param pNames：参数数组
	 * @param pValues：值数组
	 * @param logic：逻辑数组
	 * @param relation：参数值关系
	 * @param rule：规则
	 * @param start：开始记录
	 * @param max：记录数
	 * @return 记录列表
	 * @Exception throws RuntimeException
	 */
	public List<T> findByPropertiesRule(final String[] pNames, Object[] pValues, final String[] logic,
			final String[] relation, final String rule, final Integer start, final Integer max,
			final String[] modifiers) throws RuntimeException {

		if (pNames.length == 0)
			return findAll(start, max, rule);

		String queryString = "select model from " + TBLNAME + " model";
		List<Object> lstValue = new ArrayList<Object>();
		if (pValues != null) {
			for (Object o : pValues) {
				lstValue.add(o);
			}
		}
		queryString += createWhere(pNames, lstValue, relation, logic);
		pValues = lstValue.toArray();

		if (null != rule)
			queryString += " " + rule;

		EntityManager em = openTransactionEntityManager();
		Query queryObject = em.createQuery(queryString);
		setParameter(queryObject, pValues, relation);
		if (null != start && null != max && start >= 0 && max > 0) {
			queryObject.setFirstResult(start);
			queryObject.setMaxResults(max);
		}
		List<T> lst = queryObject.getResultList();
		this.closeEntityManager(em);
		return lst;
	}

	/**
	 * 通过属性查找并按要求返回结果
	 * 
	 * @param pNames：参数数组
	 * @param pValues：值数组
	 * @param logic：逻辑数组
	 * @param relation：参数值关系
	 * @param rule：规则
	 * @param start：开始记录
	 * @param max：记录数
	 * @return 记录列表
	 * @Exception throws RuntimeException
	 */
	public List findByPropertiesRule(final String resultStr, final String[] pNames, Object[] pValues,
			final String[] logic, final String[] relation, final String rule, final Integer start, final Integer max,
			final String[] modifiers) throws RuntimeException {

		String[] fjs = createFieldsAndJoin(resultStr, modifiers);
		String queryString = "select " + fjs[0] + " from " + TBLNAME + " model" + " " + fjs[1];
		List<Object> lstValue = new ArrayList<Object>();
		if (pValues != null) {
			for (Object o : pValues) {
				lstValue.add(o);
			}
		}
		queryString += createWhere(pNames, lstValue, relation, logic);
		pValues = lstValue.toArray();
		if (null != rule)
			queryString += " " + rule;
		EntityManager em = openTransactionEntityManager();
		Query queryObject = em.createQuery(queryString);
		setParameter(queryObject, pValues, relation);
		if (null != start && null != max && start >= 0 && max > 0) {
			queryObject.setFirstResult(start);
			queryObject.setMaxResults(max);
		}
		List lst = queryObject.getResultList();
		this.closeEntityManager(em);
		return lst;
	}

	/**
	 * 通过sql查询
	 * 
	 * @param sql
	 * @param start
	 * @param max
	 * @return
	 */
	public List<T> findBySql(String sql, Integer start, Integer max) {
		EntityManager em = openTransactionEntityManager();
		Query queryObject = em.createQuery(sql);

		if (null != start && null != max && start >= 0 && max > 0) {
			queryObject.setFirstResult(start);
			queryObject.setMaxResults(max);
		}
		List<T> lst = queryObject.getResultList();
		this.closeEntityManager(em);
		return lst;
	}

	/**
	 * 通过sql查询
	 * 
	 * @param sql
	 * @param pValues:参数值
	 * @param relation:关系
	 * @param start
	 * @param max
	 * @return
	 */
	public List<T> findBySql(String sql, Object[] pValues, String[] relation, Integer start, Integer max) {
		EntityManager em = openTransactionEntityManager();
		Query queryObject = em.createQuery(sql);
		setParameter(queryObject, pValues, relation);
		if (null != start && null != max && start >= 0 && max > 0) {
			queryObject.setFirstResult(start);
			queryObject.setMaxResults(max);
		}
		List<T> lst = queryObject.getResultList();
		this.closeEntityManager(em);
		return lst;
	}

	/**
	 * 
	 * @param sql
	 * @param pNames
	 * @param pValues
	 * @param relation
	 * @param start
	 * @param max
	 * @return
	 */

	public List<T> findBySql(String sql, String[] pNames, Object[] pValues, String[] relation, String[] logic,
			Integer start, Integer max, String rule) {
		List<Object> lstValue = new ArrayList<Object>();
		if (pValues != null) {
			for (Object o : pValues) {
				lstValue.add(o);
			}
		}
		sql += createWhere(pNames, lstValue, relation, logic);
		pValues = lstValue.toArray();
		if (rule != null)
			sql += " " + rule;
		EntityManager em = openTransactionEntityManager();
		Query queryObject = em.createQuery(sql);
		setParameter(queryObject, pValues, relation);
		if (null != start && null != max && start >= 0 && max > 0) {
			queryObject.setFirstResult(start);
			queryObject.setMaxResults(max);
		}
		List<T> lst = queryObject.getResultList();
		this.closeEntityManager(em);
		return lst;
	}

	/**
	 * 
	 * @param sql
	 * @param pNames
	 * @param pValues
	 * @param relation
	 * @param start
	 * @param max
	 * @return
	 */

	public List<T> findBySql(String sql, String[] pNames, Object[] pValues, String[] relation, Integer start,
			Integer max, String rule) {
		List<Object> lstValue = new ArrayList<Object>();
		if (pValues != null) {
			for (Object o : pValues) {
				lstValue.add(o);
			}
		}
		sql += createWhere(pNames, lstValue, relation, null);
		pValues = lstValue.toArray();
		if (rule != null)
			sql += " " + rule;
		EntityManager em = openTransactionEntityManager();
		Query queryObject = em.createQuery(sql);
		setParameter(queryObject, pValues, relation);
		if (null != start && null != max && start >= 0 && max > 0) {
			queryObject.setFirstResult(start);
			queryObject.setMaxResults(max);
		}
		List<T> lst = queryObject.getResultList();
		this.closeEntityManager(em);
		return lst;
	}

	/**
	 * 
	 * @param sql
	 * @param pNames
	 * @param pValues
	 * @param relation
	 * @param start
	 * @param max
	 * @return
	 */

	public Object findObjBySql(String sql, String[] pNames, Object[] pValues, String[] relation, Integer start,
			Integer max, String rule) {
		return findObjBySql(sql, pNames, pValues, null, relation, start, max, rule);
	}

	/**
	 * 
	 * @param sql
	 * @param pNames
	 * @param pValues
	 * @param logic
	 * @param relation
	 * @param start
	 * @param max
	 * @return
	 */

	public Object findObjBySql(String sql, String[] pNames, Object[] pValues, String[] logic, String[] relation,
			Integer start, Integer max, String rule) {
		List<Object> lstValue = new ArrayList<Object>();
		if (pValues != null) {
			for (Object o : pValues) {
				lstValue.add(o);
			}
		}
		sql += createWhere(pNames, lstValue, relation, logic);
		pValues = lstValue.toArray();
		if (rule != null)
			sql += " " + rule;
		EntityManager em = openTransactionEntityManager();
		Query queryObject = em.createQuery(sql);
		setParameter(queryObject, pValues, relation);
		if (null != start && null != max && start >= 0 && max > 0) {
			queryObject.setFirstResult(start);
			queryObject.setMaxResults(max);
		}
		Object obj = queryObject.getSingleResult();
		this.closeEntityManager(em);
		return obj;
	}

	/**
	 * 原生sql查询
	 * 
	 * @param sql
	 * @param clazz
	 * @return
	 */
	public List<T> findByNative(String sql, Class clazz) {
		EntityManager em = openTransactionEntityManager();
		Query queryObject = em.createNativeQuery(sql, clazz);
		return queryObject.getResultList();
	}

	/**
	 * 通过sql获取记录数
	 * 
	 * @param sql
	 * @return
	 */
	public Long getCountBySql(String sql) {
		EntityManager em = openTransactionEntityManager();
		Query queryObject = em.createQuery(sql);
		Long x = (Long) queryObject.getSingleResult();
		this.closeEntityManager(em);
		return x;
	}

	/**
	 * 通过sql获取记录数
	 * 
	 * @param sql
	 * @param pValues
	 *            值数组
	 * @return
	 */
	public Long getCountBySql(String sql, Object[] pValues) {
		EntityManager em = openTransactionEntityManager();
		Query queryObject = em.createQuery(sql);
		setParameter(queryObject, pValues, null);
		Long x = (Long) queryObject.getSingleResult();
		this.closeEntityManager(em);
		return x;
	}

	/**
	 * 通过sql获取记录数
	 * 
	 * @param sql
	 * @param pNames
	 * @param pValues
	 * @param relation
	 * @return
	 */
	public Long getCountBySql(String sql, String[] pNames, Object[] pValues, String[] relation) {
		return getCountBySql(sql, pNames, pValues, null, relation);
	}

	/**
	 * 通过sql获取记录数
	 * 
	 * @param sql
	 * @param pNames
	 * @param pValues
	 * @param relation
	 * @return
	 */
	public Long getCountBySql(String sql, String[] pNames, Object[] pValues, String[] logic, String[] relation) {
		List<Object> lstValue = new ArrayList<Object>();
		if (pValues != null) {
			for (Object o : pValues) {
				lstValue.add(o);
			}
		}
		sql += createWhere(pNames, lstValue, relation, logic);
		pValues = lstValue.toArray();

		EntityManager em = openTransactionEntityManager();
		Query queryObject = em.createQuery(sql);
		setParameter(queryObject, pValues, relation);
		Long x = (Long) queryObject.getSingleResult();
		this.closeEntityManager(em);
		return x;
	}

	/**
	 * 获取所有记录数
	 * 
	 * @return 记录数
	 * @Exception throws RuntimeException
	 */
	public Long getAllCount() throws RuntimeException {
		final String queryString = "select count(model) from " + TBLNAME + " model";
		EntityManager em = openTransactionEntityManager();
		Query queryObject = em.createQuery(queryString);
		Long x = (Long) queryObject.getSingleResult();
		this.closeEntityManager(em);
		return x;
	}

	/**
	 * 获取规则记录数
	 * 
	 * @param rule：规则
	 * @return 记录数
	 * @Exception throws RuntimeException
	 */
	public Long getAllCountByRule(String rule) throws RuntimeException {
		String qstr = "select count(model) from " + TBLNAME + " model ";
		if (null != rule)
			qstr += rule;
		final String queryString = qstr;
		EntityManager em = openTransactionEntityManager();
		Query queryObject = em.createQuery(queryString);
		Long x = (Long) queryObject.getSingleResult();
		this.closeEntityManager(em);
		return x;
	}

	/**
	 * 获取属性记录数
	 * 
	 * @param map：键值映射
	 * @param relation：关系
	 * @param rule：规则
	 * @return 记录数
	 * @Exception throws RuntimeException
	 */
	public Long getPropertiesCount(final Map<String, Object> map, final String[] relation, final String rule)
			throws RuntimeException {
		if (null == map || map.isEmpty())
			return getAllCountByRule(rule);
		String queryString = "select count(model) from " + TBLNAME + " model";
		Iterator<Entry<String, Object>> l = map.entrySet().iterator();
		Entry<String, Object> e = l.next();
		if (null == relation)
			queryString += " where model." + e.getKey() + " = ?1";
		else
			queryString += " where model." + e.getKey() + " " + relation[0] + " ?1";
		int i = 2;
		for (; l.hasNext(); i++) {
			e = l.next();
			if (null != relation)
				queryString += " and model." + e.getKey() + " " + relation[i - 1] + " ?" + i;
			else
				queryString += " and model." + e.getKey() + " = ?" + i;
		}
		if (null != rule)
			queryString += " " + rule;
		EntityManager em = openTransactionEntityManager();
		Query queryObject = em.createQuery(queryString);
		i = 1;
		for (l = map.entrySet().iterator(); l.hasNext(); i++) {
			e = l.next();
			if (null != relation && "like".equals(relation[i - 1]))
				queryObject.setParameter(i, "%" + e.getValue() + "%");
			else
				queryObject.setParameter(i, e.getValue());
		}
		Long x = (Long) queryObject.getSingleResult();
		this.closeEntityManager(em);
		return x;
	}

	/**
	 * 获取属性记录数
	 * 
	 * @param pNames：参数数组
	 * @param pValues:
	 *            值数组
	 * @param relation：关系
	 * @param rule：规则
	 * @return 记录数
	 * @Exception throws RuntimeException
	 */
	public Long getPropertiesCount(final String[] pNames, Object[] pValues, final String[] relation, final String rule)
			throws RuntimeException {

		if (null == pNames)
			return getAllCount();
		int len = pNames.length;
		if (len == 0)
			return getAllCount();
		String queryString = "select count(model) from " + TBLNAME + " model";
		List<Object> lstValue = new ArrayList<Object>();
		if (pValues != null) {
			for (Object o : pValues) {
				lstValue.add(o);
			}
		}
		queryString += createWhere(pNames, lstValue, relation, null);
		pValues = lstValue.toArray();
		EntityManager em = openTransactionEntityManager();
		Query queryObject = em.createQuery(queryString);
		setParameter(queryObject, pValues, relation);
		Long x = (Long) queryObject.getSingleResult();
		this.closeEntityManager(em);
		return x;

	}

	/**
	 * 获取属性记录数
	 * 
	 * @param pNames：参数数组
	 * @param pValues:
	 *            值数组
	 * @param relation：关系
	 * @param rule：规则
	 * @return 记录数
	 * @Exception throws RuntimeException
	 */
	public Long getPropertiesCount(final String[] pNames, Object[] pValues, final String[] logic,
			final String[] relation, final String rule) throws RuntimeException {
		if (null == pNames || pNames.length == 0)
			return getAllCount();
		String queryString = "select count(model) from " + TBLNAME + " model ";
		StringBuffer where = new StringBuffer();
		where.append(" where ");

		List<Object> lstValue = new ArrayList<Object>();
		if (pValues != null) {
			for (Object o : pValues) {
				lstValue.add(o);
			}
		}
		queryString += createWhere(pNames, lstValue, relation, logic);
		pValues = lstValue.toArray();
		EntityManager em = openTransactionEntityManager();
		Query queryObject = em.createQuery(queryString);
		setParameter(queryObject, pValues, relation);
		Long x = (Long) queryObject.getSingleResult();
		this.closeEntityManager(em);
		return x;
	}

	/**
	 * 更新使能
	 * 
	 * @param id:id类型
	 * @param enableString
	 *            id1|0,id2|1...
	 * @return 成功1
	 * @Exception throws RuntimeException, NoSuchFieldException
	 */
	public Integer updateEnable(final String id, String enableString) throws RuntimeException, NoSuchFieldException {
		String[] strs = enableString.split(",");
		final int len = strs.length;
		final String[] ids = new String[len];
		final String[] flags = new String[len];
		// 分拆数组
		for (int i = 0; i < len; i++) {
			String[] xx = strs[i].split("\\|");
			ids[i] = xx[0];
			flags[i] = xx[1];
		}
		final String idType = this.clazz.getDeclaredField(id).getType().getName();
		String queryString = "update " + TBLNAME + " model set model.enabled = :enabled where model." + id + " = :id";
		EntityManager em = openTransactionEntityManager();
		for (int i = 0; i < len; i++) {
			Query queryObject = em.createQuery(queryString);
			if ("1".equals(flags[i]))
				queryObject.setParameter("enabled", "Y");
			else if ("0".equals(flags[i]))
				queryObject.setParameter("enabled", "N");
			else
				continue;
			Object id1;
			if ("java.lang.Integer".equals(idType))
				id1 = Integer.parseInt(ids[i]);
			else if ("java.lang.Long".equals(idType)) {
				id1 = Long.parseLong(ids[i]);
			} else
				id1 = ids[i];
			queryObject.setParameter("id", id1);
			queryObject.executeUpdate();
		}
		this.closeEntityManager(em);
		return 1;
	}

	/**
	 * 执行sql
	 * 
	 * @param sql
	 *            sql串
	 * @param type
	 *            0 查询 1修改或删除
	 */
	public Object executeSql(String sql, Integer type) throws Exception {
		EntityManager em = openTransactionEntityManager();
		Query query = em.createQuery(sql);
		if (type == 1) {
			return query.executeUpdate();
		}
		return query.getResultList();
	}

	// 构造where 条件
	private String createWhere(String[] pNames, List lstValue, String[] relation, String[] logic) {
		if (null == pNames || null == lstValue)
			return "";
		int len = pNames.length;
		if (len == 0)
			return "";
		StringBuffer where = new StringBuffer();
		where.append(" where ");
		int logicInd = 0, logicLen = 0;

		boolean hasLogic = false, hasRelation = false;
		if (null != logic && logic.length > 0) {
			logicLen = logic.length;
			hasLogic = true;
		}
		if (null != relation && relation.length > 0)
			hasRelation = true;

		for (int i = 0, index = 0; i < len; i++, index++) {
			// 第一个不添加 逻辑
			if (where.length() > 10) {
				if (hasLogic)
					where.append(" ").append(logic[logicInd++]).append(" "); // 处理逻辑
				else
					where.append(" and ");
			}

			if (hasLogic && logicInd < logicLen && "(".equals(logic[logicInd]))// 处理括号
				where.append(logic[logicInd++]);
			String name = pNames[i];
			// $开头的字段添加model，并需要把$去掉
			if (name.startsWith("$")) {
				where.append(" " + name.substring(1));
			} else {
				where.append(" model." + name);
			}

			if (hasRelation) {
				where.append(" " + relation[i]);
				if (lstValue.get(index) == null) {
					// 从value删除null元素
					lstValue.remove(index);
					index--;
				} else {
					where.append(" ?" + (index + 1));
				}
			} else {
				where.append(" =?" + (i + 1));
			}
			// 处理括号
			if (hasLogic && logicInd < logic.length && (")".equals(logic[logicInd])))
				where.append(logic[logicInd++]);
		}
		return where.toString();
	}

	// 设置参数
	private void setParameter(Query queryObject, Object[] pValues, String[] relation) {
		if (null == pValues)
			return;
		int len = pValues.length;
		int xInd = 0;
		for (int i = 0; i < len; i++) {
			if (pValues[i] == null) {
				continue;
			}
			if (null != relation && "like".equals(relation[i]))
				queryObject.setParameter((i + 1), "%" + pValues[i] + "%");
			else
				queryObject.setParameter((i + 1), pValues[i]);
		}
	}

	/**
	 * 解析field 和 join table
	 * 
	 * @param fieldStr:字段字符串
	 * @return string[] 0fields 1join table
	 */
	private String[] createFieldsAndJoin(String fieldStr, String[] modifiers) {
		String[] fieldArr = fieldStr.split(",");
		Map<String, String> map = new HashMap<String, String>();
		StringBuffer fields = new StringBuffer();
		StringBuffer join = new StringBuffer();
		boolean notFirst = false;
		boolean hasModifier = false;
		if (null != modifiers && modifiers.length > 0)
			hasModifier = true;

		for (int i = 0, ind = 1, len = fieldArr.length; i < len; i++) {
			String s = fieldArr[i];
			String[] sp = s.split("\\.");
			if (sp.length == 1) {
				if (notFirst)
					fields.append(",");
				notFirst = true;
				if (hasModifier)
					fields.append(" ").append(modifiers[i]).append(" ");
				if ("*".endsWith(s))
					fields.append("model");
				else
					fields.append("model." + s);
			} else {
				// 最后一个为字段
				// 最后一个为字段
				String lastT = "";
				for (int j = 1, len1 = sp.length; j < len1; j++) {
					String t = "";
					int k = 0;
					for (; k < j; k++) {
						if (k > 0)
							t += ".";
						t += sp[k];
					}
					if (!map.containsKey(t)) {
						map.put(t, "model" + ind);
						join.append(" left join ");
						// 处理表前缀
						String tPre = "model.";
						if (!StringUtils.isEmpty(lastT)) {
							String stmp = map.get(lastT);
							if (null != stmp)
								tPre = stmp + ".";
						}

						join.append(tPre).append(sp[k - 1]).append(" ").append("model").append(ind);
						ind++;
					}
					lastT = t;
				}
				int ind1 = s.lastIndexOf(".");
				if (notFirst)
					fields.append(",");
				notFirst = true;
				if (hasModifier)
					fields.append(" ").append(modifiers[i]).append(" ");
				fields.append(map.get(s.substring(0, ind1)) + "." + s.substring(ind1 + 1));
			}
		}
		return new String[] { fields.toString(), join.toString() };
	}

	/**
	 * 自定义sql查询
	 * 
	 * @param sql
	 * @return
	 * @throws RuntimeException
	 */
	public List<Object[]> findByNativeSql(String sql) throws RuntimeException {
		EntityManager em = openTransactionEntityManager();
		Query queryObject = em.createNativeQuery(sql);
		List<Object[]> lst = queryObject.getResultList();
		return lst;
	}
}
