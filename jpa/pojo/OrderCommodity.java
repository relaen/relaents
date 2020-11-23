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
 * OrderCommodity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_order_commodity", catalog = "tiandian")
public class OrderCommodity extends BasePojo implements java.io.Serializable {
	// Fields
	private Long order_commodity_id;
	private Orders orders;
	private Sku sku;
	private Integer commodity_num;
	private Double sale_price = 0d;
	private Double bi_price = 0d;
	private Double dian_yuan = 0d;
	private Integer dian_bi = 0;
	private Integer dian_fen = 0;
	private Integer give_fen = 0;
	private Double pay_money = 0d;
	private Integer has_commented = 0;
	private Double cost_price = 0d;
	private Commodity commodity;
	private Package1 package1;
	private Seckill seckill;
	private PromotionCommodity promotionCommodity;
	private Set<ReturnCommodity> returnCommodities = new HashSet<ReturnCommodity>(0);
	private Set<CommodityComment> commodityComments = new HashSet<CommodityComment>(0);

	// Constructors
	/** default constructor */
	public OrderCommodity() {
	}

	/** minimal constructor */
	public OrderCommodity(Long order_commodity_id) {
		this.order_commodity_id = order_commodity_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "ORDERCOMMODITY_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_ORDERCOMMODITY", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ORDERCOMMODITY_GEN")
	@Column(name = "order_commodity_id", unique = true, nullable = false)
	public Long getOrder_commodity_id() {
		return this.order_commodity_id;
	}

	public void setOrder_commodity_id(Long order_commodity_id) {
		this.order_commodity_id = order_commodity_id;
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
	@JoinColumn(name = "sku_id")
	public Sku getSku() {
		return this.sku;
	}

	public void setSku(Sku sku) {
		this.sku = sku;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commodity_id")
	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	@Column(name = "commodity_num")
	public Integer getCommodity_num() {
		return this.commodity_num;
	}

	public void setCommodity_num(Integer commodity_num) {
		this.commodity_num = commodity_num;
	}

	@Column(name = "sale_price", precision = 13)
	public Double getSale_price() {
		return this.sale_price;
	}

	public void setSale_price(Double sale_price) {
		this.sale_price = sale_price;
	}

	@Column(name = "bi_price", precision = 13)
	public Double getBi_price() {
		return bi_price;
	}

	public void setBi_price(Double bi_price) {
		this.bi_price = bi_price;
	}

	@Column(name = "dian_yuan", precision = 13)
	public Double getDian_yuan() {
		return this.dian_yuan;
	}

	public void setDian_yuan(Double dian_yuan) {
		this.dian_yuan = dian_yuan;
	}

	@Column(name = "pay_money", precision = 13)
	public Double getPay_money() {
		return pay_money;
	}

	public void setPay_money(Double pay_money) {
		this.pay_money = pay_money;
	}

	@Column(name = "dian_bi")
	public Integer getDian_bi() {
		return this.dian_bi;
	}

	public void setDian_bi(Integer dian_bi) {
		this.dian_bi = dian_bi;
	}

	@Column(name = "dian_fen")
	public Integer getDian_fen() {
		return this.dian_fen;
	}

	public void setDian_fen(Integer dian_fen) {
		this.dian_fen = dian_fen;
	}

	@Column(name = "give_fen")
	public Integer getGive_fen() {
		return give_fen;
	}

	public void setGive_fen(Integer give_fen) {
		this.give_fen = give_fen;
	}

	@Column(name = "has_commented")
	public Integer getHas_commented() {
		return this.has_commented;
	}

	public void setHas_commented(Integer has_commented) {
		this.has_commented = has_commented;
	}

	@Column(name = "cost_price")
	public Double getCost_price() {
		return cost_price;
	}

	public void setCost_price(Double cost_price) {
		this.cost_price = cost_price;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "package_id")
	public Package1 getPackage1() {
		return package1;
	}

	public void setPackage1(Package1 package1) {
		this.package1 = package1;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seckill_id")
	public Seckill getSeckill() {
		return seckill;
	}

	public void setSeckill(Seckill seckill) {
		this.seckill = seckill;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "promotion_commodity_id")
	public PromotionCommodity getPromotionCommodity() {
		return promotionCommodity;
	}
	
	public void setPromotionCommodity(PromotionCommodity promotionCommodity) {
		this.promotionCommodity = promotionCommodity;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orderCommodity")
	public Set<CommodityComment> getCommodityComments() {
		return this.commodityComments;
	}

	public void setCommodityComments(Set<CommodityComment> commodityComments) {
		this.commodityComments = commodityComments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orderCommodity")
	public Set<ReturnCommodity> getReturnCommodities() {
		return returnCommodities;
	}

	public void setReturnCommodities(Set<ReturnCommodity> returnCommodities) {
		this.returnCommodities = returnCommodities;
	}

	@Override
	public Object getEntityId() {
		return this.order_commodity_id;
	}
}