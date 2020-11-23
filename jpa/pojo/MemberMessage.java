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
@Table(name = "t_member_message", catalog = "tiandian")
public class MemberMessage extends BasePojo implements java.io.Serializable {
	// Fields
	private Long member_message_id;
	private Member member;
	private Message message;
	private Integer has_readed = 0;

	@Override
	public Object getEntityId() {
		return this.member_message_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "MEMBERMESSAGE_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_MEMBERMESSAGE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMBERMESSAGE_GEN")
	@Column(name = "member_message_id", unique = true, nullable = false)
	public Long getMember_message_id() {
		return member_message_id;
	}

	public void setMember_message_id(Long member_message_id) {
		this.member_message_id = member_message_id;
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
	@JoinColumn(name = "message_id")
	public Message getMessage() {
		return this.message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	@Column(name = "has_readed")
	public Integer getHas_readed() {
		return has_readed;
	}

	public void setHas_readed(Integer has_readed) {
		this.has_readed = has_readed;
	}
}