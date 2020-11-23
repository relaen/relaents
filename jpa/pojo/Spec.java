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
 * Spec entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_spec", catalog = "tiandian")
public class Spec extends BasePojo implements java.io.Serializable {
	// Fields
	private Long spec_id;
	private String spec_name;
	private Set<CateSpecRel> cateSpecRels = new HashSet<CateSpecRel>(0);

	// Constructors
	/** default constructor */
	public Spec() {
	}

	/** minimal constructor */
	public Spec(Long spec_id) {
		this.spec_id = spec_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "SPEC_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_SPEC", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SPEC_GEN")
	@Column(name = "spec_id", unique = true, nullable = false)
	public Long getSpec_id() {
		return this.spec_id;
	}

	public void setSpec_id(Long spec_id) {
		this.spec_id = spec_id;
	}

	@Column(name = "spec_name", length = 50)
	public String getSpec_name() {
		return this.spec_name;
	}

	public void setSpec_name(String spec_name) {
		this.spec_name = spec_name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "spec")
	public Set<CateSpecRel> getCateSpecRels() {
		return this.cateSpecRels;
	}

	public void setCateSpecRels(Set<CateSpecRel> cateSpecRels) {
		this.cateSpecRels = cateSpecRels;
	}

	@Override
	public Object getEntityId() {
		return this.spec_id;
	}
}