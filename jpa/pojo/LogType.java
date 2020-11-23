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
 * TLogType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_log_type", catalog = "tiandian")

public class LogType implements java.io.Serializable {

	// Fields

	private Long log_type_id;
	private String log_type_name;
	private String simple_name;
	private Set<Log> logs = new HashSet<Log>(0);

	// Property accessors
	@Id
	@TableGenerator(name = "LOGTYPE_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_LOGTYPE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "LOGTYPE_GEN")
	@Column(name = "log_type_id", unique = true, nullable = false)
	public Long getLog_type_id() {
		return log_type_id;
	}

	public void setLog_type_id(Long log_type_id) {
		this.log_type_id = log_type_id;
	}

	@Column(name = "log_type_name")
	public String getLog_type_name() {
		return log_type_name;
	}

	public void setLog_type_name(String log_type_name) {
		this.log_type_name = log_type_name;
	}

	@Column(name = "simple_name")
	public String getSimple_name() {
		return simple_name;
	}

	public void setSimple_name(String simple_name) {
		this.simple_name = simple_name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "logType")
	public Set<Log> getLogs() {
		return logs;
	}

	public void setLogs(Set<Log> logs) {
		this.logs = logs;
	}

}