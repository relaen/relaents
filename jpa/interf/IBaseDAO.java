package dao.interf;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

public interface IBaseDAO<T> {

	public EntityManager openTransactionEntityManager();

	public void closeEntityManager(EntityManager em);

	// 保存
	public T save(T entity) throws RuntimeException;

	// 编辑
	public T update(T entity) throws RuntimeException;

	// 删除
	public void delete(T entity) throws RuntimeException;

	// 批量添加
	public void batchAdd(List<T> lst) throws RuntimeException;

	// 批量修改
	public void batchUpd(List<T> lst) throws RuntimeException;

	// 批量删除
	public void batchDel(List<T> lst) throws RuntimeException;

	// 通过编号删除
	public void deleteById(Object id) throws RuntimeException, ClassNotFoundException;

	// 删除所有记录
	public void deleteAll() throws RuntimeException;

	// 通过属性删除
	public int deleteByProperties(final Map<String, Object> params) throws RuntimeException;

	// 通过属性删除
	public int deleteByProperties(final String[] pNames, final Object[] pValues, final String[] relation)
			throws RuntimeException;

	// 通过编号查找
	public T findById(Object id) throws RuntimeException, ClassNotFoundException;

	// 获取所有记录
	public List<T> findAll(String rule) throws RuntimeException;

	// 获取所有记录
	public List<T> findAll(final Integer start, final Integer max, String rule) throws RuntimeException;

	// 通过属性查找
	public List<T> findByProperties(final Map<String, Object> map) throws RuntimeException;

	// 通过属性查找
	public List<T> findByProperties(final Map<String, Object> map, final String[] relation, final Integer start,
			final Integer max) throws RuntimeException;

	// 通过属性查找（按规则排序）
	public List<T> findByPropertiesRule(final Map<String, Object> map, String rule) throws RuntimeException;

	// 通过属性查找（按规则排序）
	public List<T> findByPropertiesRule(final Map<String, Object> map, final String[] relation, final String rule,
			final Integer start, final Integer max) throws RuntimeException;

	// 通过属性查找（按规则排序）
	public List<T> findByProperties(String[] pNames, Object[] pValues) throws RuntimeException;

	// 通过属性查找
	public List<T> findByPropertiesRule(String[] propertyNames, final Object[] values, String rule)
			throws RuntimeException;

	// 通过属性查找（按规则排序）
	public List<T> findByPropertiesRule(final String[] pNames, final Object[] pValues, final String[] relation,
			final String rule, final Integer start, final Integer max) throws RuntimeException;

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
			final String[] modifiers) throws RuntimeException;

	// 通过属性查找并按要求返回结果（）
	public List<T> findByPropertiesRule(final String fieldStr, final String[] pNames, final Object[] pValues,
			final String[] logic, final String[] relation, final String rule, final Integer start, final Integer max,
			final String[] modifiers) throws RuntimeException;

	// 通过sql查询
	public List<T> findBySql(String sql, Integer start, Integer max);

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
	public List<T> findBySql(String sql, Object[] pValues, String[] relation, Integer start, Integer max);

	public List<T> findBySql(String sql, String[] pNames, Object[] pValues, String[] relation, Integer start,
			Integer max, String rule);

	public List<T> findBySql(String sql, String[] pNames, Object[] pValues, String[] relation, String[] logic,
			Integer start, Integer max, String rule);

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
			Integer max, String rule);

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
			Integer start, Integer max, String rule);

	/**
	 * 原生sql查询
	 * 
	 * @param sql
	 * @param clazz
	 * @return
	 */
	public List<T> findByNative(String sql, Class clazz);

	// 通过sql获取记录数
	public Long getCountBySql(String sql);

	/**
	 * 通过sql获取记录数
	 * 
	 * @param sql
	 * @param pValues
	 *            值数组
	 * @return
	 */
	public Long getCountBySql(String sql, Object[] pValues);

	/**
	 * 通过sql获取记录数
	 * 
	 * @param sql
	 * @param pNames
	 * @param pValues
	 * @param relation
	 * @return
	 */
	public Long getCountBySql(String sql, String[] pNames, Object[] pValues, String[] relation);

	/**
	 * 通过sql获取记录数
	 * 
	 * @param sql
	 * @param pNames
	 * @param pValues
	 * @param logic
	 * @param relation
	 * @return
	 */
	public Long getCountBySql(String sql, String[] pNames, Object[] pValues, String[] logic, String[] relation);

	// 获取count
	public Long getAllCount() throws RuntimeException;

	// 通过规则获取count
	public Long getAllCountByRule(String rule) throws RuntimeException;

	// 通过属性获取count
	public Long getPropertiesCount(final Map<String, Object> map, final String[] relation, final String rule)
			throws RuntimeException;

	// 通过属性获取count
	public Long getPropertiesCount(final String[] pNames, final Object[] pValues, final String[] relation,
			final String rule) throws RuntimeException;

	// 通过属性获取count
	public Long getPropertiesCount(final String[] pNames, final Object[] pValues, final String[] logic,
			final String[] relation, final String rule) throws RuntimeException;

	// 更新使能
	public Integer updateEnable(final String id, String enableString) throws RuntimeException, NoSuchFieldException;

	/**
	 * 执行sql
	 * 
	 * @param sql
	 *            sql串
	 * @param type
	 *            0 查询 1修改或删除
	 */
	public Object executeSql(String sql, Integer type) throws Exception;

	/**
	 * 自定义sql查询
	 * 
	 * @param sql
	 * @return
	 * @throws RuntimeException
	 */
	public List<Object[]> findByNativeSql(String sql) throws RuntimeException;
}