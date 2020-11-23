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
 * TGsMemberLevel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_gs_member_level", catalog = "tiandian")

public class GsMemberLevel extends BasePojo implements java.io.Serializable {

	// Fields

	private Long gs_member_level_id;
	private String level_name;
	private Double start_dian_fen; // 当前等级大于该分数
	private Double end_dian_fen; // 当前等级小于等于该分数
	private Set<GsMemberLevelRcd> startGsMemberLevelRcds = new HashSet<GsMemberLevelRcd>(0);
	private Set<GsMember> gsMembers = new HashSet<GsMember>(0);
	private Set<GsMemberLevelRcd> endGsMemberLevelRcds = new HashSet<GsMemberLevelRcd>(0);

	@Override
	public Object getEntityId() {
		// TODO Auto-generated method stub
		return gs_member_level_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "GSMEMBERLEVEL_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_GSMEMBERLEVEL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GSMEMBERLEVEL_GEN")
	@Column(name = "gs_member_level_id", unique = true, nullable = false)
	public Long getGs_member_level_id() {
		return gs_member_level_id;
	}

	public void setGs_member_level_id(Long gs_member_level_id) {
		this.gs_member_level_id = gs_member_level_id;
	}

	@Column(name = "level_name", length = 50)
	public String getLevel_name() {
		return level_name;
	}

	public void setLevel_name(String level_name) {
		this.level_name = level_name;
	}

	@Column(name = "start_dian_fen", precision = 13)
	public Double getStart_dian_fen() {
		return start_dian_fen;
	}

	public void setStart_dian_fen(Double start_dian_fen) {
		this.start_dian_fen = start_dian_fen;
	}

	@Column(name = "end_dian_fen", precision = 13)
	public Double getEnd_dian_fen() {
		return end_dian_fen;
	}

	public void setEnd_dian_fen(Double end_dian_fen) {
		this.end_dian_fen = end_dian_fen;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gsMemberLevel")
	public Set<GsMember> getGsMembers() {
		return gsMembers;
	}

	public void setGsMembers(Set<GsMember> gsMembers) {
		this.gsMembers = gsMembers;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "startGsMemberLevel")

	public Set<GsMemberLevelRcd> getStartGsMemberLevelRcds() {
		return startGsMemberLevelRcds;
	}

	public void setStartGsMemberLevelRcds(Set<GsMemberLevelRcd> startGsMemberLevelRcds) {
		this.startGsMemberLevelRcds = startGsMemberLevelRcds;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "endGsMemberLevel")
	public Set<GsMemberLevelRcd> getEndGsMemberLevelRcds() {
		return endGsMemberLevelRcds;
	}

	public void setEndGsMemberLevelRcds(Set<GsMemberLevelRcd> endGsMemberLevelRcds) {
		this.endGsMemberLevelRcds = endGsMemberLevelRcds;
	}

}