package dao.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * TCommodityOther entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_commodity_other", catalog = "tiandian")
public class CommodityOther extends BasePojo implements java.io.Serializable {

	// Fields

	private Long commodity_id;
	private CommodityProvider commodityProvider;
	private Commodity commodity;
	private Integer is_updated;
	private String commodity_no;

	@Override
	public Object getEntityId() {
		// TODO Auto-generated method stub
		return commodity_id;
	}

	// Property accessors
	@Id
	@Column(name = "commodity_id", unique = true, nullable = false)
	public Long getCommodity_id() {
		return commodity_id;
	}
	
	public void setCommodity_id(Long commodity_id) {
		this.commodity_id = commodity_id;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commodity_provider_id")
	public CommodityProvider getCommodityProvider() {
		return commodityProvider;
	}

	public void setCommodityProvider(CommodityProvider commodityProvider) {
		this.commodityProvider = commodityProvider;
	}

	@Column(name = "is_updated")
	public Integer getIs_updated() {
		return is_updated;
	}

	public void setIs_updated(Integer is_updated) {
		this.is_updated = is_updated;
	}

	@Column(name = "commodity_no")
	public String getCommodity_no() {
		return commodity_no;
	}

	public void setCommodity_no(String commodity_no) {
		this.commodity_no = commodity_no;
	}

}