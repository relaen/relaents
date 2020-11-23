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
 * TYuejiGiveRcd entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_yueji_give_rcd", catalog = "tiandian")

public class YuejiGiveRcd extends BasePojo implements java.io.Serializable {

	// Fields

	private Long yueji_give_rcd_id;
	private Member giveMember;
	private Member getMember;
	private Long rcd_time;

	@Override
	public Object getEntityId() {
		return yueji_give_rcd_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "YUEJIGIVERCD_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_YUEJIGIVERCD", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "YUEJIGIVERCD_GEN")
	@Column(name = "yueji_give_rcd_id", unique = true, nullable = false)
	public Long getYueji_give_rcd_id() {
		return yueji_give_rcd_id;
	}

	public void setYueji_give_rcd_id(Long yueji_give_rcd_id) {
		this.yueji_give_rcd_id = yueji_give_rcd_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "give_member_id")
	public Member getGiveMember() {
		return giveMember;
	}

	public void setGiveMember(Member giveMember) {
		this.giveMember = giveMember;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "get_member_id")
	public Member getGetMember() {
		return getMember;
	}

	public void setGetMember(Member getMember) {
		this.getMember = getMember;
	}

	@Column(name = "rcd_time")
	public Long getRcd_time() {
		return rcd_time;
	}

	public void setRcd_time(Long rcd_time) {
		this.rcd_time = rcd_time;
	}

}