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
 * TGsTask entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_gs_task", catalog = "tiandian")

public class GsTask extends BasePojo implements java.io.Serializable {

	// Fields

	private Long gs_task_id;
	private String gs_task_name;
	private String simple_name;
	private Integer enabled;
	private Set<GsTaskDetail> gsTaskDetails = new HashSet<GsTaskDetail>(0);

	@Override
	public Object getEntityId() {
		// TODO Auto-generated method stub
		return gs_task_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "GSTASK_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_GSTASK", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GSTASK_GEN")
	@Column(name = "gs_task_id", unique = true, nullable = false)
	public Long getGs_task_id() {
		return gs_task_id;
	}

	public void setGs_task_id(Long gs_task_id) {
		this.gs_task_id = gs_task_id;
	}

	@Column(name = "gs_task_name", length = 50)
	public String getGs_task_name() {
		return gs_task_name;
	}

	public void setGs_task_name(String gs_task_name) {
		this.gs_task_name = gs_task_name;
	}

	@Column(name = "simple_name", length = 50)
	public String getSimple_name() {
		return simple_name;
	}

	public void setSimple_name(String simple_name) {
		this.simple_name = simple_name;
	}

	@Column(name = "enabled")
	public Integer getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gsTask")
	public Set<GsTaskDetail> getGsTaskDetails() {
		return gsTaskDetails;
	}

	public void setGsTaskDetails(Set<GsTaskDetail> gsTaskDetails) {
		this.gsTaskDetails = gsTaskDetails;
	}

}