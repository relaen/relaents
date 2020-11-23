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
 * CommodityVerifyRel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_commodity_verify_rel", catalog = "tiandian")
public class CommodityVerifyRel extends BasePojo implements java.io.Serializable {
	// Fields
	private Long commodity_verify_rel_id;
	private Commodity commodity;
	private CommodityVerify commodityVerify;

	// Constructors
	/** default constructor */
	public CommodityVerifyRel() {
	}

	/** minimal constructor */
	public CommodityVerifyRel(Long commodity_verify_rel_id) {
		this.commodity_verify_rel_id = commodity_verify_rel_id;
	}

	/** full constructor */
	public CommodityVerifyRel(Long commodity_verify_rel_id, Commodity commodity, CommodityVerify commodityVerify) {
		this.commodity_verify_rel_id = commodity_verify_rel_id;
		this.commodity = commodity;
		this.commodityVerify = commodityVerify;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "COMMODITYVERIFYREL_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COMMODITYVERIFYREL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMMODITYVERIFYREL_GEN")
	@Column(name = "commodity_verify_rel_id", unique = true, nullable = false)
	public Long getCommodity_verify_rel_id() {
		return this.commodity_verify_rel_id;
	}

	public void setCommodity_verify_rel_id(Long commodity_verify_rel_id) {
		this.commodity_verify_rel_id = commodity_verify_rel_id;
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
	@JoinColumn(name = "commodity_verify_id")
	public CommodityVerify getCommodityVerify() {
		return this.commodityVerify;
	}

	public void setCommodityVerify(CommodityVerify commodityVerify) {
		this.commodityVerify = commodityVerify;
	}

	@Override
	public Object getEntityId() {
		return this.commodity_verify_rel_id;
	}
}