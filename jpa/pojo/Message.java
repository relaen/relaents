package dao.pojo;

// default package
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * Message entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_message", catalog = "tiandian")
public class Message extends BasePojo implements java.io.Serializable {
	// Fields
	private Long message_id;
	private MessageType messageType;
	private ImageResource main_img;
	private Long send_time;
	private String msg_title;
	private String content;
	private Integer link_type; // 0链接；1商品、2活动、3秒杀、4砍价
	private String link_url;
	private Commodity commodity;
	private Activity activity;
	private Integer target_type; // 0所有会员；1推广大使；2区域经理；3商户会员；4指定会员
	private Set<MsgImg> msgImgs = new HashSet<MsgImg>(0);

	@Override
	public Object getEntityId() {
		return this.message_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "MESSAGE_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_MESSAGE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MESSAGE_GEN")
	@Column(name = "message_id", unique = true, nullable = false)
	public Long getMessage_id() {
		return this.message_id;
	}

	public void setMessage_id(Long message_id) {
		this.message_id = message_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "message_type_id")
	public MessageType getMessageType() {
		return this.messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "main_img_id")
	public ImageResource getMain_img() {
		return main_img;
	}

	public void setMain_img(ImageResource main_img) {
		this.main_img = main_img;
	}

	@Column(name = "send_time", nullable = false, length = 19)
	public Long getSend_time() {
		return this.send_time;
	}

	public void setSend_time(Long send_time) {
		this.send_time = send_time;
	}

	@Column(name = "msg_title", length = 200)
	public String getMsg_title() {
		return this.msg_title;
	}

	public void setMsg_title(String msg_title) {
		this.msg_title = msg_title;
	}

	@Column(name = "content", length = 500)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "link_type")
	public Integer getLink_type() {
		return link_type;
	}

	public void setLink_type(Integer link_type) {
		this.link_type = link_type;
	}

	@Column(name = "link_url")
	public String getLink_url() {
		return link_url;
	}

	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commodity_id")
	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "activity_id")
	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	@Column(name = "target_type")
	public Integer getTarget_type() {
		return target_type;
	}

	public void setTarget_type(Integer target_type) {
		this.target_type = target_type;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "message")
	public Set<MsgImg> getMsgImgs() {
		return this.msgImgs;
	}

	public void setMsgImgs(Set<MsgImg> msgImgs) {
		this.msgImgs = msgImgs;
	}
}