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
 * ShoppingCart entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_shopping_cart", catalog = "tiandian")
public class ShoppingCart extends BasePojo implements java.io.Serializable {
	// Fields
	private Long shopping_cart_id;
	private Sku sku;
	private Member member;
	private Commodity commodity;
	private Long create_time;
	private Integer commodity_num;
	private Double cart_price;
	private Integer is_fen = 0;

	// Constructors
	/** default constructor */
	public ShoppingCart() {
	}

	/** minimal constructor */
	public ShoppingCart(Long shopping_cart_id, Long create_time) {
		this.shopping_cart_id = shopping_cart_id;
		this.create_time = create_time;
	}

	/** full constructor */
	public ShoppingCart(Long shopping_cart_id, Sku sku, Member member, Long create_time, Integer commodity_num,
			Double cart_price) {
		this.shopping_cart_id = shopping_cart_id;
		this.sku = sku;
		this.member = member;
		this.create_time = create_time;
		this.commodity_num = commodity_num;
		this.cart_price = cart_price;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "SHOPPINGCART_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_SHOPPINGCART", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SHOPPINGCART_GEN")
	@Column(name = "shopping_cart_id", unique = true, nullable = false)
	public Long getShopping_cart_id() {
		return this.shopping_cart_id;
	}

	public void setShopping_cart_id(Long shopping_cart_id) {
		this.shopping_cart_id = shopping_cart_id;
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
	@JoinColumn(name = "member_id")
	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commodity_id")
	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	@Column(name = "create_time", nullable = false, length = 19)
	public Long getCreate_time() {
		return this.create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	@Column(name = "commodity_num")
	public Integer getCommodity_num() {
		return this.commodity_num;
	}

	public void setCommodity_num(Integer commodity_num) {
		this.commodity_num = commodity_num;
	}

	@Column(name = "cart_price", precision = 13)
	public Double getCart_price() {
		return this.cart_price;
	}

	public void setCart_price(Double cart_price) {
		this.cart_price = cart_price;
	}

	@Column(name = "is_fen")
	public Integer getIs_fen() {
		return is_fen;
	}
	
	public void setIs_fen(Integer is_fen) {
		this.is_fen = is_fen;
	}

	@Override
	public Object getEntityId() {
		return this.shopping_cart_id;
	}
}