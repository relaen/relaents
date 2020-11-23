package dao.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * TMemberExamine entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_member_examine", catalog = "tiandian")

public class MemberExamine extends BasePojo implements java.io.Serializable {

	// Fields

	private Long member_examine_id;
	private Examine examine;
	private Member member;
	private Long create_time;
	private String real_name;
	private String mobile;
	private String suggest;
	private Set<MemberExamineOption> memberExamineOptions = new HashSet<MemberExamineOption>(0);

	@Override
	public Object getEntityId() {
		// TODO Auto-generated method stub
		return member_examine_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "MEMBEREXAMINE_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_MEMBEREXAMINE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMBEREXAMINE_GEN")
	@Column(name = "member_examine_id", unique = true, nullable = false)

	public Long getMember_examine_id() {
		return member_examine_id;
	}

	public void setMember_examine_id(Long member_examine_id) {
		this.member_examine_id = member_examine_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "examine_id")
	public Examine getExamine() {
		return examine;
	}

	public void setExamine(Examine examine) {
		this.examine = examine;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Column(name = "create_time")
	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	@Column(name = "real_name")
	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	@Column(name = "mobile")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "suggest")
	public String getSuggest() {
		return suggest;
	}

	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "memberExamine")
	public Set<MemberExamineOption> getMemberExamineOptions() {
		return memberExamineOptions;
	}

	public void setMemberExamineOptions(Set<MemberExamineOption> memberExamineOptions) {
		this.memberExamineOptions = memberExamineOptions;
	}

}