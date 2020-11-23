package dao.pojo;

// default package
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
 * ActivityImg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_activity_img")
public class ActivityImg extends BasePojo implements java.io.Serializable {
	// Fields
	private Long activity_img_id;
	private Activity activity;
	private ImageResource imageResource;
	private Integer img_type;

	// Constructors
	/** default constructor */
	public ActivityImg() {
	}

	/** minimal constructor */
	public ActivityImg(Long activity_img_id) {
		this.activity_img_id = activity_img_id;
	}

	/** full constructor */
	public ActivityImg(Long activity_img_id, Activity activity, ImageResource imageResource) {
		this.activity_img_id = activity_img_id;
		this.activity = activity;
		this.imageResource = imageResource;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "ACTIVITYIMG_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_ACTIVITYIMG", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ACTIVITYIMG_GEN")
	@Column(name = "activity_img_id", unique = true, nullable = false)
	public Long getActivity_img_id() {
		return this.activity_img_id;
	}

	public void setActivity_img_id(Long activity_img_id) {
		this.activity_img_id = activity_img_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "activity_id")
	public Activity getActivity() {
		return this.activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "image_resource_id")
	public ImageResource getImageResource() {
		return this.imageResource;
	}

	public void setImageResource(ImageResource imageResource) {
		this.imageResource = imageResource;
	}

	@Column(name = "img_type")
	public Integer getImg_type() {
		return img_type;
	}

	public void setImg_type(Integer img_type) {
		this.img_type = img_type;
	}

	@Override
	public Object getEntityId() {
		return this.activity_img_id;
	}
}