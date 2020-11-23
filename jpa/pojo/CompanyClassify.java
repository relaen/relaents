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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * TCompanyClassify entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_company_classify", catalog = "tiandian")

public class CompanyClassify extends BasePojo implements java.io.Serializable {

	// Fields

	private Long company_classify_id;
	private CompanyClassify companyClassify;
	private String cate_name;
	private String cate_img;
	private Integer sort_order;
	private Integer company_num;
	private Set<CompanyClassify> companyClassifies = new HashSet<CompanyClassify>(0);
	private Set<Company> companies = new HashSet<Company>(0);

	@Override
	public Object getEntityId() {
		// TODO Auto-generated method stub
		return company_classify_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "COMPANYCLASSIFY_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COMPANYCLASSIFY", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMPANYCLASSIFY_GEN")
	@Column(name = "company_classify_id", unique = true, nullable = false)
	public Long getCompany_classify_id() {
		return company_classify_id;
	}

	public void setCompany_classify_id(Long company_classify_id) {
		this.company_classify_id = company_classify_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	public CompanyClassify getCompanyClassify() {
		return this.companyClassify;
	}

	public void setCompanyClassify(CompanyClassify companyClassify) {
		this.companyClassify = companyClassify;
	}

	@Column(name = "cate_name", length = 50)
	public String getCate_name() {
		return cate_name;
	}

	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}

	@Column(name = "cate_img", length = 500)
	public String getCate_img() {
		return cate_img;
	}

	public void setCate_img(String cate_img) {
		this.cate_img = cate_img;
	}

	@Column(name = "sort_order")
	public Integer getSort_order() {
		return sort_order;
	}

	public void setSort_order(Integer sort_order) {
		this.sort_order = sort_order;
	}

	@Column(name = "company_num")
	public Integer getCompany_num() {
		return company_num;
	}

	public void setCompany_num(Integer company_num) {
		this.company_num = company_num;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "companyClassify")
	public Set<CompanyClassify> getCompanyClassifies() {
		return companyClassifies;
	}

	public void setCompanyClassifies(Set<CompanyClassify> companyClassifies) {
		this.companyClassifies = companyClassifies;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "companyClassify")
	public Set<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(Set<Company> companies) {
		this.companies = companies;
	}

}