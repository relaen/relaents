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
 * GrowType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_grow_type", catalog = "tiandian")
public class GrowType extends BasePojo implements java.io.Serializable {
	// Fields
	private Long grow_type_id;
	private String grow_type_name;
	private String img_url;
	private Integer value=0;
	private Integer onetime = 0;
	private String trigger_name;
	private String method_name;
	private String remarks;
	private Set<GrowRcd> growRcds = new HashSet<GrowRcd>(0);
	private Set<GrowRule> growRules = new HashSet<GrowRule>(0);

	// Constructors
	/** default constructor */
	public GrowType() {
	}

	/** minimal constructor */
	public GrowType(Long grow_type_id) {
		this.grow_type_id = grow_type_id;
	}

	/** full constructor */
	public GrowType(Long grow_type_id, String grow_type_name, Set<GrowRcd> growRcds, Set<GrowRule> growRules) {
		this.grow_type_id = grow_type_id;
		this.grow_type_name = grow_type_name;
		this.growRcds = growRcds;
		this.growRules = growRules;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "GROWTYPE_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_GROWTYPE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GROWTYPE_GEN")
	@Column(name = "grow_type_id", unique = true, nullable = false)
	public Long getGrow_type_id() {
		return this.grow_type_id;
	}

	public void setGrow_type_id(Long grow_type_id) {
		this.grow_type_id = grow_type_id;
	}

	@Column(name = "grow_type_name", length = 50)
	public String getGrow_type_name() {
		return this.grow_type_name;
	}

	public void setGrow_type_name(String grow_type_name) {
		this.grow_type_name = grow_type_name;
	}
	
	
	@Column(name = "img_url", length = 500)
	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	@Column(name = "value")
	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Column(name = "onetime")
	public Integer getOnetime() {
		return onetime;
	}

	public void setOnetime(Integer onetime) {
		this.onetime = onetime;
	}

	@Column(name = "trigger_name", length = 50)
	public String getTrigger_name() {
		return trigger_name;
	}

	public void setTrigger_name(String trigger_name) {
		this.trigger_name = trigger_name;
	}

	@Column(name = "method_name", length = 50)
	public String getMethod_name() {
		return method_name;
	}

	public void setMethod_name(String method_name) {
		this.method_name = method_name;
	}

	@Column(name = "remarks", length = 50)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "growType")
	public Set<GrowRcd> getGrowRcds() {
		return this.growRcds;
	}

	public void setGrowRcds(Set<GrowRcd> growRcds) {
		this.growRcds = growRcds;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "growType")
	public Set<GrowRule> getGrowRules() {
		return this.growRules;
	}

	public void setGrowRules(Set<GrowRule> growRules) {
		this.growRules = growRules;
	}

	@Override
	public Object getEntityId() {
		return this.grow_type_id;
	}
}