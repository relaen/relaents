package dao.pojo;

// default package
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * CompanyCheck entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_company_check", catalog = "tiandian")
public class CompanyCheck extends BasePojo implements java.io.Serializable {
	// Fields
	private Long company_check_id;
	private Company company;
	private Long check_time;
	private String reject_reason;
	private Integer is_agree;
	private Member member;
	private Member checker;
	private Long ask_time;
	private String company_data;
	private String remarks;

	@Override
	public Object getEntityId() {
		return this.company_check_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "COMPANYCHECK_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COMPANYCHECK", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMPANYCHECK_GEN")
	@Column(name = "company_check_id", unique = true, nullable = false)
	public Long getCompany_check_id() {
		return this.company_check_id;
	}

	public void setCompany_check_id(Long company_check_id) {
		this.company_check_id = company_check_id;
	}

	@OneToOne(fetch = FetchType.LAZY)
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
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "checker_id")
	public Member getChecker() {
		return checker;
	}

	public void setChecker(Member checker) {
		this.checker = checker;
	}

	@Column(name = "check_time", nullable = false, length = 19)
	public Long getCheck_time() {
		return this.check_time;
	}

	public void setCheck_time(Long check_time) {
		this.check_time = check_time;
	}

	@Column(name = "reject_reason", length = 500)
	public String getReject_reason() {
		return this.reject_reason;
	}

	public void setReject_reason(String reject_reason) {
		this.reject_reason = reject_reason;
	}

	@Column(name = "is_agree")
	public Integer getIs_agree() {
		return this.is_agree;
	}

	public void setIs_agree(Integer is_agree) {
		this.is_agree = is_agree;
	}

	@Column(name = "ask_time")
	public Long getAsk_time() {
		return ask_time;
	}

	public void setAsk_time(Long ask_time) {
		this.ask_time = ask_time;
	}

	@Column(name = "company_data")
	public String getCompany_data() {
		return company_data;
	}

	public void setCompany_data(String company_data) {
		this.company_data = company_data;
	}

	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}