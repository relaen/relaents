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
 * CateSpecRel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_cate_spec_value_rel", catalog = "tiandian")
public class CateSpecValueRel extends BasePojo implements java.io.Serializable {
	// Fields
	private Long cate_spec_value_rel_id;
	private CommodityCate commodityCate;
	private SpecValue specValue;

	@Override
	public Object getEntityId() {
		return this.cate_spec_value_rel_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "CATESPECVALUEREL_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_CATESPECVALUEREL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CATESPECVALUEREL_GEN")
	@Column(name = "cate_spec_value_rel_id", unique = true, nullable = false)
	public Long getCate_spec_value_rel_id() {
		return cate_spec_value_rel_id;
	}

	public void setCate_spec_value_rel_id(Long cate_spec_value_rel_id) {
		this.cate_spec_value_rel_id = cate_spec_value_rel_id;
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
	@JoinColumn(name = "spec_value_id")
	public SpecValue getSpecValue() {
		return specValue;
	}

	public void setSpecValue(SpecValue specValue) {
		this.specValue = specValue;
	}
}