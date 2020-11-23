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
 * TExamineQuestion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_examine_question", catalog = "tiandian")

public class ExamineQuestion extends BasePojo implements java.io.Serializable {

	// Fields

	private Long examine_question_id;
	private Examine examine;
	private String title;
	private Integer type;// 1单选2多选3输入框
	private Integer sort_order;// 排序，数字大的在前面
	private Set<ExamineOption> examineOptions = new HashSet<ExamineOption>(0);
	private Set<MemberExamineOption> memberExamineOptions = new HashSet<MemberExamineOption>(0);

	@Override
	public Object getEntityId() {
		// TODO Auto-generated method stub
		return examine_question_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "EXAMINEQUESTION_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_EXAMINEQUESTION	", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "EXAMINEQUESTION_GEN")
	@Column(name = "examine_question_id", unique = true, nullable = false)
	public Long getExamine_question_id() {
		return examine_question_id;
	}

	public void setExamine_question_id(Long examine_question_id) {
		this.examine_question_id = examine_question_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "examine_id")
	public Examine getExamine() {
		return examine;
	}

	public void setExamine(Examine examine) {
		this.examine = examine;
	}

	@Column(name = "title", length = 50)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "sort_order")
	public Integer getSort_order() {
		return sort_order;
	}

	public void setSort_order(Integer sort_order) {
		this.sort_order = sort_order;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "examineQuestion")
	public Set<ExamineOption> getExamineOptions() {
		return examineOptions;
	}

	public void setExamineOptions(Set<ExamineOption> examineOptions) {
		this.examineOptions = examineOptions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "examineQuestion")
	public Set<MemberExamineOption> getMemberExamineOptions() {
		return memberExamineOptions;
	}

	public void setMemberExamineOptions(Set<MemberExamineOption> memberExamineOptions) {
		this.memberExamineOptions = memberExamineOptions;
	}

}