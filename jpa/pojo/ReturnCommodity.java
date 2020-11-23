package dao.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * TReturnCommodity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_return_commodity", catalog = "tiandian")

public class ReturnCommodity extends BasePojo implements java.io.Serializable {

	// Fields

	private Long return_commodity_id;
	private OrderCommodity orderCommodity;
	private ReturnInfo returnInfo;
	private Integer commodity_num;

	// Constructors

	/** default constructor */
	public ReturnCommodity() {
	}

	/** minimal constructor */
	public ReturnCommodity(Long returnCommodityId) {
		this.return_commodity_id = returnCommodityId;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "RETURNCOMMODITY_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_RETURNCOMMODITY", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "RETURNCOMMODITY_GEN")
	@Column(name = "return_commodity_id", unique = true, nullable = false)

	public Long getReturn_commodity_id() {
		return this.return_commodity_id;
	}

	public void setReturn_commodity_id(Long returnCommodityId) {
		this.return_commodity_id = returnCommodityId;
	}
	
	
	@Column(name = "commodity_num")
	public Integer getCommodity_num() {
		return commodity_num;
	}

	public void setCommodity_num(Integer commodity_num) {
		this.commodity_num = commodity_num;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_commodity_id")

	public OrderCommodity getOrderCommodity() {
		return this.orderCommodity;
	}

	public void setOrderCommodity(OrderCommodity TOrderCommodity) {
		this.orderCommodity = TOrderCommodity;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "return_info_id")

	public ReturnInfo getReturnInfo() {
		return this.returnInfo;
	}

	public void setReturnInfo(ReturnInfo returnInfo) {
		this.returnInfo = returnInfo;
	}
	

	@Override
	public Object getEntityId() {
		return this.return_commodity_id;
	}
	
}