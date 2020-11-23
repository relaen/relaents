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
 * BuyStatistic entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_buy_statistic", catalog = "tiandian")
public class BuyStatistic extends BasePojo implements java.io.Serializable {
	// Fields
	private Long buy_statistic_id;
	private CompanyStatistic companyStatistic;
	private Double buy_money;

	// Constructors
	/** default constructor */
	public BuyStatistic() {
	}

	/** minimal constructor */
	public BuyStatistic(Long buy_statistic_id) {
		this.buy_statistic_id = buy_statistic_id;
	}


	// Property accessors
	@Id
	@TableGenerator(name = "BUYSTATISTIC_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_BUYSTATISTIC", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "BUYSTATISTIC_GEN")
	@Column(name = "buy_statistic_id", unique = true, nullable = false)
	public Long getBuy_statistic_id() {
		return this.buy_statistic_id;
	}

	public void setBuy_statistic_id(Long buy_statistic_id) {
		this.buy_statistic_id = buy_statistic_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_statistic_id")
	public CompanyStatistic getCompanyStatistic() {
		return this.companyStatistic;
	}

	public void setCompanyStatistic(CompanyStatistic companyStatistic) {
		this.companyStatistic = companyStatistic;
	}

	@Column(name = "buy_money", precision = 13)
	public Double getBuy_money() {
		return this.buy_money;
	}

	public void setBuy_money(Double buy_money) {
		this.buy_money = buy_money;
	}

	@Override
	public Object getEntityId() {
		return this.buy_statistic_id;
	}
}