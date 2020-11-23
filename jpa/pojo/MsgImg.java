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
 * MsgImg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_msg_img", catalog = "tiandian")
public class MsgImg extends BasePojo implements java.io.Serializable {
	// Fields
	private Long msg_img_id;
	private Message message;
	private ImageResource imageResource;

	// Constructors
	/** default constructor */
	public MsgImg() {
	}

	/** minimal constructor */
	public MsgImg(Long msg_img_id) {
		this.msg_img_id = msg_img_id;
	}

	/** full constructor */
	public MsgImg(Long msg_img_id, Message message, ImageResource imageResource) {
		this.msg_img_id = msg_img_id;
		this.message = message;
		this.imageResource = imageResource;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "MSGIMG_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_MSGIMG", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MSGIMG_GEN")
	@Column(name = "msg_img_id", unique = true, nullable = false)
	public Long getMsg_img_id() {
		return this.msg_img_id;
	}

	public void setMsg_img_id(Long msg_img_id) {
		this.msg_img_id = msg_img_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "message_id")
	public Message getMessage() {
		return this.message;
	}

	public void setMessage(Message message) {
		this.message = message;
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
		return this.msg_img_id;
	}
}