package dao.pojo;
// default package

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * SupplierBalance entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_balance_order", catalog = "tiandian")

public class BalanceOrder extends BasePojo implements java.io.Serializable {

	// Fields

	private Long balance_order_id;
	private SupplierBalance supplierBalance;
	private Orders orders;

	// Property accessors
	@Id
	@TableGenerator(name = "BALANCEORDER_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_BALANCEORDER", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "BALANCEORDER_GEN")
	@Column(name = "balance_order_id", unique = true, nullable = false)
	public Long getBalance_order_id() {
		return balance_order_id;
	}

	public void setBalance_order_id(Long balance_order_id) {
		this.balance_order_id = balance_order_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplier_balance_id")
	public SupplierBalance getSupplierBalance() {
		return supplierBalance;
	}

	public void setSupplierBalance(SupplierBalance supplierBalance) {
		this.supplierBalance = supplierBalance;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	public Orders getOrders() {
		return this.orders;
	}

	public void setOrders(Orders order) {
		this.orders = order;
	}

	@Override
	public Object getEntityId() {
		return this.balance_order_id;
	}

}