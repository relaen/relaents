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
 * PropCateRel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_prop_cate_rel", catalog = "tiandian")

public class PropCateRel extends BasePojo implements java.io.Serializable {

	// Fields

	private Long prop_cate_rel_id;
	private CommodityProp commodityProp;
	private CommodityCate commodityCate;

	// Constructors

	/** default constructor */
	public PropCateRel() {
	}

	/** minimal constructor */
	public PropCateRel(Long prop_cate_rel_id) {
		this.prop_cate_rel_id = prop_cate_rel_id;
	}


	// Property accessors
	@Id
	@TableGenerator(name = "PROPCATEREL_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_PROPCATEREL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PROPCATEREL_GEN")
	@Column(name = "prop_cate_rel_id", unique = true, nullable = false)

	public Long getProp_cate_rel_id() {
		return this.prop_cate_rel_id;
	}

	public void setProp_cate_rel_id(Long prop_cate_rel_id) {
		this.prop_cate_rel_id = prop_cate_rel_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commodity_prop_id")

	public CommodityProp getCommodityProp() {
		return this.commodityProp;
	}

	public void setCommodityProp(CommodityProp commodityProp) {
		this.commodityProp = commodityProp;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commodity_cate_id")

	public CommodityCate getCommodityCate() {
		return this.commodityCate;
	}

	public void setCommodityCate(CommodityCate commodityCate) {
		this.commodityCate = commodityCate;
	}

	@Override
	public Object getEntityId() {
		return this.prop_cate_rel_id;
	}

}