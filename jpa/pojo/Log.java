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
 * TLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_log", catalog = "tiandian")

public class Log implements java.io.Serializable {

	// Fields

	private Long log_id;
	private LogType logType;
	private Member Member;
	private Long other_id;
	private Long log_time;

	// Property accessors
	@Id
	@TableGenerator(name = "LOG_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_LOG", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "LOG_GEN")
	@Column(name = "log_id", unique = true, nullable = false)
	public Long getLog_id() {
		return log_id;
	}

	public void setLog_id(Long log_id) {
		this.log_id = log_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "log_type_id")
	public LogType getLogType() {
		return logType;
	}

	public void setLogType(LogType logType) {
		this.logType = logType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	public Member getMember() {
		return Member;
	}

	public void setMember(Member member) {
		Member = member;
	}

	@Column(name = "other_id")
	public Long getOther_id() {
		return other_id;
	}

	public void setOther_id(Long other_id) {
		this.other_id = other_id;
	}

	@Column(name = "log_time")
	public Long getLog_time() {
		return log_time;
	}

	public void setLog_time(Long log_time) {
		this.log_time = log_time;
	}

}