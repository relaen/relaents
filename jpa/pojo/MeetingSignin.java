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
 * Message entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_meeting_signin", catalog = "tiandian")
public class MeetingSignin extends BasePojo implements java.io.Serializable {
	// Fields
	private Long meeting_signin_id;
	private Member member;
	private String meeting_type;
	private Long signin_time;

	@Override
	public Object getEntityId() {
		return this.meeting_signin_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "MEETINGSIGNIN_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_MEETINGSIGNIN", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MEETINGSIGNIN_GEN")
	@Column(name = "meeting_signin_id", unique = true, nullable = false)
	public Long getMeeting_signin_id() {
		return meeting_signin_id;
	}

	public void setMeeting_signin_id(Long meeting_signin_id) {
		this.meeting_signin_id = meeting_signin_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Column(name = "meeting_type")
	public String getMeeting_type() {
		return meeting_type;
	}

	public void setMeeting_type(String meeting_type) {
		this.meeting_type = meeting_type;
	}

	@Column(name = "signin_time")
	public Long getSignin_time() {
		return signin_time;
	}

	public void setSignin_time(Long signin_time) {
		this.signin_time = signin_time;
	}
}