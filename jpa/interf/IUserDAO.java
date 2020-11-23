package dao.interf;

import java.util.List;

import dao.pojo.Authority;
import dao.pojo.User;

public interface IUserDAO extends IBaseDAO<User>{
	//获取权限用户
	public List<User> getUsersByAuthority(final Authority auth) throws RuntimeException;
}
