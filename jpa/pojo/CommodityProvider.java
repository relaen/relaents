package dao.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * TCommodityProvider entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_commodity_provider", catalog = "tiandian")
public class CommodityProvider extends BasePojo implements java.io.Serializable {

	// Fields

	private Long commodity_provider_id;
	private String commodity_provider_name;
	private String simple_name;
	private String remarks;
	private Set<CommodityOther> commodityOthers = new HashSet<CommodityOther>(0);

	@Override
	public Object getEntityId() {
		// TODO Auto-generated method stub
		return commodity_provider_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "COMMODITYPROVIDER_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COMMODITYPROVIDER", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMMODITYPROVIDER_GEN")
	@Column(name = "commodity_provider_id", unique = true, nullable = false)
	public Long getCommodity_provider_id() {
		return commodity_provider_id;
	}

	public void setCommodity_provider_id(Long commodity_provider_id) {
		this.commodity_provider_id = commodity_provider_id;
	}

	@Column(name = "commodity_provider_name", length = 50)
	public String getCommodity_provider_name() {
		return commodity_provider_name;
	}

	public void setCommodity_provider_name(String commodity_provider_name) {
		this.commodity_provider_name = commodity_provider_name;
	}

	@Column(name = "simple_name", length = 50)
	public String getSimple_name() {
		return simple_name;
	}

	public void setSimple_name(String simple_name) {
		this.simple_name = simple_name;
	}

	@Column(name = "remarks", length = 200)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commodityProvider")
	public Set<CommodityOther> getCommodityOthers() {
		return commodityOthers;
	}

	public void setCommodityOthers(Set<CommodityOther> commodityOthers) {
		this.commodityOthers = commodityOthers;
	}
}