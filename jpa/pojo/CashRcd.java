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
 * CashRcd entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_cash_rcd", catalog = "tiandian")

public class CashRcd extends BasePojo implements java.io.Serializable {

	// Fields

	private Long cash_rcd_id;
	private SaleMan saleMan;
	private Member checker; // 审核员
	private BankCard bankCard;
	private Double cash_money;
	private Long cash_time;
	private Long finish_time;
	private String reject_reason;
	private Long check_time;
	private Integer is_agree;
	private PlatPayRcd platPayRcd;

	// Constructors

	/** default constructor */
	public CashRcd() {
	}

	/** minimal constructor */
	public CashRcd(Long cash_rcd_id) {
		this.cash_rcd_id = cash_rcd_id;
	}

	/** full constructor */
	public CashRcd(Long cash_rcd_id, SaleMan saleMan, Double cash_money, Long cash_time, Long finish_time,
			String against_reason, Long check_time, Integer is_agree) {
		this.cash_rcd_id = cash_rcd_id;
		this.saleMan = saleMan;
		this.cash_money = cash_money;
		this.cash_time = cash_time;
		this.finish_time = finish_time;
		this.reject_reason = against_reason;
		this.check_time = check_time;
		this.is_agree = is_agree;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "CASHRCD_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_CASHRCD", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CASHRCD_GEN")
	@Column(name = "cash_rcd_id", unique = true, nullable = false)
	public Long getCash_rcd_id() {
		return this.cash_rcd_id;
	}

	public void setCash_rcd_id(Long cash_rcd_id) {
		this.cash_rcd_id = cash_rcd_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sale_man_id")

	public SaleMan getSaleMan() {
		return this.saleMan;
	}

	public void setSaleMan(SaleMan saleMan) {
		this.saleMan = saleMan;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bank_card_id")
	public BankCard getBankCard() {
		return bankCard;
	}

	public void setBankCard(BankCard bankCard) {
		this.bankCard = bankCard;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "checker_id")
	public Member getChecker() {
		return checker;
	}

	public void setChecker(Member checker) {
		this.checker = checker;
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

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "cashRcd")
	public PlatPayRcd getPlatPayRcd() {
		return platPayRcd;
	}
	
	public void setPlatPayRcd(PlatPayRcd platPayRcd) {
		this.platPayRcd = platPayRcd;
	}

	@Override
	public Object getEntityId() {
		return this.cash_rcd_id;
	}
}