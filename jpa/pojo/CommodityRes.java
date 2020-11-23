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
 * CommodityRes entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_commodity_res", catalog = "tiandian")
public class CommodityRes extends BasePojo implements java.io.Serializable {
	// Fields
	private Long commodity_res_id;
	private Commodity commodity;
	private ImageResource imageResource;
	private Integer img_res_type;
	
	// Constructors
	/** default constructor */
	public CommodityRes() {
	}

	/** minimal constructor */
	public CommodityRes(Long commodity_res_id) {
		this.commodity_res_id = commodity_res_id;
	}

	/** full constructor */
	public CommodityRes(Long commodity_res_id, Commodity commodity, ImageResource imageResource, Integer img_res_type) {
		this.commodity_res_id = commodity_res_id;
		this.commodity = commodity;
		this.imageResource = imageResource;
		this.img_res_type = img_res_type;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "COMMODITYRES_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COMMODITYRES", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMMODITYRES_GEN")
	@Column(name = "commodity_res_id", unique = true, nullable = false)
	public Long getCommodity_res_id() {
		return this.commodity_res_id;
	}

	public void setCommodity_res_id(Long commodity_res_id) {
		this.commodity_res_id = commodity_res_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commodity_id")
	public Commodity getCommodity() {
		return this.commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "image_resource_id")
	public ImageResource getImageResource() {
		return this.imageResource;
	}

	public void setImageResource(ImageResource imageResource) {
		this.imageResource = imageResource;
	}

	@Column(name = "img_res_type")
	public Integer getImg_res_type() {
		return img_res_type;
	}

	public void setImg_res_type(Integer img_res_type) {
		this.img_res_type = img_res_type;
	}	
	
	@Override
	public Object getEntityId() {
		return this.commodity_res_id;
	}

}