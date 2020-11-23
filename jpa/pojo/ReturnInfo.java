package dao.pojo;

import javax.persistence.TableGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
// default package
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * ReturnInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_return_info", catalog = "tiandian")
public class ReturnInfo extends BasePojo implements java.io.Serializable {
	// Fields
	private Long return_info_id;
	private ReturnReason returnReason;
	private ReturnState returnState;
	private Double return_money;
	private Long create_time;
	private Long finish_time;
	private String aggainst_reason;
	private Long agree_time;
	private String reason;
	private Integer return_type; // 1退货2换货
	private Integer is_agree;
	private Orders orders;
	private String remarks;
	private Set<ReturnRcd> returnProcesses = new HashSet<ReturnRcd>(0);
	private Set<ReturnImg> returnImgs = new HashSet<ReturnImg>(0);
	private Set<ReturnCommodity> returnCommodities = new HashSet<ReturnCommodity>(0);
	private Set<PostInfo> postInfos = new HashSet<PostInfo>(0);
	
	// Constructors
	/** default constructor */
	public ReturnInfo() {
	}

	/** minimal constructor */
	public ReturnInfo(Long return_info, Long create_time, Long finish_time, Long agree_time) {
		this.return_info_id = return_info;
		this.create_time = create_time;
		this.finish_time = finish_time;
		this.agree_time = agree_time;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "RETURNINFO_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_RETURNINFO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "RETURNINFO_GEN")
	@Column(name = "return_info_id", unique = true, nullable = false)
	public Long getReturn_info_id() {
		return this.return_info_id;
	}

	public void setReturn_info_id(Long return_info_id) {
		this.return_info_id = return_info_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "return_reason_id")
	public ReturnReason getReturnReason() {
		return this.returnReason;
	}

	public void setReturnReason(ReturnReason returnReason) {
		this.returnReason = returnReason;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "return_state_id")
	public ReturnState getReturnState() {
		return this.returnState;
	}

	public void setReturnState(ReturnState returnState) {
		this.returnState = returnState;
	}

	@Column(name = "return_money", precision = 13)
	public Double getReturn_money() {
		return this.return_money;
	}
	public void setReturn_money(Double return_money) {
		this.return_money = return_money;
	}

	@Column(name = "create_time", nullable = false, length = 19)
	public Long getCreate_time() {
		return this.create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	@Column(name = "finish_time", nullable = false, length = 19)
	public Long getFinish_time() {
		return this.finish_time;
	}

	public void setFinish_time(Long finish_time) {
		this.finish_time = finish_time;
	}

	@Column(name = "aggainst_reason", length = 200)
	public String getAggainst_reason() {
		return this.aggainst_reason;
	}

	public void setAggainst_reason(String aggainst_reason) {
		this.aggainst_reason = aggainst_reason;
	}

	@Column(name = "agree_time", nullable = false, length = 19)
	public Long getAgree_time() {
		return this.agree_time;
	}

	public void setAgree_time(Long agree_time) {
		this.agree_time = agree_time;
	}

	@Column(name = "reason", length = 500)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "return_type")
	public Integer getReturn_type() {
		return return_type;
	}

	public void setReturn_type(Integer return_type) {
		this.return_type = return_type;
	}

	
	@Column(name = "is_agree")
	public Integer getIs_agree() {
		return is_agree;
	}
	public void setIs_agree(Integer is_agree) {
		this.is_agree = is_agree;
	}

	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	public Orders getOrders() {
		return this.orders;
	}

	public void setOrders(Orders order) {
		this.orders = order;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "returnInfo")
	public Set<ReturnRcd> getReturnProcesses() {
		return this.returnProcesses;
	}

	public void setReturnProcesses(Set<ReturnRcd> returnProcesses) {
		this.returnProcesses = returnProcesses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "returnInfo")
	public Set<ReturnImg> getReturnImgs() {
		return this.returnImgs;
	}

	public void setReturnImgs(Set<ReturnImg> returnImgs) {
		this.returnImgs = returnImgs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "returnInfo")
	public Set<ReturnCommodity> getReturnCommodities() {
		return returnCommodities;
	}

	public void setReturnCommodities(Set<ReturnCommodity> returnCommodities) {
		this.returnCommodities = returnCommodities;
	}
	

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "returnInfo")
	public Set<PostInfo> getPostInfos() {
		return postInfos;
	}

	public void setPostInfos(Set<PostInfo> postInfos) {
		this.postInfos = postInfos;
	}

	@Override
	public Object getEntityId() {
		return this.return_info_id;
	}
}