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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * SpecValue entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_spec_value", catalog = "tiandian")

public class SpecValue extends BasePojo implements java.io.Serializable {

	// Fields
	private Long spec_value_id;
	private CateSpecRel cateSpecRel;
	private String value;
	private Set<CateSpecValueRel> cateSpecValueRels = new HashSet<CateSpecValueRel>(0);
	private Set<SkuSpecValue> skuSpecValues = new HashSet<SkuSpecValue>(0);
	private Set<CommoditySpecValue> commoditySpecValues = new HashSet<CommoditySpecValue>(0);

	// Constructors

	/** default constructor */
	public SpecValue() {
	}

	/** minimal constructor */
	public SpecValue(Long spec_value_id) {
		this.spec_value_id = spec_value_id;
	}

	/** full constructor */
	public SpecValue(Long spec_value_id, CateSpecRel cateSpecRel, String value, Set<CateSpecValueRel> cateSpecValueRels,
			Set<SkuSpecValue> skuSpecValues, Set<CommoditySpecValue> commoditySpecValues) {
		this.spec_value_id = spec_value_id;
		this.cateSpecRel = cateSpecRel;
		this.value = value;
		this.cateSpecValueRels = cateSpecValueRels;
		this.skuSpecValues = skuSpecValues;
		this.commoditySpecValues = commoditySpecValues;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "SPECVALUE_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_SPECVALUE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SPECVALUE_GEN")
	@Column(name = "spec_value_id", unique = true, nullable = false)
	public Long getSpec_value_id() {
		return this.spec_value_id;
	}

	public void setSpec_value_id(Long spec_value_id) {
		this.spec_value_id = spec_value_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cate_spec_rel_id")

	public CateSpecRel getCateSpecRel() {
		return this.cateSpecRel;
	}

	public void setCateSpecRel(CateSpecRel cateSpecRel) {
		this.cateSpecRel = cateSpecRel;
	}

	@Column(name = "value", length = 50)

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "specValue")

	public Set<CateSpecValueRel> getCateSpecValueRels() {
		return this.cateSpecValueRels;
	}

	public void setCateSpecValueRels(Set<CateSpecValueRel> cateSpecValueRels) {
		this.cateSpecValueRels = cateSpecValueRels;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "specValue")

	public Set<SkuSpecValue> getSkuSpecValues() {
		return this.skuSpecValues;
	}

	public void setSkuSpecValues(Set<SkuSpecValue> skuSpecValues) {
		this.skuSpecValues = skuSpecValues;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "specValue")

	public Set<CommoditySpecValue> getCommoditySpecValues() {
		return this.commoditySpecValues;
	}

	public void setCommoditySpecValues(Set<CommoditySpecValue> commoditySpecValues) {
		this.commoditySpecValues = commoditySpecValues;
	}

	@Override
	public Object getEntityId() {
		return this.spec_value_id;
	}
}