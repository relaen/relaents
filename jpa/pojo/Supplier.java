package dao.pojo;

// default package
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * Supplier entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_supplier", catalog = "tiandian")
public class Supplier extends BasePojo implements java.io.Serializable {
	// Fields
	private Long supplier_id;
	private Area area;
	private String supplier_name;
	private String address;
	private String zip_code;
	private String fax_no;
	private String mobile;
	private Long create_time;
	private Integer enabled;
	private Warehouse warehouse;
	private BankCard bankCard;
	private SupplierWallet supplierWallet;
	private Set<SupplierBalance> supplierBalances = new HashSet<SupplierBalance>(0);

	// Constructors
	/** default constructor */
	public Supplier() {
	}

	/** minimal constructor */
	public Supplier(Long supplier_id) {
		this.supplier_id = supplier_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "SUPPLIER_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_SUPPLIER", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SUPPLIER_GEN")
	@Column(name = "supplier_id", unique = true, nullable = false)
	public Long getSupplier_id() {
		return this.supplier_id;
	}

	public void setSupplier_id(Long supplier_id) {
		this.supplier_id = supplier_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_id")
	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@Column(name = "supplier_name", length = 50)
	public String getSupplier_name() {
		return this.supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}

	@Column(name = "address", length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "zip_code", length = 6)
	public String getZip_code() {
		return this.zip_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	@Column(name = "fax_no", length = 15)
	public String getFax_no() {
		return this.fax_no;
	}

	public void setFax_no(String fax_no) {
		this.fax_no = fax_no;
	}

	@Column(name = "mobile")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "create_time")
	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	@Column(name = "enabled")
	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "supplier")
	public SupplierWallet getSupplierWallet() {
		return supplierWallet;
	}

	public void setSupplierWallet(SupplierWallet supplierWallet) {
		this.supplierWallet = supplierWallet;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "supplier")
	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bank_card_id")
	public BankCard getBankCard() {
		return bankCard;
	}

	public void setBankCard(BankCard bankCard) {
		this.bankCard = bankCard;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "supplier")
	public Set<SupplierBalance> getSupplierBalances() {
		return this.supplierBalances;
	}

	public void setSupplierBalances(Set<SupplierBalance> supplierBalances) {
		this.supplierBalances = supplierBalances;
	}

	@Override
	public Object getEntityId() {
		return this.supplier_id;
	}
}