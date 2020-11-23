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
 * TGsMemberLevelRcd entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_gs_member_level_rcd", catalog = "tiandian")

public class GsMemberLevelRcd extends BasePojo implements java.io.Serializable {

	// Fields

	private Long gs_member_level_rcd_id;
	private GsMemberLevel endGsMemberLevel;
	private GsMemberLevel startGsMemberLevel;
	private GsMember gsMember;
	private Long rcd_time;
	private String remarks;

	@Override
	public Object getEntityId() {
		// TODO Auto-generated method stub
		return gs_member_level_rcd_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "GSMEMBERLEVELRCD_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_GSMEMBERLEVELRCD", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GSMEMBERLEVELRCD_GEN")
	@Column(name = "gs_member_level_rcd_id", unique = true, nullable = false)
	public Long getGs_member_level_rcd_id() {
		return gs_member_level_rcd_id;
	}

	public void setGs_member_level_rcd_id(Long gs_member_level_rcd_id) {
		this.gs_member_level_rcd_id = gs_member_level_rcd_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "end_gs_member_level_id")
	public GsMemberLevel getEndGsMemberLevel() {
		return endGsMemberLevel;
	}

	public void setEndGsMemberLevel(GsMemberLevel endGsMemberLevel) {
		this.endGsMemberLevel = endGsMemberLevel;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "start_gs_member_level_id")
	public GsMemberLevel getStartGsMemberLevel() {
		return startGsMemberLevel;
	}

	public void setStartGsMemberLevel(GsMemberLevel startGsMemberLevel) {
		this.startGsMemberLevel = startGsMemberLevel;
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