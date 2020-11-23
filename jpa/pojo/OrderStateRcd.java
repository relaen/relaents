package dao.pojo;

import javax.persistence.TableGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
// default package
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * OrderStateRcd entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_order_state_rcd", catalog = "tiandian")
public class OrderStateRcd extends BasePojo implements java.io.Serializable {
	// Fields
	private Long order_state_rcd_id;
	private Orders orders;
	private OrderState orderState;
	private Long rcd_time;
	private String remarks;

	// Constructors
	/** default constructor */
	public OrderStateRcd() {
	}

	/** minimal constructor */
	public OrderStateRcd(Long order_state_rcd_id, Long rcd_time) {
		this.order_state_rcd_id = order_state_rcd_id;
		this.rcd_time = rcd_time;
	}

	/** full constructor */
	public OrderStateRcd(Long order_state_rcd_id, Orders orders, OrderState orderState, Long rcd_time,
			String remarks) {
		this.order_state_rcd_id = order_state_rcd_id;
		this.orders = orders;
		this.orderState = orderState;
		this.rcd_time = rcd_time;
		this.remarks = remarks;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "ORDERSTATERCD_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_ORDERSTATERCD", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ORDERSTATERCD_GEN")
	@Column(name = "order_state_rcd_id", unique = true, nullable = false)
	public Long getOrder_state_rcd_id() {
		return this.order_state_rcd_id;
	}

	public void setOrder_state_rcd_id(Long order_state_rcd_id) {
		this.order_state_rcd_id = order_state_rcd_id;
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
	@JoinColumn(name = "order_state_id")
	public OrderState getOrderState() {
		return this.orderState;
	}

	public void setOrderState(OrderState orderState) {
		this.orderState = orderState;
	}

	@Column(name = "rcd_time", nullable = false, length = 19)
	public Long getRcd_time() {
		return this.rcd_time;
	}

	public void setRcd_time(Long rcd_time) {
		this.rcd_time = rcd_time;
	}

	@Column(name = "remarks", length = 500)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public Object getEntityId() {
		return this.order_state_rcd_id;
	}
}