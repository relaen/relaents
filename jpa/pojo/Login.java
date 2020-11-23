package dao.pojo;

import javax.persistence.TableGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
// default package
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Login entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_login", catalog = "tiandian")
public class Login extends BasePojo implements java.io.Serializable {
	// Fields
	private Long login_id;
	private User user;
	private Long login_time;
	private String login_ip;

	// Constructors
	/** default constructor */
	public Login() {
	}

	/** minimal constructor */
	public Login(Long login_id, Long login_time) {
		this.login_id = login_id;
		this.login_time = login_time;
	}

	/** full constructor */
	public Login(Long login_id, User user, Long login_time, String login_ip) {
		this.login_id = login_id;
		this.user = user;
		this.login_time = login_time;
		this.login_ip = login_ip;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "LOGIN_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_LOGIN", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "LOGIN_GEN")
	@Column(name = "login_id", unique = true, nullable = false)
	public Long getLogin_id() {
		return this.login_id;
	}

	public void setLogin_id(Long login_id) {
		this.login_id = login_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "login_time", nullable = false, length = 19)
	public Long getLogin_time() {
		return this.login_time;
	}

	public void setLogin_time(Long login_time) {
		this.login_time = login_time;
	}

	@Column(name = "login_ip", length = 50)
	public String getLogin_ip() {
		return this.login_ip;
	}

	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}

	@Override
	public Object getEntityId() {
		return this.login_id;
	}
}