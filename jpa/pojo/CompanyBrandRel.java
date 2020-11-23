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
 * CompanyBrandRel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_company_brand_rel", catalog = "tiandian")
public class CompanyBrandRel extends BasePojo implements java.io.Serializable {
	// Fields
	private Long company_brand_rel_id;
	private Company company;
	private Brand brand;

	// Constructors
	/** default constructor */
	public CompanyBrandRel() {
	}

	/** minimal constructor */
	public CompanyBrandRel(Long company_brand_rel_id) {
		this.company_brand_rel_id = company_brand_rel_id;
	}

	/** full constructor */
	public CompanyBrandRel(Long company_brand_rel_id, Company company, Brand brand) {
		this.company_brand_rel_id = company_brand_rel_id;
		this.company = company;
		this.brand = brand;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "COMPANYBRANDREL_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COMPANYBRANDREL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMPANYBRANDREL_GEN")
	@Column(name = "company_brand_rel_id", unique = true, nullable = false)
	public Long getCompany_brand_rel_id() {
		return this.company_brand_rel_id;
	}

	public void setCompany_brand_rel_id(Long company_brand_rel_id) {
		this.company_brand_rel_id = company_brand_rel_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id")
	public Brand getBrand() {
		return this.brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	@Override
	public Object getEntityId() {
		return this.company_brand_rel_id;
	}
}