package dao.pojo;

import javax.persistence.TableGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
// default package
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * PostInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_post_info", catalog = "tiandian")
public class PostInfo extends BasePojo implements java.io.Serializable {
	// Fields
	private Long post_id;
	private Orders orders;
	private Logistics logistics;
	private String post_no;
	private Long create_time;
	private Integer post_type=1;  //卖家发货1  买家发货2 
	private ReturnInfo returnInfo;
	private Integer finished=0;
	private String remarks;
	private Set<PostRcd> postRcds = new HashSet<PostRcd>(0);
	// Constructors
	/** default constructor */
	public PostInfo() {
	}

	/** minimal constructor */
	public PostInfo(Long post_id, Long create_time) {
		this.post_id = post_id;
		this.create_time = create_time;
	}

	/** full constructor */
	public PostInfo(Long post_id, Orders orders, Logistics logistics, String post_no, Long create_time,
			String remarks) {
		this.post_id = post_id;
		this.orders = orders;
		this.logistics = logistics;
		this.post_no = post_no;
		this.create_time = create_time;
		this.remarks = remarks;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "POSTINFO_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_POSTINFO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "POSTINFO_GEN")
	@Column(name = "post_id", unique = true, nullable = false)
	public Long getPost_id() {
		return this.post_id;
	}

	public void setPost_id(Long post_id) {
		this.post_id = post_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	public Orders getOrders() {
		return this.orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "logistics_id")
	public Logistics getLogistics() {
		return this.logistics;
	}

	public void setLogistics(Logistics logistics) {
		this.logistics = logistics;
	}

	@Column(name = "post_no", length = 50)
	public String getPost_no() {
		return this.post_no;
	}

	public void setPost_no(String post_no) {
		this.post_no = post_no;
	}

	@Column(name = "create_time")
	public Long getCreate_time() {
		return this.create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}
	
	@Column(name = "finished")
	public Integer getFinished() {
		return finished;
	}

	public void setFinished(Integer finished) {
		this.finished = finished;
	}

	@Column(name = "post_type")
	public Integer getPost_type() {
		return post_type;
	}

	public void setPost_type(Integer post_type) {
		this.post_type = post_type;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "return_info_id")
	public ReturnInfo getReturnInfo() {
		return returnInfo;
	}

	public void setReturnInfo(ReturnInfo returnInfo) {
		this.returnInfo = returnInfo;
	}

	@Column(name = "remarks", length = 200)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "postInfo")
	public Set<PostRcd> getPostRcds() {
		return postRcds;
	}

	public void setPostRcds(Set<PostRcd> postRcds) {
		this.postRcds = postRcds;
	}

	@Override
	public Object getEntityId() {
		return this.post_id;
	}
}