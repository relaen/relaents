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
 * WalletFlow entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_company_wallet_flow", catalog = "tiandian")

public class CompanyWalletFlow extends BasePojo implements java.io.Serializable {

	// Fields
	private Long company_wallet_flow_id;
	private CompanyWallet companyWallet;
	private FlowType flowType;
	private String flow_no;
	private Long create_time;
	private Double num;

	// Property accessors
	@Id
	@TableGenerator(name = "COMPANYWALLETFLOW_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COMPANYWALLETFLOW", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMPANYWALLETFLOW_GEN")
	@Column(name = "company_wallet_flow_id", unique = true, nullable = false)
	public Long getCompany_wallet_flow_id() {
		return company_wallet_flow_id;
	}

	public void setCompany_wallet_flow_id(Long company_wallet_flow_id) {
		this.company_wallet_flow_id = company_wallet_flow_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	public CompanyWallet getCompanyWallet() {
		return companyWallet;
	}

	public void setCompanyWallet(CompanyWallet companyWallet) {
		this.companyWallet = companyWallet;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "flow_type_id")
	public FlowType getFlowType() {
		return this.flowType;
	}

	public void setFlowType(FlowType flowType) {
		this.flowType = flowType;
	}

	@Column(name = "flow_no", length = 50)
	public String getFlow_no() {
		return this.flow_no;
	}

	public void setFlow_no(String flow_no) {
		this.flow_no = flow_no;
	}

	@Column(name = "num", precision = 13)
	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}

	@Column(name = "create_time")
	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	@Override
	public Object getEntityId() {
		return this.company_wallet_flow_id;
	}

}