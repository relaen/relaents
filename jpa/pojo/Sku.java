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
 * Sku entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_sku", catalog = "tiandian")

public class Sku extends BasePojo implements java.io.Serializable {

	// Fields

	private Long sku_id;
	private Commodity commodity;
	private Integer stocks;
	private String commodity_no;
	private String spec_value_ids;
	private Price price;
	private Set<SkuSpecValue> skuSpecValues = new HashSet<SkuSpecValue>(0);
	private Set<OrderCommodity> orderCommodities = new HashSet<OrderCommodity>(0);
	private Set<SkuRes> skuReses = new HashSet<SkuRes>(0);
	private Set<ShoppingCart> shoppingCarts = new HashSet<ShoppingCart>(0);

	// Constructors

	/** default constructor */
	public Sku() {
	}

	/** minimal constructor */
	public Sku(Long sku_id) {
		this.sku_id = sku_id;
	}


	// Property accessors
	@Id
	@TableGenerator(name = "SKU_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_SKU", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SKU_GEN")
	@Column(name = "sku_id", unique = true, nullable = false)
	public Long getSku_id() {
		return this.sku_id;
	}

	public void setSku_id(Long sku_id) {
		this.sku_id = sku_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commodity_id")

	public Commodity getCommodity() {
		return this.commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	@Column(name = "stocks")

	public Integer getStocks() {
		return this.stocks;
	}

	public void setStocks(Integer stocks) {
		this.stocks = stocks;
	}

	@Column(name = "commodity_no", length = 50)

	public String getCommodity_no() {
		return this.commodity_no;
	}

	public void setCommodity_no(String commodity_no) {
		this.commodity_no = commodity_no;
	}
	
	@Column(name = "spec_value_ids", length = 50)
	public String getSpec_value_ids() {
		return spec_value_ids;
	}
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "sku")
	public Price getPrice() {
		return this.price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public void setSpec_value_ids(String spec_value_ids) {
		this.spec_value_ids = spec_value_ids;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sku")

	public Set<SkuSpecValue> getSkuSpecValues() {
		return this.skuSpecValues;
	}

	public void setSkuSpecValues(Set<SkuSpecValue> skuSpecValues) {
		this.skuSpecValues = skuSpecValues;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sku")

	public Set<OrderCommodity> getOrderCommodities() {
		return this.orderCommodities;
	}

	public void setOrderCommodities(Set<OrderCommodity> orderCommodities) {
		this.orderCommodities = orderCommodities;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sku")

	public Set<SkuRes> getSkuReses() {
		return this.skuReses;
	}

	public void setSkuReses(Set<SkuRes> skuReses) {
		this.skuReses = skuReses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sku")

	public Set<ShoppingCart> getShoppingCarts() {
		return this.shoppingCarts;
	}

	public void setShoppingCarts(Set<ShoppingCart> shoppingCarts) {
		this.shoppingCarts = shoppingCarts;
	}

	@Override
	public Object getEntityId() {
		return this.sku_id;
	}

}