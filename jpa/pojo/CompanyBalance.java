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
 * CompanyBalance entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_company_balance", catalog = "tiandian")
public class CompanyBalance extends BasePojo implements java.io.Serializable {
	// Fields
	private Long company_balance_id;
	private Company company;
	private Long balance_time;
	private Long start_time;
	private Long end_time;
	private Double balance_money;

	// Constructors
	/** default constructor */
	public CompanyBalance() {
	}

	/** minimal constructor */
	public CompanyBalance(Long company_balance_id) {
		this.company_balance_id = company_balance_id;
	}

	/** full constructor */
	public CompanyBalance(Long company_balance_id, Company company, Long balance_time, Long start_time, Long end_time,
			Double balance_money) {
		this.company_balance_id = company_balance_id;
		this.company = company;
		this.balance_time = balance_time;
		this.start_time = start_time;
		this.end_time = end_time;
		this.balance_money = balance_money;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "COMPANYBALANCE_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COMPANYBALANCE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMPANYBALANCE_GEN")
	@Column(name = "company_balance_id", unique = true, nullable = false)
	public Long getCompany_balance_id() {
		return this.company_balance_id;
	}

	public void setCompany_balance_id(Long company_balance_id) {
		this.company_balance_id = company_balance_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Column(name = "balance_time")
	public Long getBalance_time() {
		return this.balance_time;
	}

	public void setBalance_time(Long balance_time) {
		this.balance_time = balance_time;
	}

	@Column(name = "start_time")
	public Long getStart_time() {
		return this.start_time;
	}

	public void setStart_time(Long start_time) {
		this.start_time = start_time;
	}

	@Column(name = "end_time")
	public Long getEnd_time() {
		return this.end_time;
	}

	public void setEnd_time(Long end_time) {
		this.end_time = end_time;
	}

	@Column(name = "balance_money", precision = 13)
	public Double getBalance_money() {
		return this.balance_money;
	}

	public void setBalance_money(Double balance_money) {
		this.balance_money = balance_money;
	}

	@Override
	public Object getEntityId() {
		return this.company_balance_id;
	}
}