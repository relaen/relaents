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
 * AdvImg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_adv_img", catalog = "tiandian")
public class AdvImg extends BasePojo implements java.io.Serializable {
	// Fields
	private Long adv_img_id;
	private ImageResource imageResource;
	private Adv adv;
	private Integer is_main;

	// Constructors
	/** default constructor */
	public AdvImg() {
	}

	/** minimal constructor */
	public AdvImg(Long adv_img_id) {
		this.adv_img_id = adv_img_id;
	}

	/** full constructor */
	public AdvImg(Long adv_img_id, ImageResource imageResource, Adv adv, Integer is_main) {
		this.adv_img_id = adv_img_id;
		this.imageResource = imageResource;
		this.adv = adv;
		this.is_main = is_main;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "ADVIMG_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_ADVIMG", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ADVIMG_GEN")
	@Column(name = "adv_img_id", unique = true, nullable = false)
	public Long getAdv_img_id() {
		return this.adv_img_id;
	}

	public void setAdv_img_id(Long adv_img_id) {
		this.adv_img_id = adv_img_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "image_resource_id")
	public ImageResource getImageResource() {
		return this.imageResource;
	}

	public void setImageResource(ImageResource imageResource) {
		this.imageResource = imageResource;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "adv_id")
	public Adv getAdv() {
		return this.adv;
	}

	public void setAdv(Adv adv) {
		this.adv = adv;
	}

	@Column(name = "is_main")
	public Integer getIs_main() {
		return this.is_main;
	}

	public void setIs_main(Integer is_main) {
		this.is_main = is_main;
	}

	@Override
	public Object getEntityId() {
		return this.adv_img_id;
	}
}