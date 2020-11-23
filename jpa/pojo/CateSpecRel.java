package dao.pojo;

import javax.persistence.TableGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
// default package
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CateSpecRel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_cate_spec_rel", catalog = "tiandian")
public class CateSpecRel extends BasePojo implements java.io.Serializable {
	// Fields
	private Long cate_spec_rel_id;
	private CommodityCate commodityCate;
	private Spec spec;

	// Constructors
	/** default constructor */
	public CateSpecRel() {
	}

	/** minimal constructor */
	public CateSpecRel(Long cate_spec_rel_id) {
		this.cate_spec_rel_id = cate_spec_rel_id;
	}

	/** full constructor */
	public CateSpecRel(Long cate_spec_rel_id, CommodityCate commodityCate, Spec spec) {
		this.cate_spec_rel_id = cate_spec_rel_id;
		this.commodityCate = commodityCate;
		this.spec = spec;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "CATESPECREL_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_CATESPECREL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CATESPECREL_GEN")
	@Column(name = "cate_spec_rel_id", unique = true, nullable = false)
	public Long getCate_spec_rel_id() {
		return this.cate_spec_rel_id;
	}

	public void setCate_spec_rel_id(Long cate_spec_rel_id) {
		this.cate_spec_rel_id = cate_spec_rel_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commodity_cate_id")
	public CommodityCate getCommodityCate() {
		return this.commodityCate;
	}

	public void setCommodityCate(CommodityCate commodityCate) {
		this.commodityCate = commodityCate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "spec_id")
	public Spec getSpec() {
		return this.spec;
	}

	public void setSpec(Spec spec) {
		this.spec = spec;
	}

	@Override
	public Object getEntityId() {
		return this.cate_spec_rel_id;
	}
}