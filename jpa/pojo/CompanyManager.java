package dao.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * CompanyManager entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_company_manager", catalog = "tiandian")

public class CompanyManager extends BasePojo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// Fields
	private String company_user_id;
	private Company company;
	private Member member;

	// Constructors

	/** default constructor */
	public CompanyManager() {
	}

	/** minimal constructor */
	public CompanyManager(String companyUserId) {
		this.company_user_id = companyUserId;
	}

	/** full constructor */
	public CompanyManager(String companyUserId, Company company, Member member) {
		this.company_user_id = companyUserId;
		this.company = company;
		this.member = member;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "COMPANYMANAGER_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COMPANYMANAGER", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMPANYMANAGER_GEN")
	@Column(name = "company_user_id", unique = true, nullable = false, length = 10)

	public String getCompany_user_id() {
		return this.company_user_id;
	}

	public void setCompany_user_id(String company_user_id) {
		this.company_user_id = company_user_id;
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
	@JoinColumn(name = "member_id")
	public Member getMember() {
		return this.member;
	}

	public void setMember(Member TMember) {
		this.member = TMember;
	}
	@Override
	public Object getEntityId() {
		return this.company_user_id;
	}

}