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
 * TPayStyleClosePlan entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_pay_style_close_plan", catalog = "tiandian")

public class PayStyleClosePlan extends BasePojo implements java.io.Serializable {

	// Fields

	private Long pay_style_close_plan_id;
	private PayStyle payStyle;
	private Long start_time;
	private Long end_time;

	@Override
	public Object getEntityId() {
		// TODO Auto-generated method stub
		return pay_style_close_plan_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "PAYSTYLECLOSEPLAN_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_PAYSTYLECLOSEPLAN", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PAYSTYLECLOSEPLAN_GEN")
	@Column(name = "pay_style_close_plan_id", unique = true, nullable = false)
	public Long getPay_style_close_plan_id() {
		return pay_style_close_plan_id;
	}

	public void setPay_style_close_plan_id(Long pay_style_close_plan_id) {
		this.pay_style_close_plan_id = pay_style_close_plan_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pay_style_id")
	public PayStyle getPayStyle() {
		return payStyle;
	}

	public void setPayStyle(PayStyle payStyle) {
		this.payStyle = payStyle;
	}

	@Column(name = "start_time")
	public Long getStart_time() {
		return start_time;
	}

	public void setStart_time(Long start_time) {
		this.start_time = start_time;
	}

	@Column(name = "end_time")
	public Long getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Long end_time) {
		this.end_time = end_time;
	}

}