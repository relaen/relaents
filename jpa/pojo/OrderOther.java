package dao.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * TOrderOther entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_order_other", catalog = "tiandian")

public class OrderOther extends BasePojo implements java.io.Serializable {

	// Fields

	private Long order_id;
	private CommodityProvider commodityProvider;
	private Orders orders;
	private Long order_no;

	@Override
	public Object getEntityId() {
		// TODO Auto-generated method stub
		return order_id;
	}

	// Property accessors
	@Id
	@Column(name = "order_id", unique = true, nullable = false)
	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commodity_provider_id")
	public CommodityProvider getCommodityProvider() {
		return commodityProvider;
	}

	public void setCommodityProvider(CommodityProvider commodityProvider) {
		this.commodityProvider = commodityProvider;
	}

	@Column(name = "order_no")
	public Long getOrder_no() {
		return order_no;
	}

	public void setOrder_no(Long order_no) {
		this.order_no = order_no;
	}

}