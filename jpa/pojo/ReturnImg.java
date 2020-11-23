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
 * ReturnImg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_return_img", catalog = "tiandian")
public class ReturnImg extends BasePojo implements java.io.Serializable {
	// Fields
	private Long return_img_id;
	private ReturnInfo returnInfo;
	private ImageResource imageResource;

	// Constructors
	/** default constructor */
	public ReturnImg() {
	}

	/** minimal constructor */
	public ReturnImg(Long return_img_id) {
		this.return_img_id = return_img_id;
	}

	/** full constructor */
	public ReturnImg(Long return_img_id, ReturnInfo returnInfo, ImageResource imageResource) {
		this.return_img_id = return_img_id;
		this.returnInfo = returnInfo;
		this.imageResource = imageResource;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "RETURNIMG_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_RETURNIMG", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "RETURNIMG_GEN")
	@Column(name = "return_img_id", unique = true, nullable = false)
	public Long getReturn_img_id() {
		return this.return_img_id;
	}

	public void setReturn_img_id(Long return_img_id) {
		this.return_img_id = return_img_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "return_info_id")
	public ReturnInfo getReturnInfo() {
		return this.returnInfo;
	}

	public void setReturnInfo(ReturnInfo returnInfo) {
		this.returnInfo = returnInfo;
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
		return this.return_img_id;
	}
}