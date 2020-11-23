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
 * GsSaleManGiveTask entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_gs_sale_man_give_task", catalog = "tiandian")

public class GsSaleManGiveTask extends BasePojo implements java.io.Serializable {

	// Fields

	private Long gs_sale_man_give_task_id;
	private GsSaleManGiveLevel gsSaleManGiveLevel;
	private Double task_index_new;
	private Double task_index_total;
	private Double fen_give;

	@Override
	public Object getEntityId() {
		// TODO Auto-generated method stub
		return gs_sale_man_give_task_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "GSSALEMANGIVETASK_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_GSSALEMANGIVETASK", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GSSALEMANGIVETASK_GEN")
	@Column(name = "gs_sale_man_give_task_id", unique = true, nullable = false)
	public Long getGs_sale_man_give_task_id() {
		return gs_sale_man_give_task_id;
	}

	public void setGs_sale_man_give_task_id(Long gs_sale_man_give_task_id) {
		this.gs_sale_man_give_task_id = gs_sale_man_give_task_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gs_sale_man_give_level_id")
	public GsSaleManGiveLevel getGsSaleManGiveLevel() {
		return gsSaleManGiveLevel;
	}

	public void setGsSaleManGiveLevel(GsSaleManGiveLevel gsSaleManGiveLevel) {
		this.gsSaleManGiveLevel = gsSaleManGiveLevel;
	}

	@Column(name = "task_index_new", precision = 13)
	public Double getTask_index_new() {
		return task_index_new;
	}

	public void setTask_index_new(Double task_index_new) {
		this.task_index_new = task_index_new;
	}

	@Column(name = "task_index_total", precision = 13)
	public Double getTask_index_total() {
		return task_index_total;
	}

	public void setTask_index_total(Double task_index_total) {
		this.task_index_total = task_index_total;
	}

	@Column(name = "fen_give", precision = 13)
	public Double getFen_give() {
		return fen_give;
	}

	public void setFen_give(Double fen_give) {
		this.fen_give = fen_give;
	}

}