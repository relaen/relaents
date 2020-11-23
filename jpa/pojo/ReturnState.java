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
 * ReturnState entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_return_state", catalog = "tiandian")
public class ReturnState extends BasePojo implements java.io.Serializable {
	// Fields
	private Long return_state_id;
	private String return_state_name;
	private String remarks;
	private Set<ReturnRcd> returnRcds = new HashSet<ReturnRcd>(0);
	private Set<ReturnInfo> returnInfos = new HashSet<ReturnInfo>(0);

	// Constructors
	/** default constructor */
	public ReturnState() {
	}

	/** minimal constructor */
	public ReturnState(Long return_state_id) {
		this.return_state_id = return_state_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "RETURNSTATE_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_RETURNSTATE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "RETURNSTATE_GEN")
	@Column(name = "return_state_id", unique = true, nullable = false)
	public Long getReturn_state_id() {
		return this.return_state_id;
	}

	public void setReturn_state_id(Long return_state_id) {
		this.return_state_id = return_state_id;
	}

	@Column(name = "return_state_name", length = 50)
	public String getReturn_state_name() {
		return this.return_state_name;
	}

	public void setReturn_state_name(String return_state_name) {
		this.return_state_name = return_state_name;
	}

	@Column(name = "remarks", length = 200)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "returnState")
	public Set<ReturnRcd> getReturnRcds() {
		return this.returnRcds;
	}

	public void setReturnRcds(Set<ReturnRcd> returnRcds) {
		this.returnRcds = returnRcds;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "returnState")
	public Set<ReturnInfo> getReturnInfos() {
		return this.returnInfos;
	}

	public void setReturnInfos(Set<ReturnInfo> returnInfos) {
		this.returnInfos = returnInfos;
	}

	@Override
	public Object getEntityId() {
		return this.return_state_id;
	}
}