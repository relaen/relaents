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
 * CommodityCate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_commodity_cate", catalog = "tiandian")
public class CommodityCate extends BasePojo implements java.io.Serializable {
	// Fields
	private Long commodity_cate_id;
	private CommodityCate commodityCate;
	private String cate_name;
	private String cate_img;
	private String remark;
	private Integer is_recommend;
	private Integer orderbyfirst;		//分类排序
	private Integer commodity_num = 0;
	private Set<CommodityCate> commodityCates = new HashSet<CommodityCate>(0);
	private Set<CommodityCateRel> commodityCateRels = new HashSet<CommodityCateRel>(0);
	private Set<CateSpecRel> cateSpecRels = new HashSet<CateSpecRel>(0);
	private Set<PropCateRel> propCateRels = new HashSet<PropCateRel>(0);

	// Constructors
	/** default constructor */
	public CommodityCate() {
	}

	/** minimal constructor */
	public CommodityCate(Long commodity_cate_id) {
		this.commodity_cate_id = commodity_cate_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "COMMODITYCATE_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COMMODITYCATE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMMODITYCATE_GEN")
	@Column(name = "commodity_cate_id", unique = true, nullable = false)
	public Long getCommodity_cate_id() {
		return this.commodity_cate_id;
	}

	public void setCommodity_cate_id(Long commodity_cate_id) {
		this.commodity_cate_id = commodity_cate_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	public CommodityCate getCommodityCate() {
		return this.commodityCate;
	}

	public void setCommodityCate(CommodityCate commodityCate) {
		this.commodityCate = commodityCate;
	}

	@Column(name = "cate_name", length = 50)
	public String getCate_name() {
		return this.cate_name;
	}

	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}

	@Column(name = "cate_img", length = 500)
	public String getCate_img() {
		return this.cate_img;
	}

	public void setCate_img(String cate_img) {
		this.cate_img = cate_img;
	}

	@Column(name = "remark", length = 500)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "is_recommend")
	public Integer getIs_recommend() {
		return this.is_recommend;
	}

	public void setIs_recommend(Integer is_recommend) {
		this.is_recommend = is_recommend;
	}

	@Column(name = "commodity_num")
	public Integer getCommodity_num() {
		return commodity_num;
	}

	public void setCommodity_num(Integer commodity_num) {
		this.commodity_num = commodity_num;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commodityCate")
	public Set<CommodityCate> getCommodityCates() {
		return this.commodityCates;
	}

	public void setCommodityCates(Set<CommodityCate> commodityCates) {
		this.commodityCates = commodityCates;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commodityCate")
	public Set<CommodityCateRel> getCommodityCateRels() {
		return this.commodityCateRels;
	}

	public void setCommodityCateRels(Set<CommodityCateRel> commodityCateRels) {
		this.commodityCateRels = commodityCateRels;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commodityCate")
	public Set<CateSpecRel> getCateSpecRels() {
		return this.cateSpecRels;
	}

	public void setCateSpecRels(Set<CateSpecRel> cateSpecRels) {
		this.cateSpecRels = cateSpecRels;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commodityCate")
	public Set<PropCateRel> getPropCateRels() {
		return propCateRels;
	}

	public void setPropCateRels(Set<PropCateRel> propCateRels) {
		this.propCateRels = propCateRels;
	}

	@Override
	public Object getEntityId() {
		return this.commodity_cate_id;
	}

	@Column(name = "orderbyfirst")
	public Integer getOrderbyfirst() {
		return orderbyfirst;
	}

	public void setOrderbyfirst(Integer orderbyfirst) {
		this.orderbyfirst = orderbyfirst;
	}
}