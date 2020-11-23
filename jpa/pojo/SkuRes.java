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
 * SkuRes entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_sku_res", catalog = "tiandian")
public class SkuRes extends BasePojo implements java.io.Serializable {
	// Fields
	private Long sku_res_id;
	private Sku sku;
	private ImageResource imageResource;

	// Constructors
	/** default constructor */
	public SkuRes() {
	}

	/** minimal constructor */
	public SkuRes(Long sku_res_id) {
		this.sku_res_id = sku_res_id;
	}

	/** full constructor */
	public SkuRes(Long sku_res_id, Sku sku, ImageResource imageResource) {
		this.sku_res_id = sku_res_id;
		this.sku = sku;
		this.imageResource = imageResource;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "SKURES_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_SKURES", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SKURES_GEN")
	@Column(name = "sku_res_id", unique = true, nullable = false)
	public Long getSku_res_id() {
		return this.sku_res_id;
	}

	public void setSku_res_id(Long sku_res_id) {
		this.sku_res_id = sku_res_id;
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
	@JoinColumn(name = "image_resource_id")
	public ImageResource getImageResource() {
		return this.imageResource;
	}

	public void setImageResource(ImageResource imageResource) {
		this.imageResource = imageResource;
	}

	@Override
	public Object getEntityId() {
		return this.sku_res_id;
	}
}