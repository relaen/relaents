package dao.pojo;

// default package
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * CompanyWallet entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_company_wallet", catalog = "tiandian")
public class CompanyWallet extends BasePojo implements java.io.Serializable {
	// Fields
	private Long company_id;
	private Company company;
	private Double cash = 0d;
	private Double dian_yuan = 0d;
	private Integer dian_bi = 0;
	private Double dian_yuan_limit = 0d;

	// Property accessors
	@Id
	@Column(name = "company_id", unique = true, nullable = false)
	public Long getCompany_id() {
		return this.company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Column(name = "cash", precision = 13)
	public Double getCash() {
		return this.cash;
	}

	public void setCash(Double cash) {
		this.cash = cash;
	}

	@Column(name = "dian_yuan", precision = 13)
	public Double getDian_yuan() {
		return this.dian_yuan;
	}

	public void setDian_yuan(Double dian_yuan) {
		this.dian_yuan = dian_yuan;
	}

	@Column(name = "dian_bi")
	public Integer getDian_bi() {
		return dian_bi;
	}

	public void setDian_bi(Integer dian_bi) {
		this.dian_bi = dian_bi;
	}

	@Column(name = "dian_yuan_limit", precision = 13)
	public Double getDian_yuan_limit() {
		return dian_yuan_limit;
	}

	public void setDian_yuan_limit(Double dian_yuan_limit) {
		this.dian_yuan_limit = dian_yuan_limit;
	}

	@Override
	public Object getEntityId() {
		return this.company_id;
	}
}