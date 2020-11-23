package dao.pojo;

import javax.persistence.TableGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
// default package
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * PayRcd entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_pay_rcd", catalog = "tiandian")
public class PayRcd extends BasePojo implements java.io.Serializable {
	// Fields
	private Long pay_rcd_id;
	private PayStyle payStyle;
	private String pay_flow_no;
	private Double pay_money;
	private Double receipt_money;
	private String pay_account;
	private Long pay_time;
	private Long pay_confirm_time;
	private Orders orders;
	private Integer is_exception = 0;

	// Constructors
	/** default constructor */
	public PayRcd() {
	}

	/** minimal constructor */
	public PayRcd(Long pay_rcd_id, Long pay_time, Long pay_confirm_time) {
		this.pay_rcd_id = pay_rcd_id;
		this.pay_time = pay_time;
		this.pay_confirm_time = pay_confirm_time;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "PAYRCD_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_PAYRCD", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PAYRCD_GEN")
	@Column(name = "pay_rcd_id", unique = true, nullable = false)
	public Long getPay_rcd_id() {
		return this.pay_rcd_id;
	}

	public void setPay_rcd_id(Long pay_rcd_id) {
		this.pay_rcd_id = pay_rcd_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pay_style_id")
	public PayStyle getPayStyle() {
		return this.payStyle;
	}

	public void setPayStyle(PayStyle payStyle) {
		this.payStyle = payStyle;
	}

	@Column(name = "pay_flow_no", length = 50)
	public String getPay_flow_no() {
		return this.pay_flow_no;
	}

	public void setPay_flow_no(String pay_flow_no) {
		this.pay_flow_no = pay_flow_no;
	}

	@Column(name = "pay_money", precision = 13)
	public Double getPay_money() {
		return this.pay_money;
	}

	public void setPay_money(Double pay_money) {
		this.pay_money = pay_money;
	}

	@Column(name = "pay_account", length = 50)
	public String getPay_account() {
		return this.pay_account;
	}

	public void setPay_account(String pay_account) {
		this.pay_account = pay_account;
	}

	@Column(name = "pay_time", nullable = false, length = 19)
	public Long getPay_time() {
		return this.pay_time;
	}

	public void setPay_time(Long pay_time) {
		this.pay_time = pay_time;
	}

	@Column(name = "pay_confirm_time", nullable = false, length = 19)
	public Long getPay_confirm_time() {
		return this.pay_confirm_time;
	}

	public void setPay_confirm_time(Long pay_confirm_time) {
		this.pay_confirm_time = pay_confirm_time;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	@Column(name = "receipt_money")
	public Double getReceipt_money() {
		return receipt_money;
	}

	public void setReceipt_money(Double receipt_money) {
		this.receipt_money = receipt_money;
	}

	@Column(name = "is_exception")
	public Integer getIs_exception() {
		return is_exception;
	}

	public void setIs_exception(Integer is_exception) {
		this.is_exception = is_exception;
	}

	@Override
	public Object getEntityId() {
		return this.pay_rcd_id;
	}
}