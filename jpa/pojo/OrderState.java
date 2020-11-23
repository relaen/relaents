package dao.pojo;

import javax.persistence.TableGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
// default package
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * OrderState entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_order_state", catalog = "tiandian")
public class OrderState extends BasePojo implements java.io.Serializable {
	// Fields
	private Long order_state_id;
	private String order_state_name;
	private Set<OrderStateRcd> orderStateRcds = new HashSet<OrderStateRcd>(0);
	private Set<Orders> orderses = new HashSet<Orders>(0);

	// Constructors
	/** default constructor */
	public OrderState() {
	}

	/** minimal constructor */
	public OrderState(Long order_state_id) {
		this.order_state_id = order_state_id;
	}

	/** full constructor */
	public OrderState(Long order_state_id, String order_state_name, Set<OrderStateRcd> orderStateRcds,
			Set<Orders> orderses) {
		this.order_state_id = order_state_id;
		this.order_state_name = order_state_name;
		this.orderStateRcds = orderStateRcds;
		this.orderses = orderses;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "ORDERSTATE_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_ORDERSTATE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ORDERSTATE_GEN")
	@Column(name = "order_state_id", unique = true, nullable = false)
	public Long getOrder_state_id() {
		return this.order_state_id;
	}

	public void setOrder_state_id(Long order_state_id) {
		this.order_state_id = order_state_id;
	}

	@Column(name = "order_state_name", length = 50)
	public String getOrder_state_name() {
		return this.order_state_name;
	}

	public void setOrder_state_name(String order_state_name) {
		this.order_state_name = order_state_name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orderState")
	public Set<OrderStateRcd> getOrderStateRcds() {
		return this.orderStateRcds;
	}

	public void setOrderStateRcds(Set<OrderStateRcd> orderStateRcds) {
		this.orderStateRcds = orderStateRcds;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orderState")
	public Set<Orders> getOrderses() {
		return this.orderses;
	}

	public void setOrderses(Set<Orders> orderses) {
		this.orderses = orderses;
	}

	@Override
	public Object getEntityId() {
		return this.order_state_id;
	}
}