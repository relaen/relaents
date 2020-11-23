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
 * TGsGiveLevelRcd entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_gs_give_level_rcd", catalog = "tiandian")

public class GsGiveLevelRcd extends BasePojo implements java.io.Serializable {

	// Fields

	private Long gs_give_level_rcd_id;
	private GsGiveLevel endGsGiveLevel;
	private GsGiveLevel startGsGiveLevel;
	private GsMember gsMember;
	private Long rcd_time;
	private String remarks;

	@Override
	public Object getEntityId() {
		// TODO Auto-generated method stub
		return gs_give_level_rcd_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "GSGIVELEVELRCD_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_GSGIVELEVELRCD", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GSGIVELEVELRCD_GEN")
	@Column(name = "gs_give_level_rcd_id", unique = true, nullable = false)
	public Long getGs_give_level_rcd_id() {
		return gs_give_level_rcd_id;
	}

	public void setGs_give_level_rcd_id(Long gs_give_level_rcd_id) {
		this.gs_give_level_rcd_id = gs_give_level_rcd_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "end_gs_give_level_id")
	public GsGiveLevel getEndGsGiveLevel() {
		return endGsGiveLevel;
	}

	public void setEndGsGiveLevel(GsGiveLevel endGsGiveLevel) {
		this.endGsGiveLevel = endGsGiveLevel;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "start_gs_give_level_id")
	public GsGiveLevel getStartGsGiveLevel() {
		return startGsGiveLevel;
	}

	public void setStartGsGiveLevel(GsGiveLevel startGsGiveLevel) {
		this.startGsGiveLevel = startGsGiveLevel;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	public GsMember getGsMember() {
		return gsMember;
	}

	public void setGsMember(GsMember gsMember) {
		this.gsMember = gsMember;
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

}