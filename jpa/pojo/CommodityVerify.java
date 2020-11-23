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
 * CommodityVerify entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_commodity_verify", catalog = "tiandian")
public class CommodityVerify extends BasePojo implements java.io.Serializable {
	// Fields
	private Long commodity_verify_id;
	private String img_url;
	private String title;
	private String remark;
	private Set<CommodityVerifyRel> commodityVerifyRels = new HashSet<CommodityVerifyRel>(0);

	// Constructors
	/** default constructor */
	public CommodityVerify() {
	}

	/** minimal constructor */
	public CommodityVerify(Long commodity_verify_id) {
		this.commodity_verify_id = commodity_verify_id;
	}

	/** full constructor */
	public CommodityVerify(Long commodity_verify_id, String img_url, String title, String remark,
			Set<CommodityVerifyRel> commodityVerifyRels) {
		this.commodity_verify_id = commodity_verify_id;
		this.img_url = img_url;
		this.title = title;
		this.remark = remark;
		this.commodityVerifyRels = commodityVerifyRels;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "COMMODITYVERIFY_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COMMODITYVERIFY", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMMODITYVERIFY_GEN")
	@Column(name = "commodity_verify_id", unique = true, nullable = false)
	public Long getCommodity_verify_id() {
		return this.commodity_verify_id;
	}

	public void setCommodity_verify_id(Long commodity_verify_id) {
		this.commodity_verify_id = commodity_verify_id;
	}

	@Column(name = "img_url", length = 500)
	public String getImg_url() {
		return this.img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	@Column(name = "title", length = 50)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "remark", length = 500)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commodityVerify")
	public Set<CommodityVerifyRel> getCommodityVerifyRels() {
		return this.commodityVerifyRels;
	}

	public void setCommodityVerifyRels(Set<CommodityVerifyRel> commodityVerifyRels) {
		this.commodityVerifyRels = commodityVerifyRels;
	}

	@Override
	public Object getEntityId() {
		return this.commodity_verify_id;
	}
}