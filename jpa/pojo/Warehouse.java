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
 * Warehouse entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_warehouse", catalog = "tiandian")
public class Warehouse extends BasePojo implements java.io.Serializable {
	// Fields
	private Long warehouse_id;
	private Area area;
	private Supplier supplier;
	private String warehouse_name;
	private String address;
	private String mobile;
	private String linkman;
	private String tel;
	private String fax_no;
	private ReturnAddress returnAddress;
	private Set<Commodity> commodities = new HashSet<Commodity>(0);
	private Set<Orders> orders = new HashSet<Orders>(0);
	// Constructors
	/** default constructor */
	public Warehouse() {
	}

	/** minimal constructor */
	public Warehouse(Long warehouse_id) {
		this.warehouse_id = warehouse_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "WAREHOUSE_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_WAREHOUSE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "WAREHOUSE_GEN")
	@Column(name = "warehouse_id", unique = true, nullable = false)
	public Long getWarehouse_id() {
		return this.warehouse_id;
	}

	public void setWarehouse_id(Long warehouse_id) {
		this.warehouse_id = warehouse_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_id")
	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplier_id")
	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "return_address_id")
	public ReturnAddress getReturnAddress() {
		return returnAddress;
	}
	public void setReturnAddress(ReturnAddress returnAddress) {
		this.returnAddress = returnAddress;
	}
	
	@Column(name = "warehouse_name", length = 200)
	public String getWarehouse_name() {
		return this.warehouse_name;
	}

	public void setWarehouse_name(String warehouse_name) {
		this.warehouse_name = warehouse_name;
	}

	@Column(name = "address", length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "mobile", length = 11)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "linkman", length = 50)
	public String getLinkman() {
		return this.linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	@Column(name = "tel", length = 15)
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "fax_no", length = 15)
	public String getFax_no() {
		return this.fax_no;
	}

	public void setFax_no(String fax_no) {
		this.fax_no = fax_no;
	}

	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "warehouse")
	public Set<Commodity> getCommodities() {
		return commodities;
	}

	public void setCommodities(Set<Commodity> commodities) {
		this.commodities = commodities;
	}

	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "warehouse")
	public Set<Orders> getOrders() {
		return orders;
	}

	public void setOrders(Set<Orders> orders) {
		this.orders = orders;
	}

	@Override
	public Object getEntityId() {
		return this.warehouse_id;
	}
}