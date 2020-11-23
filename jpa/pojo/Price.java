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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Price entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_price", catalog = "tiandian")
public class Price extends BasePojo implements java.io.Serializable {
	// Fields
	private Long price_id;
	private Commodity commodity;
	private Sku sku;
	private Double price=0d;
	private Double sale_price=0d;
	private Double cost_price=0d;
	private Double bi_price=0d;
	private Integer dian_bi=0;
	private Integer dian_fen=0;
	private Double special_price=0d;
	private Double special_bi_price=0d;
	private Integer special_dian_fen=0;
	private Integer special_dian_bi=0;
	private Integer give_fen=0;
	private Integer stocks=0;
	

	// Constructors
	/** default constructor */
	public Price() {
	}

	/** minimal constructor */
	public Price(Long price_id) {
		this.price_id = price_id;
	}

	/** full constructor */
	public Price(Long price_id, Commodity commodity, Sku sku, Double price, Double sale_price, Double cost_price,
			Integer dian_bi, Integer dian_fen, Double fen_price, Double special_price, Integer special_dian_fen,
			Integer special_dian_bi, Integer stocks) {
		this.price_id = price_id;
		this.commodity = commodity;
		this.sku = sku;
		this.price = price;
		this.sale_price = sale_price;
		this.cost_price = cost_price;
		this.dian_bi = dian_bi;
		this.dian_fen = dian_fen;
		this.stocks = stocks;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "PRICE_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_PRICE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PRICE_GEN")
	@Column(name = "price_id", unique = true, nullable = false)
	public Long getPrice_id() {
		return this.price_id;
	}

	public void setPrice_id(Long price_id) {
		this.price_id = price_id;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commodity_id")
	public Commodity getCommodity() {
		return this.commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sku_id")
	public Sku getSku() {
		return this.sku;
	}

	public void setSku(Sku sku) {
		this.sku = sku;
	}

	@Column(name = "price", precision = 13)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "sale_price", precision = 13)
	public Double getSale_price() {
		return this.sale_price;
	}

	public void setSale_price(Double sale_price) {
		this.sale_price = sale_price;
	}

	@Column(name = "cost_price", precision = 13)
	public Double getCost_price() {
		return this.cost_price;
	}

	public void setCost_price(Double cost_price) {
		this.cost_price = cost_price;
	}

	@Column(name = "bi_price", precision = 13)
	public Double getBi_price() {
		return bi_price;
	}

	public void setBi_price(Double bi_price) {
		this.bi_price = bi_price;
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

	
	@Column(name = "special_price", precision = 13)
	public Double getSpecial_price() {
		return this.special_price;
	}

	public void setSpecial_price(Double special_price) {
		this.special_price = special_price;
	}

	@Column(name = "special_dian_fen")
	public Integer getSpecial_dian_fen() {
		return this.special_dian_fen;
	}

	public void setSpecial_dian_fen(Integer special_dian_fen) {
		this.special_dian_fen = special_dian_fen;
	}

	@Column(name = "special_dian_bi")
	public Integer getSpecial_dian_bi() {
		return this.special_dian_bi;
	}

	public void setSpecial_dian_bi(Integer special_dian_bi) {
		this.special_dian_bi = special_dian_bi;
	}
	
	@Column(name = "special_bi_price")
	public Double getSpecial_bi_price() {
		return special_bi_price;
	}

	public void setSpecial_bi_price(Double special_bi_price) {
		this.special_bi_price = special_bi_price;
	}

	
	@Column(name = "give_fen")
	public Integer getGive_fen() {
		return this.give_fen;
	}

	public void setGive_fen(Integer give_fen) {
		this.give_fen = give_fen;
	}
	
	@Column(name = "stocks")
	public Integer getStocks() {
		return this.stocks;
	}

	public void setStocks(Integer stocks) {
		this.stocks = stocks;
	}

	@Override
	public Object getEntityId() {
		return this.price_id;
	}
}