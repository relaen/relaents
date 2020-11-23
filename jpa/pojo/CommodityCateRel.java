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
 * CommodityCateRel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_commodity_cate_rel", catalog = "tiandian")
public class CommodityCateRel extends BasePojo implements java.io.Serializable {
	// Fields
	private Long commodity_cate_rel_id;
	private Commodity commodity;
	private CommodityCate commodityCate;

	// Constructors
	/** default constructor */
	public CommodityCateRel() {
	}

	/** minimal constructor */
	public CommodityCateRel(Long commodity_cate_rel_id) {
		this.commodity_cate_rel_id = commodity_cate_rel_id;
	}

	/** full constructor */
	public CommodityCateRel(Long commodity_cate_rel_id, Commodity commodity, CommodityCate commodityCate) {
		this.commodity_cate_rel_id = commodity_cate_rel_id;
		this.commodity = commodity;
		this.commodityCate = commodityCate;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "COMMODITYCATEREL_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COMMODITYCATEREL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMMODITYCATEREL_GEN")
	@Column(name = "commodity_cate_rel_id", unique = true, nullable = false)
	public Long getCommodity_cate_rel_id() {
		return this.commodity_cate_rel_id;
	}

	public void setCommodity_cate_rel_id(Long commodity_cate_rel_id) {
		this.commodity_cate_rel_id = commodity_cate_rel_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commodity_id")
	public Commodity getCommodity() {
		return this.commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
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
		return this.commodity_cate_rel_id;
	}
}