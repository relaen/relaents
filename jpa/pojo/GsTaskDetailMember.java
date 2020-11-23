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
 * TGsTaskDetailMember entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_gs_task_detail_member", catalog = "tiandian")

public class GsTaskDetailMember extends BasePojo implements java.io.Serializable {

	// Fields

	private Long gs_task_detail_member_id;
	private GsTaskDetail gsTaskDetail;
	private GsGiveLevelRcd gsGiveLevelRcd;
	private GsMember gsMember;
	private Double gs_task_index;
	private Double gs_task_index_finish;

	@Override
	public Object getEntityId() {
		// TODO Auto-generated method stub
		return gs_task_detail_member_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "GSTASKDETAILMEMBER_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_GSTASKDETAILMEMBER", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GSTASKDETAILMEMBER_GEN")
	@Column(name = "gs_task_detail_member_id", unique = true, nullable = false)
	public Long getGs_task_detail_member_id() {
		return gs_task_detail_member_id;
	}

	public void setGs_task_detail_member_id(Long gs_task_detail_member_id) {
		this.gs_task_detail_member_id = gs_task_detail_member_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gs_task_detail_id")
	public GsTaskDetail getGsTaskDetail() {
		return gsTaskDetail;
	}

	public void setGsTaskDetail(GsTaskDetail gsTaskDetail) {
		this.gsTaskDetail = gsTaskDetail;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gs_give_level_rcd_id")
	public GsGiveLevelRcd getGsGiveLevelRcd() {
		return gsGiveLevelRcd;
	}

	public void setGsGiveLevelRcd(GsGiveLevelRcd gsGiveLevelRcd) {
		this.gsGiveLevelRcd = gsGiveLevelRcd;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	public GsMember getGsMember() {
		return gsMember;
	}

	public void setGsMember(GsMember gsMember) {
		this.gsMember = gsMember;
	}

	@Column(name = "gs_task_index", precision = 13)
	public Double getGs_task_index() {
		return gs_task_index;
	}

	public void setGs_task_index(Double gs_task_index) {
		this.gs_task_index = gs_task_index;
	}

	@Column(name = "gs_task_index_finish", precision = 13)
	public Double getGs_task_index_finish() {
		return gs_task_index_finish;
	}

	public void setGs_task_index_finish(Double gs_task_index_finish) {
		this.gs_task_index_finish = gs_task_index_finish;
	}
}