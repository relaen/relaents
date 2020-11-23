package dao.pojo;

// default package
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * Token entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_token", catalog = "tiandian")
public class Token extends BasePojo implements java.io.Serializable {
	// Fields
	private Long user_id;
	private User user;
	private String token;
	private Long create_time;
	private Long disable_time;

	// Constructors
	/** default constructor */
	public Token() {
	}

	/** minimal constructor */
	public Token(Long token_id, Long create_time, Long disable_time) {
		this.user_id = token_id;
		this.create_time = create_time;
		this.disable_time = disable_time;
	}

	/** full constructor */
	public Token(Long token_id, User user, String token, Long create_time, Long disable_time) {
		this.user_id = token_id;
		this.user = user;
		this.token = token;
		this.create_time = create_time;
		this.disable_time = disable_time;
	}

	// Property accessors
	@Id
	@Column(name = "user_id", unique = true, nullable = false)
	public Long getUser_id() {
		return this.user_id;
	}

	public void setUser_id(Long token_id) {
		this.user_id = token_id;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "token", length = 32)
	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Column(name = "create_time", nullable = false, length = 19)
	public Long getCreate_time() {
		return this.create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	@Column(name = "disable_time", nullable = false, length = 19)
	public Long getDisable_time() {
		return this.disable_time;
	}

	public void setDisable_time(Long disable_time) {
		this.disable_time = disable_time;
	}

	@Override
	public Object getEntityId() {
		return this.user_id;
	}
}