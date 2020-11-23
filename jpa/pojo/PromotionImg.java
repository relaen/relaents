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
 * PromotionImg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_promotion_img", catalog = "tiandian")
public class PromotionImg extends BasePojo implements java.io.Serializable {
	// Fields
	private Long promotion_img_id;
	private Promotion promotion;
	private ImageResource imageResource;

	// Constructors
	/** default constructor */
	public PromotionImg() {
	}

	/** minimal constructor */
	public PromotionImg(Long promotion_img_id) {
		this.promotion_img_id = promotion_img_id;
	}

	/** full constructor */
	public PromotionImg(Long promotion_img_id, Promotion promotion, ImageResource imageResource) {
		this.promotion_img_id = promotion_img_id;
		this.promotion = promotion;
		this.imageResource = imageResource;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "PROMOTIONIMG_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_PROMOTIONIMG", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PROMOTIONIMG_GEN")
	@Column(name = "promotion_img_id", unique = true, nullable = false)
	public Long getPromotion_img_id() {
		return this.promotion_img_id;
	}

	public void setPromotion_img_id(Long promotion_img_id) {
		this.promotion_img_id = promotion_img_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "promotion_commodity_id")
	public Promotion getPromotion() {
		return this.promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "image_resource_id")
	public ImageResource getImageResource() {
		return this.imageResource;
	}

	public void setImageResource(ImageResource imageResource) {
		this.imageResource = imageResource;
	}

	@Override
	public Object getEntityId() {
		return this.promotion_img_id;
	}
}