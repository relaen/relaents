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
 * ReturnMoney entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_return_money", catalog = "tiandian")

public class ReturnMoney extends BasePojo implements java.io.Serializable {

	// Fields

	private Long return_money_id;
	private ReturnInfo returnInfo;
	private Orders orders;
	private Member member;
	private Member checker;
	private Long ask_time;
	private String reason;
	private Long check_time;
	private Short is_agree;
	private String reject_reason;
	private Double return_money;
	private Double return_yuan;
	private Integer return_fen;
	private Integer return_give_fen;
	private Integer return_bi;
	private Long finish_time;
	private Integer return_state;
	private String return_state_msg;

	// Property accessors
	@Id
	@TableGenerator(name = "RETURNMONEY_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_RETURNMONEY", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "RETURNMONEY_GEN")
	@Column(name = "return_money_id", unique = true, nullable = false)
	public Long getReturn_money_id() {
		return return_money_id;
	}

	public void setReturn_money_id(Long return_money_id) {
		this.return_money_id = return_money_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "return_info_id")
	public ReturnInfo getReturnInfo() {
		return returnInfo;
	}

	public void setReturnInfo(ReturnInfo returnInfo) {
		this.returnInfo = returnInfo;
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
	@JoinColumn(name = "member_id")
	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "checker_id")
	public Member getChecker() {
		return checker;
	}

	public void setChecker(Member checker) {
		this.checker = checker;
	}

	@Column(name = "ask_time")
	public Long getAsk_time() {
		return this.ask_time;
	}

	public void setAsk_time(Long ask_time) {
		this.ask_time = ask_time;
	}

	@Column(name = "reason", length = 200)
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "check_time")
	public Long getCheck_time() {
		return this.check_time;
	}

	public void setCheck_time(Long check_time) {
		this.check_time = check_time;
	}

	@Column(name = "is_agree")
	public Short getIs_agree() {
		return this.is_agree;
	}

	public void setIs_agree(Short is_agree) {
		this.is_agree = is_agree;
	}

	@Column(name = "reject_reason", length = 200)
	public String getReject_reason() {
		return this.reject_reason;
	}

	public void setReject_reason(String reject_reason) {
		this.reject_reason = reject_reason;
	}

	@Column(name = "return_money", precision = 13)
	public Double getReturn_money() {
		return return_money;
	}

	public void setReturn_money(Double return_money) {
		this.return_money = return_money;
	}

	@Column(name = "finish_time")
	public Long getFinish_time() {
		return finish_time;
	}

	public void setFinish_time(Long finish_time) {
		this.finish_time = finish_time;
	}

	@Column(name = "return_yuan")
	public Double getReturn_yuan() {
		return return_yuan;
	}

	public void setReturn_yuan(Double return_yuan) {
		this.return_yuan = return_yuan;
	}

	@Column(name = "return_fen")
	public Integer getReturn_fen() {
		return return_fen;
	}

	public void setReturn_fen(Integer return_fen) {
		this.return_fen = return_fen;
	}

	@Column(name = "return_give_fen")
	public Integer getReturn_give_fen() {
		return return_give_fen;
	}

	public void setReturn_give_fen(Integer return_give_fen) {
		this.return_give_fen = return_give_fen;
	}

	@Column(name = "return_bi")
	public Integer getReturn_bi() {
		return return_bi;
	}

	public void setReturn_bi(Integer return_bi) {
		this.return_bi = return_bi;
	}

	@Column(name = "return_state")
	public Integer getReturn_state() {
		return return_state;
	}

	public void setReturn_state(Integer return_state) {
		this.return_state = return_state;
	}

	@Column(name = "return_state_msg")
	public String getReturn_state_msg() {
		return return_state_msg;
	}

	public void setReturn_state_msg(String return_state_msg) {
		this.return_state_msg = return_state_msg;
	}

	@Override
	public Object getEntityId() {
		return this.return_money_id;
	}

}