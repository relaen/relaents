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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * CommoditySpec entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_commodity_spec", catalog = "tiandian")
public class CommoditySpec extends BasePojo implements java.io.Serializable {
	// Fields
	private Long commodity_spec_id;
	private Commodity commodity;
	private Spec spec;

	// Constructors
	/** default constructor */
	public CommoditySpec() {
	}

	/** minimal constructor */
	public CommoditySpec(Long commodity_spec_id) {
		this.commodity_spec_id = commodity_spec_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "COMMODITYSPEC_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COMMODITYSPEC", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMMODITYSPEC_GEN")
	@Column(name = "commodity_spec_id", unique = true, nullable = false)
	public Long getCommodity_spec_id() {
		return this.commodity_spec_id;
	}

	public void setCommodity_spec_id(Long commodity_spec_id) {
		this.commodity_spec_id = commodity_spec_id;
	}

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commodity_id")
	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "spec_id")
	public Spec getSpec() {
		return spec;
	}

	public void setSpec(Spec spec) {
		this.spec = spec;
	}


	@Override
	public Object getEntityId() {
		return this.commodity_spec_id;
	}
}