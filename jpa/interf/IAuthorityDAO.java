package dao.interf;

import java.util.List;

import dao.pojo.Authority;
import dao.pojo.User;

public interface IAuthorityDAO extends IBaseDAO<Authority>{
	public List<Authority> getAuthorityByUser(User user) throws RuntimeException;  //获取用户权限
}
