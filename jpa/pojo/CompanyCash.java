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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * CompanyCash entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_company_cash", catalog = "tiandian")

public class CompanyCash extends BasePojo implements java.io.Serializable {

	// Fields

	private Long company_cash_id;
	private CompanyWallet companyWallet;
	private Member member;
	private Double cash_money;
	private Long cash_time;
	private Long finish_time;
	private String reject_reason;
	private Long check_time;
	private Integer is_agree;
	private String card_no;
	private Integer cash_type;
	private PlatPayRcd platPayRcd;

	// Constructors

	/** default constructor */
	public CompanyCash() {
	}

	/** minimal constructor */
	public CompanyCash(Long company_cash_id) {
		this.company_cash_id = company_cash_id;
	}

	/** full constructor */
	public CompanyCash(Long company_cash_id, CompanyWallet companyWallet, Member member, Double cash_money,
			Long cash_time, Long finish_time, String reject_reason, Long check_time, Integer is_agree, String card_no,
			Integer cash_type) {
		this.company_cash_id = company_cash_id;
		this.companyWallet = companyWallet;
		this.member = member;
		this.cash_money = cash_money;
		this.cash_time = cash_time;
		this.finish_time = finish_time;
		this.reject_reason = reject_reason;
		this.check_time = check_time;
		this.is_agree = is_agree;
		this.card_no = card_no;
		this.cash_type = cash_type;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "COMPANYCASH_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COMPANYCASH", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMPANYCASH_GEN")
	@Column(name = "company_cash_id", unique = true, nullable = false)

	public Long getCompany_cash_id() {
		return this.company_cash_id;
	}

	public void setCompany_cash_id(Long company_cash_id) {
		this.company_cash_id = company_cash_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")

	public CompanyWallet getCompanyWallet() {
		return this.companyWallet;
	}

	public void setCompanyWallet(CompanyWallet companyWallet) {
		this.companyWallet = companyWallet;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Column(name = "cash_money", precision = 13)

	public Double getCash_money() {
		return this.cash_money;
	}

	public void setCash_money(Double cash_money) {
		this.cash_money = cash_money;
	}

	@Column(name = "cash_time")

	public Long getCash_time() {
		return this.cash_time;
	}

	public void setCash_time(Long cash_time) {
		this.cash_time = cash_time;
	}

	@Column(name = "finish_time")

	public Long getFinish_time() {
		return this.finish_time;
	}

	public void setFinish_time(Long finish_time) {
		this.finish_time = finish_time;
	}

	@Column(name = "reject_reason", length = 500)

	public String getReject_reason() {
		return this.reject_reason;
	}

	public void setReject_reason(String reject_reason) {
		this.reject_reason = reject_reason;
	}

	@Column(name = "check_time")

	public Long getCheck_time() {
		return this.check_time;
	}

	public void setCheck_time(Long check_time) {
		this.check_time = check_time;
	}

	@Column(name = "is_agree")

	public Integer getIs_agree() {
		return this.is_agree;
	}

	public void setIs_agree(Integer is_agree) {
		this.is_agree = is_agree;
	}

	@Column(name = "card_no", length = 50)
	public String getCard_no() {
		return this.card_no;
	}

	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	@Column(name = "cash_type")
	public Integer getCash_type() {
		return this.cash_type;
	}

	public void setCash_type(Integer cash_type) {
		this.cash_type = cash_type;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "companyCash")
	public PlatPayRcd getPlatPayRcd() {
		return platPayRcd;
	}

	public void setPlatPayRcd(PlatPayRcd platPayRcd) {
		this.platPayRcd = platPayRcd;
	}

	@Override
	public Object getEntityId() {
		return this.company_cash_id;
	}
}