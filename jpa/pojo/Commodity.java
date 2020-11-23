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
 * Commodity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_commodity", catalog = "tiandian")
public class Commodity extends BasePojo implements java.io.Serializable {
	// Fields
	private Long commodity_id;
	private CommodityOther commodityOther;
	private Company company;
	private Supplier supplier;
	private Country country;
	private Brand brand;
	private String commodity_name;
	private String commodity_no;
	private Integer total_sale_num = 0;
	private Integer stocks = 0;
	private Long onshief_time = null;
	private Long offshief_time = System.currentTimeMillis();
	private Double comment_score = 10.0;
	private Integer comment_num = 0;
	private Integer collection_num = 0;
	private Integer see_num = 0;
	private Integer share_num = 0;
	private Integer sale_num = 0;
	private String commodity_desc;
	private Integer is_fen = 0;
	private Integer is_online = 1;
	private Integer post_type = 4; // 邮寄类型 1自提 2同城 3自提+同城 4快递 5自提+快递 6同城+快递
									// 7自提+同城+快递
	private Double good_comment_rate = 100.0;
	private Integer enabled = 1; // 默认1有效，删除后修改为0
	private Double post_fee = 10d; // 快递邮费
	private Double city_fee = 5d; // 同城邮费
	private Warehouse warehouse;
	private Price price;
	private Integer is_new = 1; // 是否从未上架
	private Integer check_state = 1; // 审核状态，1待审核2审核通过3审核驳回
	private Integer is_show_in_app = 1; // 是否显示在app中
	private Long create_time; // 创建时间
	private Integer is_profit_no_fen = 0; // 是否发放无点分佣金
	private String commodity_name_short; // 商品简称
	private Integer buy_least_num = 1; // 起购数量
	private Integer is_recommend_company; // 商家推荐
	private Integer is_recommend_platform; // 平台推荐

	private Set<CommodityRes> commodityReses = new HashSet<CommodityRes>(0);
	private Set<Promotion> promotions = new HashSet<Promotion>(0);
	private Set<CommodityVerifyRel> commodityVerifyRels = new HashSet<CommodityVerifyRel>(0);
	private Set<RecommendCommodity> recommendCommodities = new HashSet<RecommendCommodity>(0);
	private Set<Adv> advs = new HashSet<Adv>(0);
	private Set<CommodityCateRel> commodityCateRels = new HashSet<CommodityCateRel>(0);
	private Set<CommodityCollection> commodityCollections = new HashSet<CommodityCollection>(0);
	private Set<CommodityPropValue> commodityPropValues = new HashSet<CommodityPropValue>(0);
	private Set<ActivityCommodity> activityCommodities = new HashSet<ActivityCommodity>(0);
	private Set<CommodityComment> commodityComments = new HashSet<CommodityComment>(0);
	private Set<ShoppingCart> shoppingCarts = new HashSet<ShoppingCart>(0);
	private Set<OrderCommodity> orderCommodities = new HashSet<OrderCommodity>(0);
	private Set<Sku> skus = new HashSet<Sku>(0);
	private Set<CommodityCheck> commodityChecks = new HashSet<CommodityCheck>(0);

	// Constructors
	/** default constructor */
	public Commodity() {
	}

	/** minimal constructor */
	public Commodity(Long commodity_id, Long onshief_time, Long offshief_time) {
		this.commodity_id = commodity_id;
		this.onshief_time = onshief_time;
		this.offshief_time = offshief_time;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "COMMODITY_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COMMODITY", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMMODITY_GEN")
	@Column(name = "commodity_id", unique = true, nullable = false)
	public Long getCommodity_id() {
		return this.commodity_id;
	}

	public void setCommodity_id(Long commodity_id) {
		this.commodity_id = commodity_id;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "commodity")
	public CommodityOther getCommodityOther() {
		return commodityOther;
	}

	public void setCommodityOther(CommodityOther commodityOther) {
		this.commodityOther = commodityOther;
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
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id")
	public Brand getBrand() {
		return this.brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "warehouse_id")
	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	@Column(name = "commodity_name", length = 100)
	public String getCommodity_name() {
		return this.commodity_name;
	}

	public void setCommodity_name(String commodity_name) {
		this.commodity_name = commodity_name;
	}

	@Column(name = "stocks")
	public Integer getStocks() {
		return stocks;
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

	@Column(name = "total_sale_num")
	public Integer getTotal_sale_num() {
		return this.total_sale_num;
	}

	public void setTotal_sale_num(Integer total_sale_num) {
		this.total_sale_num = total_sale_num;
	}

	@Column(name = "onshief_time", nullable = false, length = 19)
	public Long getOnshief_time() {
		return this.onshief_time;
	}

	public void setOnshief_time(Long onshief_time) {
		this.onshief_time = onshief_time;
	}

	@Column(name = "offshief_time", nullable = false, length = 19)
	public Long getOffshief_time() {
		return this.offshief_time;
	}

	public void setOffshief_time(Long offshief_time) {
		this.offshief_time = offshief_time;
	}

	@Column(name = "comment_score", precision = 2, scale = 1)
	public Double getComment_score() {
		return this.comment_score;
	}

	public void setComment_score(Double comment_score) {
		this.comment_score = comment_score;
	}

	@Column(name = "comment_num")
	public Integer getComment_num() {
		return this.comment_num;
	}

	public void setComment_num(Integer comment_num) {
		this.comment_num = comment_num;
	}

	@Column(name = "collection_num")
	public Integer getCollection_num() {
		return this.collection_num;
	}

	public void setCollection_num(Integer collection_num) {
		this.collection_num = collection_num;
	}

	@Column(name = "see_num")
	public Integer getSee_num() {
		return this.see_num;
	}

	public void setSee_num(Integer see_num) {
		this.see_num = see_num;
	}

	@Column(name = "share_num")
	public Integer getShare_num() {
		return this.share_num;
	}

	public void setShare_num(Integer share_num) {
		this.share_num = share_num;
	}

	@Column(name = "sale_num")
	public Integer getSale_num() {
		return this.sale_num;
	}

	public void setSale_num(Integer sale_num) {
		this.sale_num = sale_num;
	}

	@Column(name = "commodity_desc", length = 2000)
	public String getCommodity_desc() {
		return this.commodity_desc;
	}

	public void setCommodity_desc(String commodity_desc) {
		this.commodity_desc = commodity_desc;
	}

	@Column(name = "is_fen")
	public Integer getIs_fen() {
		return this.is_fen;
	}

	public void setIs_fen(Integer is_fen) {
		this.is_fen = is_fen;
	}

	@Column(name = "is_online")
	public Integer getIs_online() {
		return this.is_online;
	}

	public void setIs_online(Integer is_online) {
		this.is_online = is_online;
	}

	@Column(name = "good_comment_rate")
	public Double getGood_comment_rate() {
		return good_comment_rate;
	}

	public void setGood_comment_rate(Double good_comment_rate) {
		this.good_comment_rate = good_comment_rate;
	}

	@Column(name = "enabled")
	public Integer getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	@Column(name = "post_type")
	public Integer getPost_type() {
		return post_type;
	}

	public void setPost_type(Integer post_type) {
		this.post_type = post_type;
	}

	@Column(name = "post_fee")
	public Double getPost_fee() {
		return post_fee;
	}

	public void setPost_fee(Double post_fee) {
		this.post_fee = post_fee;
	}

	@Column(name = "city_fee")
	public Double getCity_fee() {
		return city_fee;
	}

	public void setCity_fee(Double city_fee) {
		this.city_fee = city_fee;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commodity")
	public Set<CommodityRes> getCommodityReses() {
		return this.commodityReses;
	}

	public void setCommodityReses(Set<CommodityRes> commodityReses) {
		this.commodityReses = commodityReses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commodity")
	public Set<Promotion> getPromotions() {
		return this.promotions;
	}

	public void setPromotions(Set<Promotion> promotions) {
		this.promotions = promotions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commodity")
	public Set<CommodityVerifyRel> getCommodityVerifyRels() {
		return this.commodityVerifyRels;
	}

	public void setCommodityVerifyRels(Set<CommodityVerifyRel> commodityVerifyRels) {
		this.commodityVerifyRels = commodityVerifyRels;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commodity")
	public Set<RecommendCommodity> getRecommendCommodities() {
		return this.recommendCommodities;
	}

	public void setRecommendCommodities(Set<RecommendCommodity> recommendCommodities) {
		this.recommendCommodities = recommendCommodities;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commodity")
	public Set<Adv> getAdvs() {
		return this.advs;
	}

	public void setAdvs(Set<Adv> advs) {
		this.advs = advs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commodity")
	public Set<CommodityCateRel> getCommodityCateRels() {
		return this.commodityCateRels;
	}

	public void setCommodityCateRels(Set<CommodityCateRel> commodityCateRels) {
		this.commodityCateRels = commodityCateRels;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commodity")
	public Set<CommodityCollection> getCommodityCollections() {
		return this.commodityCollections;
	}

	public void setCommodityCollections(Set<CommodityCollection> commodityCollections) {
		this.commodityCollections = commodityCollections;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commodity")
	public Set<CommodityPropValue> getCommodityPropValues() {
		return this.commodityPropValues;
	}

	public void setCommodityPropValues(Set<CommodityPropValue> commodityPropValues) {
		this.commodityPropValues = commodityPropValues;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commodity")
	public Set<ActivityCommodity> getActivityCommodities() {
		return this.activityCommodities;
	}

	public void setActivityCommodities(Set<ActivityCommodity> activityCommodities) {
		this.activityCommodities = activityCommodities;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commodity")
	public Set<CommodityComment> getCommodityComments() {
		return this.commodityComments;
	}

	public void setCommodityComments(Set<CommodityComment> commodityComments) {
		this.commodityComments = commodityComments;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "commodity")
	public Price getPrice() {
		return this.price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	@Column(name = "is_new")
	public Integer getIs_new() {
		return is_new;
	}

	public void setIs_new(Integer is_new) {
		this.is_new = is_new;
	}

	@Column(name = "check_state")
	public Integer getCheck_state() {
		return check_state;
	}

	public void setCheck_state(Integer check_state) {
		this.check_state = check_state;
	}

	@Column(name = "is_show_in_app")
	public Integer getIs_show_in_app() {
		return is_show_in_app;
	}

	public void setIs_show_in_app(Integer is_show_in_app) {
		this.is_show_in_app = is_show_in_app;
	}

	@Column(name = "create_time")
	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	@Column(name = "is_profit_no_fen")
	public Integer getIs_profit_no_fen() {
		return is_profit_no_fen;
	}

	public void setIs_profit_no_fen(Integer is_profit_no_fen) {
		this.is_profit_no_fen = is_profit_no_fen;
	}

	@Column(name = "commodity_name_short", length = 20)
	public String getCommodity_name_short() {
		return commodity_name_short;
	}

	public void setCommodity_name_short(String commodity_name_short) {
		this.commodity_name_short = commodity_name_short;
	}

	@Column(name = "buy_least_num")
	public Integer getBuy_least_num() {
		return buy_least_num;
	}

	public void setBuy_least_num(Integer buy_least_num) {
		this.buy_least_num = buy_least_num;
	}

	@Column(name = "is_recommend_company")
	public Integer getIs_recommend_company() {
		return is_recommend_company;
	}

	public void setIs_recommend_company(Integer is_recommend_company) {
		this.is_recommend_company = is_recommend_company;
	}

	@Column(name = "is_recommend_platform")
	public Integer getIs_recommend_platform() {
		return is_recommend_platform;
	}

	public void setIs_recommend_platform(Integer is_recommend_platform) {
		this.is_recommend_platform = is_recommend_platform;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commodity")
	public Set<Sku> getSkus() {
		return this.skus;
	}

	public void setSkus(Set<Sku> skus) {
		this.skus = skus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commodity")
	public Set<ShoppingCart> getShoppingCarts() {
		return shoppingCarts;
	}

	public void setShoppingCarts(Set<ShoppingCart> shoppingCarts) {
		this.shoppingCarts = shoppingCarts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commodity")
	public Set<OrderCommodity> getOrderCommodities() {
		return orderCommodities;
	}

	public void setOrderCommodities(Set<OrderCommodity> orderCommodities) {
		this.orderCommodities = orderCommodities;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commodity")
	public Set<CommodityCheck> getCommodityChecks() {
		return commodityChecks;
	}

	public void setCommodityChecks(Set<CommodityCheck> commodityChecks) {
		this.commodityChecks = commodityChecks;
	}

	@Override
	public Object getEntityId() {
		return this.commodity_id;
	}
}