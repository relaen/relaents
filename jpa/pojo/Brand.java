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
 * Brand entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_brand", catalog = "tiandian")
public class Brand extends BasePojo implements java.io.Serializable {
	// Fields
	private Long brand_id;
	private String brand_name;
	private String remarks;
	private String brand_img;
	private Set<CompanyBrandRel> companyBrandRels = new HashSet<CompanyBrandRel>(0);
	private Set<Commodity> commodities = new HashSet<Commodity>(0);

	// Constructors
	/** default constructor */
	public Brand() {
	}

	/** minimal constructor */
	public Brand(Long brand_id) {
		this.brand_id = brand_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "BRAND_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_BRAND", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "BRAND_GEN")
	@Column(name = "brand_id", unique = true, nullable = false)
	public Long getBrand_id() {
		return this.brand_id;
	}

	public void setBrand_id(Long brand_id) {
		this.brand_id = brand_id;
	}

	@Column(name = "brand_name", length = 50)
	public String getBrand_name() {
		return this.brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	@Column(name = "remarks", length = 200)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "brand_img", length = 500)
	public String getBrand_img() {
		return this.brand_img;
	}

	public void setBrand_img(String brand_img) {
		this.brand_img = brand_img;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "brand")
	public Set<CompanyBrandRel> getCompanyBrandRels() {
		return this.companyBrandRels;
	}

	public void setCompanyBrandRels(Set<CompanyBrandRel> companyBrandRels) {
		this.companyBrandRels = companyBrandRels;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "brand")
	public Set<Commodity> getCommodities() {
		return this.commodities;
	}

	public void setCommodities(Set<Commodity> commodities) {
		this.commodities = commodities;
	}

	@Override
	public Object getEntityId() {
		return this.brand_id;
	}
}