package dao.pojo;

import javax.persistence.TableGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
// default package
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_user", catalog = "tiandian")
public class User extends BasePojo implements java.io.Serializable {
	// Fields
	private Long user_id;
	private String user_name;
	private String user_pwd;
	private Token token1;
	private Integer enabled;
	private Member member;
	
	private Set<GroupMember> groupMembers = new HashSet<GroupMember>(0);
	private Set<Login> logins = new HashSet<Login>(0);
	

	// Constructors
	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(Long user_id) {
		this.user_id = user_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "USER_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_USER", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "USER_GEN")
	@Column(name = "user_id", unique = true, nullable = false)
	public Long getUser_id() {
		return this.user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	@Column(name = "user_name", length = 50)
	public String getUser_name() {
		return this.user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	@Column(name = "user_pwd", length = 32)
	public String getUser_pwd() {
		return this.user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	
	@Column(name = "enabled")
	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<GroupMember> getGroupMembers() {
		return this.groupMembers;
	}

	public void setGroupMembers(Set<GroupMember> groupMembers) {
		this.groupMembers = groupMembers;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Login> getLogins() {
		return this.logins;
	}

	public void setLogins(Set<Login> logins) {
		this.logins = logins;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
	public Token getToken1() {
		return this.token1;
	}

	public void setToken1(Token token) {
		this.token1 = token;
	}

	@Override
	public Object getEntityId() {
		return this.user_id;
	}
}