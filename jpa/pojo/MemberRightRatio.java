package dao.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * TMemberRightRatio entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_member_right_ratio", catalog = "tiandian")

public class MemberRightRatio extends BasePojo implements java.io.Serializable {

	// Fields

	private Long member_id;
	private Member Member;
	private Double right_ratio;

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
		return Member;
	}

	public void setMember(Member member) {
		Member = member;
	}

	@Column(name = "right_ratio", precision = 4)
	public Double getRight_ratio() {
		return right_ratio;
	}

	public void setRight_ratio(Double right_ratio) {
		this.right_ratio = right_ratio;
	}

}