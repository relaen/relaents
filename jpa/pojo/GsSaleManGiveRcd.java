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
 * GsSaleManGiveRcd entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_gs_sale_man_give_rcd", catalog = "tiandian")

public class GsSaleManGiveRcd extends BasePojo implements java.io.Serializable {

	// Fields

	private Long gs_sale_man_give_rcd_id;
	private GsSaleManGiveTask gsSaleManGiveTask;
	private GsSaleMan gsSaleMan;
	private Double fen_give;
	private Long rcd_time;
	private String remarks;
	private Double task_index_new;
	private Double task_index_total;
	private Double task_index_new_finish;
	private Double task_index_total_finish;
	private Long start_time;
	private Long end_time;
	private Double fen_cur;

	@Override
	public Object getEntityId() {
		// TODO Auto-generated method stub
		return gs_sale_man_give_rcd_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "GSSALEMANGIVERCD_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_GSSALEMANGIVERCD", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GSSALEMANGIVERCD_GEN")
	@Column(name = "gs_sale_man_give_rcd_id", unique = true, nullable = false)
	public Long getGs_sale_man_give_rcd_id() {
		return gs_sale_man_give_rcd_id;
	}

	public void setGs_sale_man_give_rcd_id(Long gs_sale_man_give_rcd_id) {
		this.gs_sale_man_give_rcd_id = gs_sale_man_give_rcd_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gs_sale_man_give_task_id")
	public GsSaleManGiveTask getGsSaleManGiveTask() {
		return gsSaleManGiveTask;
	}

	public void setGsSaleManGiveTask(GsSaleManGiveTask gsSaleManGiveTask) {
		this.gsSaleManGiveTask = gsSaleManGiveTask;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sale_man_id")
	public GsSaleMan getGsSaleMan() {
		return gsSaleMan;
	}

	public void setGsSaleMan(GsSaleMan gsSaleMan) {
		this.gsSaleMan = gsSaleMan;
	}

	@Column(name = "fen_give", precision = 13)
	public Double getFen_give() {
		return fen_give;
	}

	public void setFen_give(Double fen_give) {
		this.fen_give = fen_give;
	}

	@Column(name = "rcd_time")
	public Long getRcd_time() {
		return rcd_time;
	}

	public void setRcd_time(Long rcd_time) {
		this.rcd_time = rcd_time;
	}

	@Column(name = "remarks", length = 50)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "task_index_new", precision = 13)
	public Double getTask_index_new() {
		return this.task_index_new;
	}

	public void setTask_index_new(Double task_index_new) {
		this.task_index_new = task_index_new;
	}

	@Column(name = "task_index_total", precision = 13)
	public Double getTask_index_total() {
		return this.task_index_total;
	}

	public void setTask_index_total(Double task_index_total) {
		this.task_index_total = task_index_total;
	}

	@Column(name = "task_index_new_finish", precision = 13)
	public Double getTask_index_new_finish() {
		return this.task_index_new_finish;
	}

	public void setTask_index_new_finish(Double task_index_new_finish) {
		this.task_index_new_finish = task_index_new_finish;
	}

	@Column(name = "task_index_total_finish", precision = 13)
	public Double getTask_index_total_finish() {
		return this.task_index_total_finish;
	}

	public void setTask_index_total_finish(Double task_index_total_finish) {
		this.task_index_total_finish = task_index_total_finish;
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

	@Column(name = "fen_cur", precision = 13)
	public Double getFen_cur() {
		return fen_cur;
	}
	
	public void setFen_cur(Double fen_cur) {
		this.fen_cur = fen_cur;
	}

}