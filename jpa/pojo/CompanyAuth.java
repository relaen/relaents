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
 * ProviderAuth entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_company_auth", catalog = "tiandian")
public class CompanyAuth extends BasePojo implements java.io.Serializable {
	// Fields
	private Long company_auth_id;
	private AuthTag authTag;
	private Company company;
	private Long auth_time;

	// Constructors
	/** default constructor */
	public CompanyAuth() {
	}

	/** minimal constructor */
	public CompanyAuth(Long company_auth_id, Long auth_time) {
		this.company_auth_id = company_auth_id;
		this.auth_time = auth_time;
	}

	/** full constructor */
	public CompanyAuth(Long company_auth_id, AuthTag authTag, Company company, Long auth_time) {
		this.company_auth_id = company_auth_id;
		this.authTag = authTag;
		this.company = company;
		this.auth_time = auth_time;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "COMPANYAUTH_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COMPANYAUTH", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMPANYAUTH_GEN")
	@Column(name = "company_auth_id", unique = true, nullable = false)
	public Long getCompany_auth_id() {
		return this.company_auth_id;
	}

	public void setCompany_auth_id(Long company_auth_id) {
		this.company_auth_id = company_auth_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auth_tag_id")
	public AuthTag getAuthTag() {
		return this.authTag;
	}

	public void setAuthTag(AuthTag authTag) {
		this.authTag = authTag;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Column(name = "auth_time", nullable = false, length = 19)
	public Long getAuth_time() {
		return this.auth_time;
	}

	public void setAuth_time(Long auth_time) {
		this.auth_time = auth_time;
	}

	@Override
	public Object getEntityId() {
		return this.company_auth_id;
	}
}