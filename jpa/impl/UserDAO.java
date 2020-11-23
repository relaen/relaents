package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.interf.IUserDAO;
import dao.pojo.Authority;
import dao.pojo.User;

public class UserDAO extends BaseDAO<User> implements IUserDAO{
	/**
	 * 获取权限用户
	 * @param auth:权限
	 * @return user list
	 */
	public List<User> getUsersByAuthority(final Authority auth) throws RuntimeException{
		if(null == auth)
			return null;
		final String qString = "select m2.user from GroupAuthority m1,GroupMember m2 " +
				"where m1.authority=:auth and m1.group=m2.group";
		EntityManager em = super.openTransactionEntityManager();
		Query queryObject = em.createQuery(qString);
    	queryObject.setParameter("auth", auth);
    	List lst = queryObject.getResultList();
    	//super.closeEntityManager(em);
    	return lst;
	}	
}
