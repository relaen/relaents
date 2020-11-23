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
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * Package entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_package1", catalog = "tiandian")

public class Package1 extends BasePojo implements java.io.Serializable {

	// Fields

	private Long package_id;
	private Orders orders;
	private Warehouse warehouse;
	private String package_no;
	private Integer commodity_num=0;
	private Double package_weight=0d;
	private Set<OrderCommodity> orderCommodities = new HashSet<OrderCommodity>(0);

	// Constructors

	/** default constructor */
	public Package1() {
	}

	/** minimal constructor */
	public Package1(Long package_id) {
		this.package_id = package_id;
	}

	/** full constructor */
	public Package1(Long package_id, Orders orders, Warehouse warehouse, String package_no, Integer commodity_num,
			Double package_weight, Set<OrderCommodity> orderCommodities) {
		this.package_id = package_id;
		this.orders = orders;
		this.warehouse = warehouse;
		this.package_no = package_no;
		this.commodity_num = commodity_num;
		this.package_weight = package_weight;
		this.orderCommodities = orderCommodities;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "PACKAGE_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_PACKAGE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PACKAGE_GEN")
	@Column(name = "package_id", unique = true, nullable = false)

	public Long getPackage_id() {
		return this.package_id;
	}

	public void setPackage_id(Long package_id) {
		this.package_id = package_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")

	public Orders getOrders() {
		return this.orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "warehouse_id")

	public Warehouse getWarehouse() {
		return this.warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	@Column(name = "package_no", length = 20)

	public String getPackage_no() {
		return this.package_no;
	}

	public void setPackage_no(String package_no) {
		this.package_no = package_no;
	}

	@Column(name = "commodity_num")

	public Integer getCommodity_num() {
		return this.commodity_num;
	}

	public void setCommodity_num(Integer commodity_num) {
		this.commodity_num = commodity_num;
	}

	@Column(name = "package_weight", precision = 10, scale = 3)

	public Double getPackage_weight() {
		return this.package_weight;
	}

	public void setPackage_weight(Double package_weight) {
		this.package_weight = package_weight;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "package1")

	public Set<OrderCommodity> getOrderCommodities() {
		return this.orderCommodities;
	}

	public void setOrderCommodities(Set<OrderCommodity> orderCommodities) {
		this.orderCommodities = orderCommodities;
	}

	@Override
	public Object getEntityId() {
		return this.package_id;
	}
}