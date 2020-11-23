package dao.pojo;

import javax.persistence.TableGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
// default package
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * AttentionRcd entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_attention_rcd", catalog = "tiandian")
public class AttentionRcd extends BasePojo implements java.io.Serializable {
	// Fields
	private Long attention_rcd_id;
	private Company company;
	private Member member;
	private Long attention_time;
	private Long cancel_time;

	// Constructors
	/** default constructor */
	public AttentionRcd() {
	}

	/** minimal constructor */
	public AttentionRcd(Long attention_rcd_id, Long attention_time, Long cancel_time) {
		this.attention_rcd_id = attention_rcd_id;
		this.attention_time = attention_time;
		this.cancel_time = cancel_time;
	}

	/** full constructor */
	public AttentionRcd(Long attention_rcd_id, Company company, Member member, Long attention_time,
			Long cancel_time) {
		this.attention_rcd_id = attention_rcd_id;
		this.company = company;
		this.member = member;
		this.attention_time = attention_time;
		this.cancel_time = cancel_time;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "ATTENTIONRCD_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_ATTENTIONRCD", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ATTENTIONRCD_GEN")
	@Column(name = "attention_rcd_id", unique = true, nullable = false)
	public Long getAttention_rcd_id() {
		return this.attention_rcd_id;
	}

	public void setAttention_rcd_id(Long attention_rcd_id) {
		this.attention_rcd_id = attention_rcd_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Column(name = "attention_time", nullable = false, length = 19)
	public Long getAttention_time() {
		return this.attention_time;
	}

	public void setAttention_time(Long attention_time) {
		this.attention_time = attention_time;
	}

	@Column(name = "cancel_time", nullable = false, length = 19)
	public Long getCancel_time() {
		return this.cancel_time;
	}

	public void setCancel_time(Long cancel_time) {
		this.cancel_time = cancel_time;
	}

	@Override
	public Object getEntityId() {
		return this.attention_rcd_id;
	}
}