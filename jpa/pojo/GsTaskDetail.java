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
 * TGsTaskDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_gs_task_detail", catalog = "tiandian")

public class GsTaskDetail extends BasePojo implements java.io.Serializable {

	// Fields

	private Long gs_task_detail_id;
	private GsTask gsTask;
	private GsGiveLevel gsGiveLevel;
	private Double gs_task_index;
	private Integer type; // 为0必须完成，不为0，则为不同归类，搭类之间的或关系，完成一类的任务即可
	private Set<GsTaskDetailMember> gsTaskDetailMembers = new HashSet<GsTaskDetailMember>(0);

	// Property accessors
	@Id
	@TableGenerator(name = "GSTASKDETAIL_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_GSTASKDETAIL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GSTASKDETAIL_GEN")
	@Column(name = "gs_task_detail_id", unique = true, nullable = false)
	public Long getGs_task_detail_id() {
		return gs_task_detail_id;
	}

	public void setGs_task_detail_id(Long gs_task_detail_id) {
		this.gs_task_detail_id = gs_task_detail_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gs_task_id")
	public GsTask getGsTask() {
		return gsTask;
	}

	public void setGsTask(GsTask gsTask) {
		this.gsTask = gsTask;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gs_give_level_id")
	public GsGiveLevel getGsGiveLevel() {
		return gsGiveLevel;
	}

	public void setGsGiveLevel(GsGiveLevel gsGiveLevel) {
		this.gsGiveLevel = gsGiveLevel;
	}

	@Column(name = "gs_task_index", precision = 13)
	public Double getGs_task_index() {
		return gs_task_index;
	}

	public void setGs_task_index(Double gs_task_index) {
		this.gs_task_index = gs_task_index;
	}

	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gsTaskDetail")
	public Set<GsTaskDetailMember> getGsTaskDetailMembers() {
		return gsTaskDetailMembers;
	}

	public void setGsTaskDetailMembers(Set<GsTaskDetailMember> gsTaskDetailMembers) {
		this.gsTaskDetailMembers = gsTaskDetailMembers;
	}

}