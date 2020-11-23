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
@Table(name = "t_wallet_flow", catalog = "tiandian")

public class WalletFlow extends BasePojo implements java.io.Serializable {

	// Fields
	private Long wallet_flow_id;
	private Wallet wallet;
	private FlowType flowType;
	private String flow_no;
	private Long create_time;
	private Double num;
	private String remarks;
	private Orders orders;

	// Constructors

	/** default constructor */
	public WalletFlow() {
	}

	/** minimal constructor */
	public WalletFlow(Long wallet_flow_id) {
		this.wallet_flow_id = wallet_flow_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "WALLETFLOW_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_WALLETFLOW", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "WALLETFLOW_GEN")
	@Column(name = "wallet_flow_id", unique = true, nullable = false)

	public Long getWallet_flow_id() {
		return this.wallet_flow_id;
	}

	public void setWallet_flow_id(Long wallet_flow_id) {
		this.wallet_flow_id = wallet_flow_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wallet_id")
	public Wallet getWallet() {
		return this.wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
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

	@Column(name = "remarks", length = 50)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public Object getEntityId() {
		return this.wallet_flow_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	public Orders getOrders() {
		return orders;
	}
	
	public void setOrders(Orders orders) {
		this.orders = orders;
	}

}