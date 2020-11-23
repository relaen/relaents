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
 * TSeckill entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_promotion_commodity", catalog = "tiandian")

public class PromotionCommodity extends BasePojo implements java.io.Serializable {

	// Fields

	private Long promotion_commodity_id;
	private Commodity commodity;
	private SpreadActivity spreadActivity;
	private String promotion_type;
	private String promotion_type_name;
	private Double sale_price = 0d;
	private Double bi_price = 0d;
	private Double fen_price = 0d;
	private Integer dian_bi = 0;
	private Integer dian_fen = 0;
	private Integer give_fen = 0;
	private Integer stocks = -1; // 默认-1，表示不限库存
	private Integer stocks_real = -1; // 默认-1，表示不限库存
	private Integer stocks_total = -1; // 默认-1，表示不限库存
	private Long start_time = 0l;
	private Long end_time = 0l;
	private Integer buy_limit = 0; // 默认0，表示不限购
	private Double post_fee = 0d;
	private Double city_fee = 0d;
	private Integer enabled;

	@Override
	public Object getEntityId() {
		return promotion_commodity_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "PROMOTION_COMMODITY_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_PROMOTION_COMMODITY", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PROMOTION_COMMODITY_GEN")
	@Column(name = "promotion_commodity_id", unique = true, nullable = false)
	public Long getPromotion_commodity_id() {
		return promotion_commodity_id;
	}

	public void setPromotion_commodity_id(Long promotion_commodity_id) {
		this.promotion_commodity_id = promotion_commodity_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commodity_id")
	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "spread_activity_id")
	public SpreadActivity getSpreadActivity() {
		return spreadActivity;
	}

	public void setSpreadActivity(SpreadActivity spreadActivity) {
		this.spreadActivity = spreadActivity;
	}

	@Column(name = "promotion_type")
	public String getPromotion_type() {
		return promotion_type;
	}

	public void setPromotion_type(String promotion_type) {
		this.promotion_type = promotion_type;
	}

	@Column(name = "promotion_type_name")
	public String getPromotion_type_name() {
		return promotion_type_name;
	}

	public void setPromotion_type_name(String promotion_type_name) {
		this.promotion_type_name = promotion_type_name;
	}

	@Column(name = "sale_price", precision = 13)
	public Double getSale_price() {
		return sale_price;
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

	@Column(name = "fen_price", precision = 13)
	public Double getFen_price() {
		return fen_price;
	}

	public void setFen_price(Double fen_price) {
		this.fen_price = fen_price;
	}

	@Column(name = "dian_bi")
	public Integer getDian_bi() {
		return dian_bi;
	}

	public void setDian_bi(Integer dian_bi) {
		this.dian_bi = dian_bi;
	}

	@Column(name = "dian_fen")
	public Integer getDian_fen() {
		return dian_fen;
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

	@Column(name = "stocks")
	public Integer getStocks() {
		return stocks;
	}

	public void setStocks(Integer stocks) {
		this.stocks = stocks;
	}

	@Column(name = "stocks_real")
	public Integer getStocks_real() {
		return stocks_real;
	}

	public void setStocks_real(Integer stocks_real) {
		this.stocks_real = stocks_real;
	}

	@Column(name = "stocks_total")
	public Integer getStocks_total() {
		return stocks_total;
	}

	public void setStocks_total(Integer stocks_total) {
		this.stocks_total = stocks_total;
	}

	@Column(name = "start_time")
	public Long getStart_time() {
		return start_time;
	}

	public void setStart_time(Long start_time) {
		this.start_time = start_time;
	}

	@Column(name = "end_time")
	public Long getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Long end_time) {
		this.end_time = end_time;
	}

	@Column(name = "buy_limit")
	public Integer getBuy_limit() {
		return buy_limit;
	}

	public void setBuy_limit(Integer buy_limit) {
		this.buy_limit = buy_limit;
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

	@Column(name = "enabled")
	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

}