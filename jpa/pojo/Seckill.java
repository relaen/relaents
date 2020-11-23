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
@Table(name = "t_seckill", catalog = "tiandian")

public class Seckill extends BasePojo implements java.io.Serializable {

	// Fields

	private Long seckill_id;
	private SeckillActivity seckillActivity;
	private Commodity commodity;
	private Double sale_price = 0d;
	private Double bi_price = 0d;
	private Double fen_price = 0d;
	private Integer dian_bi = 0;
	private Integer dian_fen = 0;
	private Integer give_fen = 0;
	private Integer stocks = 0;
	private Integer stocks_real = 0;
	private Integer stocks_total = 0;
	private Long start_time = 0l;
	private Long end_time = 0l;
	private Integer buy_limit = 0;
	private Double post_fee = 0d;
	private Double city_fee = 0d;
	private Integer is_super;

	@Override
	public Object getEntityId() {
		return seckill_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "SECKILL_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_SECKILL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SECKILL_GEN")
	@Column(name = "seckill_id", unique = true, nullable = false)
	public Long getSeckill_id() {
		return seckill_id;
	}

	public void setSeckill_id(Long seckill_id) {
		this.seckill_id = seckill_id;
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
	@JoinColumn(name = "seckill_activity_id")
	public SeckillActivity getSeckillActivity() {
		return seckillActivity;
	}

	public void setSeckillActivity(SeckillActivity seckillActivity) {
		this.seckillActivity = seckillActivity;
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

	@Column(name = "is_super")
	public Integer getIs_super() {
		return is_super;
	}

	public void setIs_super(Integer is_super) {
		this.is_super = is_super;
	}

}