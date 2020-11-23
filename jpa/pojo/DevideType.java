package dao.pojo;
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
 * DevideType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_devide_type", catalog = "tiandian")

public class DevideType extends BasePojo implements java.io.Serializable {

	// Fields
	private Long devide_type_id;
	private String type_name;
	private String remarks;
	private Set<ProfitAssign> profitAssigns = new HashSet<ProfitAssign>(0);

	// Constructors

	/** default constructor */
	public DevideType() {
	}

	/** minimal constructor */
	public DevideType(Long devide_type_id) {
		this.devide_type_id = devide_type_id;
	}

	/** full constructor */
	public DevideType(Long devide_type_id, String type_name, String remarks, Set<ProfitAssign> profitAssigns) {
		this.devide_type_id = devide_type_id;
		this.type_name = type_name;
		this.remarks = remarks;
		this.profitAssigns = profitAssigns;
	}

	// Property accessors
	@Id
	@Column(name = "devide_type_id", unique = true, nullable = false)

	public Long getDevide_type_id() {
		return this.devide_type_id;
	}

	public void setDevide_type_id(Long devide_type_id) {
		this.devide_type_id = devide_type_id;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "devideType")

	public Set<ProfitAssign> getProfitAssigns() {
		return this.profitAssigns;
	}

	public void setProfitAssigns(Set<ProfitAssign> profitAssigns) {
		this.profitAssigns = profitAssigns;
	}

	@Override
	public Object getEntityId() {
		return this.devide_type_id;
	}
}