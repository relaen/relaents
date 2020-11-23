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
 * TOnlineRcd entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_online_rcd", catalog = "tiandian")

public class OnlineRcd extends BasePojo implements java.io.Serializable {

	// Fields

	private Long online_rcd_id;
	private Member member;
	private Long online_time;
	private Long offline_time;

	@Override
	public Object getEntityId() {
		// TODO Auto-generated method stub
		return online_rcd_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "ONLINERCD_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_ONLINERCD", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ONLINERCD_GEN")
	@Column(name = "online_rcd_id", unique = true, nullable = false)
	public Long getOnline_rcd_id() {
		return online_rcd_id;
	}

	public void setOnline_rcd_id(Long online_rcd_id) {
		this.online_rcd_id = online_rcd_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Column(name = "online_time")
	public Long getOnline_time() {
		return online_time;
	}

	public void setOnline_time(Long online_time) {
		this.online_time = online_time;
	}

	@Column(name = "offline_time")
	public Long getOffline_time() {
		return offline_time;
	}

	public void setOffline_time(Long offline_time) {
		this.offline_time = offline_time;
	}

}