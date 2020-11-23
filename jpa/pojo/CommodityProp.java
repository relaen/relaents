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
 * CommodityProp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_commodity_prop", catalog = "tiandian")
public class CommodityProp extends BasePojo implements java.io.Serializable {
	// Fields
	private Long commodity_prop_id;
	private String prop_name;
	private String remarks;
	private Set<CommodityPropValue> commodityPropValues = new HashSet<CommodityPropValue>(0);
	private Set<PropCateRel> propCateRels = new HashSet<PropCateRel>(0);
	// Constructors
	/** default constructor */
	public CommodityProp() {
	}

	/** minimal constructor */
	public CommodityProp(Long commodity_prop_id) {
		this.commodity_prop_id = commodity_prop_id;
	}

	/** full constructor */
	public CommodityProp(Long commodity_prop_id, String prop_name, String remarks,
			Set<CommodityPropValue> commodityPropValues) {
		this.commodity_prop_id = commodity_prop_id;
		this.prop_name = prop_name;
		this.remarks = remarks;
		this.commodityPropValues = commodityPropValues;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "COMMODITYPROP_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COMMODITYPROP", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMMODITYPROP_GEN")
	@Column(name = "commodity_prop_id", unique = true, nullable = false)
	public Long getCommodity_prop_id() {
		return this.commodity_prop_id;
	}

	public void setCommodity_prop_id(Long commodity_prop_id) {
		this.commodity_prop_id = commodity_prop_id;
	}

	@Column(name = "prop_name", length = 200)
	public String getProp_name() {
		return this.prop_name;
	}

	public void setProp_name(String prop_name) {
		this.prop_name = prop_name;
	}

	@Column(name = "remarks", length = 200)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commodityProp")
	public Set<CommodityPropValue> getCommodityPropValues() {
		return this.commodityPropValues;
	}

	public void setCommodityPropValues(Set<CommodityPropValue> commodityPropValues) {
		this.commodityPropValues = commodityPropValues;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commodityProp")
	public Set<PropCateRel> getPropCateRels() {
		return propCateRels;
	}

	public void setPropCateRels(Set<PropCateRel> propCateRels) {
		this.propCateRels = propCateRels;
	}
	
	
	@Override
	public Object getEntityId() {
		return this.commodity_prop_id;
	}
}