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
 * TBargainMember entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_bargain_member", catalog = "tiandian")

public class BargainMember extends BasePojo implements java.io.Serializable {

	// Fields

	private Long bargain_member_id;
	private Bargain bargain;
	private Member member;
	private Long start_time;
	private Integer cuted_num;
	private Double cuted_price;
	private Integer is_buyed = 0; // 默认0，1表示已购买
	private Long buy_time;
	private Integer is_give_up = 0; // 默认0，1表示已放弃

	@Override
	public Object getEntityId() {
		// TODO Auto-generated method stub
		return bargain_member_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "BARGAINMEMBER_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_BARGAINMEMBER", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "BARGAINMEMBER_GEN")
	@Column(name = "bargain_member_id", unique = true, nullable = false)
	public Long getBargain_member_id() {
		return bargain_member_id;
	}

	public void setBargain_member_id(Long bargain_member_id) {
		this.bargain_member_id = bargain_member_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bargain_id")
	public Bargain getBargain() {
		return bargain;
	}

	public void setBargain(Bargain bargain) {
		this.bargain = bargain;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Column(name = "start_time")
	public Long getStart_time() {
		return start_time;
	}

	public void setStart_time(Long start_time) {
		this.start_time = start_time;
	}

	@Column(name = "cuted_num")
	public Integer getCuted_num() {
		return cuted_num;
	}

	public void setCuted_num(Integer cuted_num) {
		this.cuted_num = cuted_num;
	}

	@Column(name = "cuted_price", precision = 13)
	public Double getCuted_price() {
		return cuted_price;
	}

	public void setCuted_price(Double cuted_price) {
		this.cuted_price = cuted_price;
	}

	@Column(name = "is_buyed")
	public Integer getIs_buyed() {
		return is_buyed;
	}

	public void setIs_buyed(Integer is_buyed) {
		this.is_buyed = is_buyed;
	}

	@Column(name = "buy_time")
	public Long getBuy_time() {
		return buy_time;
	}

	public void setBuy_time(Long buy_time) {
		this.buy_time = buy_time;
	}

	@Column(name = "is_give_up")
	public Integer getIs_give_up() {
		return is_give_up;
	}
	
	public void setIs_give_up(Integer is_give_up) {
		this.is_give_up = is_give_up;
	}

}