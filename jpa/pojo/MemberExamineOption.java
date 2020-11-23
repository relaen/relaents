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
 * TMemberExamineOption entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_member_examine_option", catalog = "tiandian")
public class MemberExamineOption extends BasePojo implements java.io.Serializable {

	// Fields

	private Long memberExamineOptionId;
	private ExamineOption examineOption;
	private ExamineQuestion examineQuestion;
	private MemberExamine memberExamine;
	private String content;

	@Override
	public Object getEntityId() {
		// TODO Auto-generated method stub
		return memberExamineOptionId;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "MEMBEREXAMINEOPTION_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_MEMBEREXAMINEOPTION", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMBEREXAMINEOPTION_GEN")
	@Column(name = "member_examine_option_id", unique = true, nullable = false)
	public Long getMemberExamineOptionId() {
		return this.memberExamineOptionId;
	}

	public void setMemberExamineOptionId(Long memberExamineOptionId) {
		this.memberExamineOptionId = memberExamineOptionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "examine_option_id")
	public ExamineOption getExamineOption() {
		return examineOption;
	}

	public void setExamineOption(ExamineOption examineOption) {
		this.examineOption = examineOption;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "examine_question_id")
	public ExamineQuestion getExamineQuestion() {
		return examineQuestion;
	}

	public void setExamineQuestion(ExamineQuestion examineQuestion) {
		this.examineQuestion = examineQuestion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_examine_id")
	public MemberExamine getMemberExamine() {
		return memberExamine;
	}

	public void setMemberExamine(MemberExamine memberExamine) {
		this.memberExamine = memberExamine;
	}

	@Column(name = "content", length = 200)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}