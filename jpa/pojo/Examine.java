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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * TExamine entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_examine", catalog = "tiandian")

public class Examine extends BasePojo implements java.io.Serializable {

	// Fields

	private Long examine_id;
	private Long start_time;
	private Long end_time;
	private Set<ExamineQuestion> examineQuestions = new HashSet<ExamineQuestion>(0);
	private Set<MemberExamine> memberExamines = new HashSet<MemberExamine>(0);

	@Override
	public Object getEntityId() {
		// TODO Auto-generated method stub
		return examine_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "EXAMINE_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_EXAMINE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "EXAMINE_GEN")
	@Column(name = "examine_id", unique = true, nullable = false)
	public Long getExamine_id() {
		return examine_id;
	}

	public void setExamine_id(Long examine_id) {
		this.examine_id = examine_id;
	}

	@Column(name = "start_time")
	public Long getStart_time() {
		return start_time;
	}

	public void setStart_time(Long start_time) {
		this.start_time = start_time;
	}

	@Column(name = "end_time")
	public Long getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Long end_time) {
		this.end_time = end_time;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "examine")
	public Set<ExamineQuestion> getExamineQuestions() {
		return examineQuestions;
	}

	public void setExamineQuestions(Set<ExamineQuestion> examineQuestions) {
		this.examineQuestions = examineQuestions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "examine")
	public Set<MemberExamine> getMemberExamines() {
		return memberExamines;
	}

	public void setMemberExamines(Set<MemberExamine> memberExamines) {
		this.memberExamines = memberExamines;
	}

}