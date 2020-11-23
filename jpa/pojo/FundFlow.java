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
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * FundFlow entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_fund_flow", catalog = "tiandian")

public class FundFlow extends BasePojo implements java.io.Serializable {

	// Fields

	private Long fund_flow_id;
	private Long create_time;
	private Double money;
	private FundFlowType fundFlowType;

	// Constructors

	/** default constructor */
	public FundFlow() {
	}

	/** minimal constructor */
	public FundFlow(Long fund_flow_id) {
		this.fund_flow_id = fund_flow_id;
	}

	/** full constructor */
	public FundFlow(Long fund_flow_id, Long create_time, Double money, Short flow_type) {
		this.fund_flow_id = fund_flow_id;
		this.create_time = create_time;
		this.money = money;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "FUNDFLOW_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_FUNDFLOW", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FUNDFLOW_GEN")
	@Column(name = "fund_flow_id", unique = true, nullable = false)

	public Long getFund_flow_id() {
		return this.fund_flow_id;
	}

	public void setFund_flow_id(Long fund_flow_id) {
		this.fund_flow_id = fund_flow_id;
	}

	@Column(name = "create_time")

	public Long getCreate_time() {
		return this.create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	@Column(name = "money", precision = 13)

	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fund_flow_type_id")

	public FundFlowType getFundFlowType() {
		return this.fundFlowType;
	}

	public void setFundFlowType(FundFlowType fundFlowType) {
		this.fundFlowType = fundFlowType;
	}

	@Override
	public Object getEntityId() {
		return this.fund_flow_id;
	}
}