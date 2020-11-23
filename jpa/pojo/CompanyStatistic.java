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
 * companyStatistic entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_company_statistic", catalog = "tiandian")
public class CompanyStatistic extends BasePojo implements java.io.Serializable {
	// Fields
	private Long company_statistic_id;
	private Company company;
	private Integer pay_order_num;
	private Integer send_order_num;
	private Integer sale_ranking;
	private Integer new_fans_num;
	private Integer new_client_num;
	private Integer look_person_num;
	private Integer look_commodity_num;
	private Long statistic_time;
	private Double sale_money;
	private Set<BuyStatistic> buyStatistics = new HashSet<BuyStatistic>(0);

	// Constructors
	/** default constructor */
	public CompanyStatistic() {
	}

	/** minimal constructor */
	public CompanyStatistic(Long company_statistic_id, Long statistic_time) {
		this.company_statistic_id = company_statistic_id;
		this.statistic_time = statistic_time;
	}

	/** full constructor */
	public CompanyStatistic(Long company_statistic_id, Company company, Integer pay_order_num,
			Integer send_order_num, Integer sale_ranking, Integer new_fans_num, Integer new_client_num,
			Integer look_person_num, Integer look_commodity_num, Long statistic_time, Double sale_money,
			Set<BuyStatistic> buyStatistics) {
		this.company_statistic_id = company_statistic_id;
		this.company = company;
		this.pay_order_num = pay_order_num;
		this.send_order_num = send_order_num;
		this.sale_ranking = sale_ranking;
		this.new_fans_num = new_fans_num;
		this.new_client_num = new_client_num;
		this.look_person_num = look_person_num;
		this.look_commodity_num = look_commodity_num;
		this.statistic_time = statistic_time;
		this.sale_money = sale_money;
		this.buyStatistics = buyStatistics;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "COMPANYSTATISTIC_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COMPANYSTATISTIC", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMPANYSTATISTIC_GEN")
	@Column(name = "company_statistic_id", unique = true, nullable = false)
	public Long getCompany_statistic_id() {
		return this.company_statistic_id;
	}

	public void setCompany_statistic_id(Long company_statistic_id) {
		this.company_statistic_id = company_statistic_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	public Company getcompany() {
		return this.company;
	}

	public void setcompany(Company company) {
		this.company = company;
	}

	@Column(name = "pay_order_num")
	public Integer getPay_order_num() {
		return this.pay_order_num;
	}

	public void setPay_order_num(Integer pay_order_num) {
		this.pay_order_num = pay_order_num;
	}

	@Column(name = "send_order_num")
	public Integer getSend_order_num() {
		return this.send_order_num;
	}

	public void setSend_order_num(Integer send_order_num) {
		this.send_order_num = send_order_num;
	}

	@Column(name = "sale_ranking")
	public Integer getSale_ranking() {
		return this.sale_ranking;
	}

	public void setSale_ranking(Integer sale_ranking) {
		this.sale_ranking = sale_ranking;
	}

	@Column(name = "new_fans_num")
	public Integer getNew_fans_num() {
		return this.new_fans_num;
	}

	public void setNew_fans_num(Integer new_fans_num) {
		this.new_fans_num = new_fans_num;
	}

	@Column(name = "new_client_num")
	public Integer getNew_client_num() {
		return this.new_client_num;
	}

	public void setNew_client_num(Integer new_client_num) {
		this.new_client_num = new_client_num;
	}

	@Column(name = "look_person_num")
	public Integer getLook_person_num() {
		return this.look_person_num;
	}

	public void setLook_person_num(Integer look_person_num) {
		this.look_person_num = look_person_num;
	}

	@Column(name = "look_commodity_num")
	public Integer getLook_commodity_num() {
		return this.look_commodity_num;
	}

	public void setLook_commodity_num(Integer look_commodity_num) {
		this.look_commodity_num = look_commodity_num;
	}

	@Column(name = "statistic_time", nullable = false, length = 19)
	public Long getStatistic_time() {
		return this.statistic_time;
	}

	public void setStatistic_time(Long statistic_time) {
		this.statistic_time = statistic_time;
	}

	@Column(name = "sale_money", precision = 13)
	public Double getSale_money() {
		return this.sale_money;
	}

	public void setSale_money(Double sale_money) {
		this.sale_money = sale_money;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "companyStatistic")
	public Set<BuyStatistic> getBuyStatistics() {
		return this.buyStatistics;
	}

	public void setBuyStatistics(Set<BuyStatistic> buyStatistics) {
		this.buyStatistics = buyStatistics;
	}

	@Override
	public Object getEntityId() {
		return this.company_statistic_id;
	}
}