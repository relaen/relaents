package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.interf.IAuthorityDAO;
import dao.pojo.Authority;
import dao.pojo.User;

public class AuthorityDAO extends BaseDAO<Authority> implements IAuthorityDAO{
	/**
	 * 获取用户权限
	 * @param user:用户
	 * @return authority list
	 */
	public List<Authority> getAuthorityByUser(final User user) throws RuntimeException{
		if(null == user)
			return null;
		final String qString = "select m1.authority from GroupAuthority m1,GroupMember m2 " +
				"where m2.user=:user and m1.group=m2.group";
    	EntityManager em = super.openTransactionEntityManager();
		Query queryObject = em.createQuery(qString);
    	queryObject.setParameter("user", user);
    	List<Authority> lst = (List<Authority>) queryObject.getResultList();
    	return lst;
	}
	
}
