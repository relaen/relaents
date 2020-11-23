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
 * PostRcd entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_post_rcd", catalog = "tiandian")

public class PostRcd extends BasePojo implements java.io.Serializable {

	// Fields

	private Long post_rcd_id;
	private PostInfo postInfo;
	private Long create_time;
	private String descs;
	private Integer post_rcd_type;

	// Constructors

	/** default constructor */
	public PostRcd() {
	}

	/** minimal constructor */
	public PostRcd(Long post_rcd_id) {
		this.post_rcd_id = post_rcd_id;
	}

	/** full constructor */
	public PostRcd(Long post_rcd_id, PostInfo postInfo, Long create_time, String descs) {
		this.post_rcd_id = post_rcd_id;
		this.postInfo = postInfo;
		this.create_time = create_time;
		this.descs = descs;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "POSTRCD_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_POSTRCD", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "POSTRCD_GEN")
	@Column(name = "post_rcd_id", unique = true, nullable = false)
	public Long getPost_rcd_id() {
		return this.post_rcd_id;
	}

	public void setPost_rcd_id(Long post_rcd_id) {
		this.post_rcd_id = post_rcd_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	public PostInfo getPostInfo() {
		return this.postInfo;
	}

	public void setPostInfo(PostInfo postInfo) {
		this.postInfo = postInfo;
	}

	@Column(name = "create_time")
	public Long getCreate_time() {
		return this.create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	@Column(name = "descs", length = 500)
	public String getDescs() {
		return this.descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
	}

	@Column(name = "post_rcd_type")
	public Integer getPost_rcd_type() {
		return post_rcd_type;
	}

	public void setPost_rcd_type(Integer post_rcd_type) {
		this.post_rcd_type = post_rcd_type;
	}

	@Override
	public Object getEntityId() {
		return this.post_rcd_id;
	}
}