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
 * TExamineOption entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_examine_option", catalog = "tiandian")

public class ExamineOption extends BasePojo implements java.io.Serializable {

	// Fields

	private Long examine_option_id;
	private ExamineQuestion examineQuestion;
	private String title;
	private Integer sort_order; // 排序，数字大的在前面
	private Set<MemberExamineOption> memberExamineOptions = new HashSet<MemberExamineOption>(0);

	@Override
	public Object getEntityId() {
		// TODO Auto-generated method stub
		return examine_option_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "EXAMINEOPTION_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_EXAMINEOPTION", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "EXAMINEOPTION_GEN")
	@Column(name = "examine_option_id", unique = true, nullable = false)
	public Long getExamine_option_id() {
		return examine_option_id;
	}

	public void setExamine_option_id(Long examine_option_id) {
		this.examine_option_id = examine_option_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "examine_question_id")
	public ExamineQuestion getExamineQuestion() {
		return examineQuestion;
	}

	public void setExamineQuestion(ExamineQuestion examineQuestion) {
		this.examineQuestion = examineQuestion;
	}

	@Column(name = "title", length = 50)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "sort_order")
	public Integer getSort_order() {
		return sort_order;
	}

	public void setSort_order(Integer sort_order) {
		this.sort_order = sort_order;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "examineOption")
	public Set<MemberExamineOption> getMemberExamineOptions() {
		return memberExamineOptions;
	}

	public void setMemberExamineOptions(Set<MemberExamineOption> memberExamineOptions) {
		this.memberExamineOptions = memberExamineOptions;
	}

}