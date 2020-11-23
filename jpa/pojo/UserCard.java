package dao.pojo;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * UserCard entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_user_card", catalog = "tiandian")

public class UserCard extends BasePojo implements java.io.Serializable {

	private Long user_card_id;
	private BankCard bankCard;
	private Member member;
	private Integer is_default;

	// Constructors

	/** default constructor */
	public UserCard() {
	}

	/** minimal constructor */
	public UserCard(Long user_card_id) {
		this.user_card_id = user_card_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "USERCARD_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_USERCARD", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "USERCARD_GEN")
	@Column(name = "user_card_id", unique = true, nullable = false)

	public Long getUser_card_id() {
		return this.user_card_id;
	}

	public void setUser_card_id(Long user_card_id) {
		this.user_card_id = user_card_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bank_card_id")

	public BankCard getBankCard() {
		return this.bankCard;
	}

	public void setBankCard(BankCard bankCard) {
		this.bankCard = bankCard;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Column(name = "is_default")

	public Integer getIs_default() {
		return this.is_default;
	}

	public void setIs_default(Integer is_default) {
		this.is_default = is_default;
	}

	@Override
	public Object getEntityId() {
		return this.user_card_id;
	}
}