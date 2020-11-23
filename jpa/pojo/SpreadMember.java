package dao.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * TSpreadMember entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_spread_member", catalog = "tiandian")

public class SpreadMember extends BasePojo implements java.io.Serializable {

	// Fields

	private Long member_id;
	private Member member;
	private String spread_no;

	@Override
	public Object getEntityId() {
		// TODO Auto-generated method stub
		return member_id;
	}

	// Property accessors
	@Id
	@Column(name = "member_id", unique = true, nullable = false)
	public Long getMember_id() {
		return member_id;
	}

	public void setMember_id(Long member_id) {
		this.member_id = member_id;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Column(name = "spread_no", length = 20)
	public String getSpread_no() {
		return spread_no;
	}

	public void setSpread_no(String spread_no) {
		this.spread_no = spread_no;
	}

}