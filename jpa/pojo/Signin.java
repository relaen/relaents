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
 * Signin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_signin", catalog = "tiandian")
public class Signin extends BasePojo implements java.io.Serializable {
	// Fields
	private Long signin_id;
	private Member member;
	private Long sign_time;

	// Constructors
	/** default constructor */
	public Signin() {
	}

	/** minimal constructor */
	public Signin(Long signin_id, Long sign_time) {
		this.signin_id = signin_id;
		this.sign_time = sign_time;
	}

	/** full constructor */
	public Signin(Long signin_id, Member member, Long sign_time) {
		this.signin_id = signin_id;
		this.member = member;
		this.sign_time = sign_time;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "SIGNIN_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_SIGNIN", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SIGNIN_GEN")
	@Column(name = "signin_id", unique = true, nullable = false)
	public Long getSignin_id() {
		return this.signin_id;
	}

	public void setSignin_id(Long signin_id) {
		this.signin_id = signin_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Column(name = "sign_time", nullable = false, length = 19)
	public Long getSign_time() {
		return this.sign_time;
	}

	public void setSign_time(Long sign_time) {
		this.sign_time = sign_time;
	}

	@Override
	public Object getEntityId() {
		return this.signin_id;
	}
}