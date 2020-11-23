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
 * BankCard entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_bank_card", catalog = "tiandian")

public class BankCard extends BasePojo implements java.io.Serializable {
	// Fields

	private Long bank_card_id;
	private String card_no;
	private String bank;
	private String bank_name;
	private String bank_card_name;
	private String bank_card_type;
	private String bank_account_type;
	private String mobile;
	private String link_man;
	private String email;
	private Area area;
	private Integer is_agree; // null未审核；0审核驳回；1审核通过
	private String reject_reason;

	// Constructors

	/** default constructor */
	public BankCard() {
	}

	/** minimal constructor */
	public BankCard(Long bank_card_id, String card_no) {
		this.bank_card_id = bank_card_id;
		this.card_no = card_no;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "BANKCARD_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_BANKCARD", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "BANKCARD_GEN")
	@Column(name = "bank_card_id", unique = true, nullable = false)

	public Long getBank_card_id() {
		return this.bank_card_id;
	}

	public void setBank_card_id(Long bank_card_id) {
		this.bank_card_id = bank_card_id;
	}

	@Column(name = "card_no", length = 50)

	public String getCard_no() {
		return this.card_no;
	}

	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	@Column(name = "bank")
	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	@Column(name = "bank_name", length = 200)

	public String getBank_name() {
		return this.bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	@Column(name = "bank_card_name", length = 200)

	public String getBank_card_name() {
		return this.bank_card_name;
	}

	public void setBank_card_name(String bank_card_name) {
		this.bank_card_name = bank_card_name;
	}

	@Column(name = "bank_card_type", length = 50)

	public String getBank_card_type() {
		return this.bank_card_type;
	}

	public void setBank_card_type(String bank_card_type) {
		this.bank_card_type = bank_card_type;
	}

	@Column(name = "bank_account_type", length = 50)

	public String getBank_account_type() {
		return this.bank_account_type;
	}

	public void setBank_account_type(String bank_account_type) {
		this.bank_account_type = bank_account_type;
	}

	@Column(name = "mobile", length = 11)

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "link_man", length = 50)

	public String getLink_man() {
		return this.link_man;
	}

	public void setLink_man(String link_man) {
		this.link_man = link_man;
	}

	@Column(name = "email", length = 50)

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_id")
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@Column(name = "is_agree")
	public Integer getIs_agree() {
		return is_agree;
	}

	public void setIs_agree(Integer is_agree) {
		this.is_agree = is_agree;
	}

	@Column(name = "reject_reason", length = 500)
	public String getReject_reason() {
		return reject_reason;
	}

	public void setReject_reason(String reject_reason) {
		this.reject_reason = reject_reason;
	}

	@Override
	public Object getEntityId() {
		return this.bank_card_id;
	}
}