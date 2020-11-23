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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * FundFlowType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_fund_flow_type", catalog = "tiandian")

public class FundFlowType extends BasePojo implements java.io.Serializable {

	// Fields

	private Long fund_flow_type_id;
	private String type_name;
	private String remarks;
	private Set<FundFlow> fundFlows = new HashSet<FundFlow>(0);

	// Constructors

	/** default constructor */
	public FundFlowType() {
	}

	/** minimal constructor */
	public FundFlowType(Long fund_flow_type_id) {
		this.fund_flow_type_id = fund_flow_type_id;
	}

	/** full constructor */
	public FundFlowType(Long fund_flow_type_id, String type_name, String remarks, Set<FundFlow> fundFlows) {
		this.fund_flow_type_id = fund_flow_type_id;
		this.type_name = type_name;
		this.remarks = remarks;
		this.fundFlows = fundFlows;
	}

	// Property accessors
	@Id
	@Column(name = "fund_flow_type_id", unique = true, nullable = false)
	public Long getFund_flow_type_id() {
		return this.fund_flow_type_id;
	}

	public void setFund_flow_type_id(Long fund_flow_type_id) {
		this.fund_flow_type_id = fund_flow_type_id;
	}

	@Column(name = "type_name", length = 50)

	public String getType_name() {
		return this.type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	@Column(name = "remarks", length = 200)

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fundFlowType")

	public Set<FundFlow> getFundFlows() {
		return this.fundFlows;
	}

	public void setFundFlows(Set<FundFlow> fundFlows) {
		this.fundFlows = fundFlows;
	}
	
	@Override
	public Object getEntityId() {
		return this.fund_flow_type_id;
	}

}