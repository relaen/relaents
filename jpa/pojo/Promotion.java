package dao.pojo;

import javax.persistence.TableGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
// default package
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Promotion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_promotion", catalog = "tiandian")
public class Promotion extends BasePojo implements java.io.Serializable {
	// Fields
	private Long promotion_commodity_id;
	private Commodity commodity;
	private Long start_time;
	private Long end_time;
	private Integer sort_order;
	private String promotion_img;
	private Set<PromotionImg> promotionImgs = new HashSet<PromotionImg>(0);

	// Constructors
	/** default constructor */
	public Promotion() {
	}

	/** minimal constructor */
	public Promotion(Long promotion_commodity_id, Long start_time, Long end_time) {
		this.promotion_commodity_id = promotion_commodity_id;
		this.start_time = start_time;
		this.end_time = end_time;
	}

	/** full constructor */
	public Promotion(Long promotion_commodity_id, Commodity commodity, Long start_time, Long end_time,
			Integer sort_order, String promotion_img, Set<PromotionImg> promotionImgs) {
		this.promotion_commodity_id = promotion_commodity_id;
		this.commodity = commodity;
		this.start_time = start_time;
		this.end_time = end_time;
		this.sort_order = sort_order;
		this.promotion_img = promotion_img;
		this.promotionImgs = promotionImgs;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "PROMOTION_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_PROMOTION", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PROMOTION_GEN")
	@Column(name = "promotion_commodity_id", unique = true, nullable = false)
	public Long getPromotion_commodity_id() {
		return this.promotion_commodity_id;
	}

	public void setPromotion_commodity_id(Long promotion_commodity_id) {
		this.promotion_commodity_id = promotion_commodity_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commodity_id")
	public Commodity getCommodity() {
		return this.commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	@Column(name = "start_time", nullable = false, length = 19)
	public Long getStart_time() {
		return this.start_time;
	}

	public void setStart_time(Long start_time) {
		this.start_time = start_time;
	}

	@Column(name = "end_time", nullable = false, length = 19)
	public Long getEnd_time() {
		return this.end_time;
	}

	public void setEnd_time(Long end_time) {
		this.end_time = end_time;
	}

	@Column(name = "sort_order")
	public Integer getSort_order() {
		return this.sort_order;
	}

	public void setSort_order(Integer sort_order) {
		this.sort_order = sort_order;
	}

	@Column(name = "promotion_img", length = 500)
	public String getPromotion_img() {
		return this.promotion_img;
	}

	public void setPromotion_img(String promotion_img) {
		this.promotion_img = promotion_img;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "promotion")
	public Set<PromotionImg> getPromotionImgs() {
		return this.promotionImgs;
	}

	public void setPromotionImgs(Set<PromotionImg> promotionImgs) {
		this.promotionImgs = promotionImgs;
	}

	@Override
	public Object getEntityId() {
		return this.promotion_commodity_id;
	}
}