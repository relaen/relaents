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
 * SupplierBalance entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_supplier_balance", catalog = "tiandian")

public class SupplierBalance extends BasePojo implements java.io.Serializable {

	// Fields

	private Long supplier_balance_id;
	private Supplier supplier;
	private Member member;
	private Long create_time;
	private Double balance_money;
	private Long finish_time;
	private Long check_time;
	private Short is_agree;
	private String card_no;
	private String reject_reason;
	private PlatPayRcd platPayRcd;

	// Constructors

	/** default constructor */
	public SupplierBalance() {
	}

	/** minimal constructor */
	public SupplierBalance(Long supplier_balance_id) {
		this.supplier_balance_id = supplier_balance_id;
	}

	/** full constructor */
	public SupplierBalance(Long supplier_balance_id, Supplier supplier, Member member, Long create_time,
			Double balance_money, Long finish_time, Long check_time, Short is_agree, String card_no) {
		this.supplier_balance_id = supplier_balance_id;
		this.supplier = supplier;
		this.member = member;
		this.create_time = create_time;
		this.balance_money = balance_money;
		this.finish_time = finish_time;
		this.check_time = check_time;
		this.is_agree = is_agree;
		this.card_no = card_no;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "SUPPLIERBALANCE_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_SUPPLIERBALANCE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SUPPLIERBALANCE_GEN")

	@Column(name = "supplier_balance_id", unique = true, nullable = false)

	public Long getSupplier_balance_id() {
		return this.supplier_balance_id;
	}

	public void setSupplier_balance_id(Long supplier_balance_id) {
		this.supplier_balance_id = supplier_balance_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplier_id")

	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Column(name = "create_time")

	public Long getCreate_time() {
		return this.create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	@Column(name = "balance_money", precision = 13)

	public Double getBalance_money() {
		return this.balance_money;
	}

	public void setBalance_money(Double balance_money) {
		this.balance_money = balance_money;
	}

	@Column(name = "finish_time")

	public Long getFinish_time() {
		return this.finish_time;
	}

	public void setFinish_time(Long finish_time) {
		this.finish_time = finish_time;
	}

	@Column(name = "check_time")

	public Long getCheck_time() {
		return this.check_time;
	}

	public void setCheck_time(Long check_time) {
		this.check_time = check_time;
	}

	@Column(name = "is_agree")

	public Short getIs_agree() {
		return this.is_agree;
	}

	public void setIs_agree(Short is_agree) {
		this.is_agree = is_agree;
	}

	@Column(name = "card_no", length = 50)

	public String getCard_no() {
		return this.card_no;
	}

	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	@Column(name = "reject_reason", length = 500)
	public String getReject_reason() {
		return reject_reason;
	}

	public void setReject_reason(String reject_reason) {
		this.reject_reason = reject_reason;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "supplierBalance")
	public PlatPayRcd getPlatPayRcd() {
		return platPayRcd;
	}

	public void setPlatPayRcd(PlatPayRcd platPayRcd) {
		this.platPayRcd = platPayRcd;
	}

	@Override
	public Object getEntityId() {
		return this.supplier_balance_id;
	}

}