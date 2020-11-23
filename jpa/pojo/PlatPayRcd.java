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
 * PlatPayRcd entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_plat_pay_rcd", catalog = "tiandian")

public class PlatPayRcd extends BasePojo implements java.io.Serializable {
	// Fields
	private Long plat_pay_rcd_id;
	private Company company;
	private Supplier supplier;
	private SaleMan saleMan;
	private Double pay_money;
	private Long pay_time;
	private PlatPayType platPayType;
	private CashRcd cashRcd;
	private CompanyCash companyCash;
	private SupplierBalance supplierBalance;
	private String pay_state;
	private String businessnumber;

	// Constructors

	/** default constructor */
	public PlatPayRcd() {
	}

	/** minimal constructor */
	public PlatPayRcd(Long plat_pay_rcd_id) {
		this.plat_pay_rcd_id = plat_pay_rcd_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "PLATPAYRCD_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_PLATPAYRCD", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PLATPAYRCD_GEN")
	@Column(name = "plat_pay_rcd_id", unique = true, nullable = false)

	public Long getPlat_pay_rcd_id() {
		return this.plat_pay_rcd_id;
	}

	public void setPlat_pay_rcd_id(Long plat_pay_rcd_id) {
		this.plat_pay_rcd_id = plat_pay_rcd_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
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
	@JoinColumn(name = "sale_man_id")

	public SaleMan getSaleMan() {
		return this.saleMan;
	}

	public void setSaleMan(SaleMan saleMan) {
		this.saleMan = saleMan;
	}

	@Column(name = "pay_money", precision = 13)

	public Double getPay_money() {
		return this.pay_money;
	}

	public void setPay_money(Double pay_money) {
		this.pay_money = pay_money;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "plat_pay_type_id")
	public PlatPayType getPlatPayType() {
		return platPayType;
	}

	public void setPlatPayType(PlatPayType platPayType) {
		this.platPayType = platPayType;
	}

	@Column(name = "pay_time")
	public Long getPay_time() {
		return this.pay_time;
	}

	public void setPay_time(Long pay_time) {
		this.pay_time = pay_time;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cash_rcd_id")
	public CashRcd getCashRcd() {
		return cashRcd;
	}

	public void setCashRcd(CashRcd cashRcd) {
		this.cashRcd = cashRcd;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_cash_id")
	public CompanyCash getCompanyCash() {
		return companyCash;
	}

	public void setCompanyCash(CompanyCash companyCash) {
		this.companyCash = companyCash;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplier_balance_id")
	public SupplierBalance getSupplierBalance() {
		return supplierBalance;
	}

	public void setSupplierBalance(SupplierBalance supplierBalance) {
		this.supplierBalance = supplierBalance;
	}

	@Column(name = "pay_state", length = 20)
	public String getPay_state() {
		return pay_state;
	}

	public void setPay_state(String pay_state) {
		this.pay_state = pay_state;
	}

	@Column(name = "businessnumber", length = 30)
	public String getBusinessnumber() {
		return businessnumber;
	}
	
	public void setBusinessnumber(String businessnumber) {
		this.businessnumber = businessnumber;
	}

	@Override
	public Object getEntityId() {
		return this.plat_pay_rcd_id;
	}
}