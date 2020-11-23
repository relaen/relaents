package dao.pojo;

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
 * TOrderExtendReceiveAsk entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_order_extend_receive_ask", catalog = "tiandian")

public class OrderExtendReceiveAsk extends BasePojo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long order_extend_receive_ask_id;
	private Orders orders;
	private Member checker;
	private Long check_time;
	private String reject_reason;
	private Integer is_agree;
	private Long ask_time;
	private String ask_reason;

	@Override
	public Object getEntityId() {
		return order_extend_receive_ask_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "ORDEREXTENDRECEIVEASK_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_ORDEREXTENDRECEIVEASK", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ORDEREXTENDRECEIVEASK_GEN")
	@Column(name = "order_extend_receive_ask_id", unique = true, nullable = false)
	public Long getOrder_extend_receive_ask_id() {
		return order_extend_receive_ask_id;
	}

	public void setOrder_extend_receive_ask_id(Long order_extend_receive_ask_id) {
		this.order_extend_receive_ask_id = order_extend_receive_ask_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "checker_id")
	public Member getChecker() {
		return checker;
	}

	public void setChecker(Member checker) {
		this.checker = checker;
	}

	@Column(name = "check_time")
	public Long getCheck_time() {
		return check_time;
	}

	public void setCheck_time(Long check_time) {
		this.check_time = check_time;
	}

	@Column(name = "reject_reason", length = 500)
	public String getReject_reason() {
		return reject_reason;
	}

	public void setReject_reason(String reject_reason) {
		this.reject_reason = reject_reason;
	}

	@Column(name = "is_agree")
	public Integer getIs_agree() {
		return is_agree;
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

	@Column(name = "ask_reason", length = 50)
	public String getAsk_reason() {
		return ask_reason;
	}

	public void setAsk_reason(String ask_reason) {
		this.ask_reason = ask_reason;
	}

}