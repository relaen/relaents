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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ReturnReason entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_return_reason", catalog = "tiandian")
public class ReturnReason extends BasePojo implements java.io.Serializable {
	// Fields
	private Long return_reason_id;
	private String reason_name;
	private Integer return_type;
	private String remarks;
	private Set<ReturnInfo> returnInfos = new HashSet<ReturnInfo>(0);

	// Constructors
	/** default constructor */
	public ReturnReason() {
	}

	/** minimal constructor */
	public ReturnReason(Long return_reason_id) {
		this.return_reason_id = return_reason_id;
	}

	/** full constructor */
	public ReturnReason(Long return_reason_id, String reason_name, String remarks, Set<ReturnInfo> returnInfos) {
		this.return_reason_id = return_reason_id;
		this.reason_name = reason_name;
		this.remarks = remarks;
		this.returnInfos = returnInfos;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "RETURNREASON_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_RETURNREASON", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "RETURNREASON_GEN")
	@Column(name = "return_reason_id", unique = true, nullable = false)
	public Long getReturn_reason_id() {
		return this.return_reason_id;
	}

	public void setReturn_reason_id(Long return_reason_id) {
		this.return_reason_id = return_reason_id;
	}

	@Column(name = "reason_name", length = 50)
	public String getReason_name() {
		return this.reason_name;
	}

	public void setReason_name(String reason_name) {
		this.reason_name = reason_name;
	}

	@Column(name = "remarks", length = 200)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	@Column(name = "return_type", length = 200)
	public Integer getReturn_type() {
		return return_type;
	}

	public void setReturn_type(Integer return_type) {
		this.return_type = return_type;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "returnReason")
	public Set<ReturnInfo> getReturnInfos() {
		return this.returnInfos;
	}

	public void setReturnInfos(Set<ReturnInfo> returnInfos) {
		this.returnInfos = returnInfos;
	}

	@Override
	public Object getEntityId() {
		return this.return_reason_id;
	}
}