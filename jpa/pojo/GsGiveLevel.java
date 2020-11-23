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
 * TGsGiveLevel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_gs_give_level", catalog = "tiandian")

public class GsGiveLevel implements java.io.Serializable {

	// Fields

	private Long gs_give_level_id;
	private String level_name;
	private Double right_radio;
	private Integer valid_day_num;
	private Integer first_valid_day_num;
	private Integer is_def;
	private Set<GsMember> gsMembers = new HashSet<GsMember>(0);
	private Set<GsTaskDetail> gsTaskDetails = new HashSet<GsTaskDetail>(0);
	private Set<GsGiveLevelRcd> endGsGiveLevelRcds = new HashSet<GsGiveLevelRcd>(0);
	private Set<GsGiveLevelRcd> startGsGiveLevelRcds = new HashSet<GsGiveLevelRcd>(0);

	// Property accessors
	@Id
	@TableGenerator(name = "GSGIVELEVEL_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_GSGIVELEVEL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GSGIVELEVEL_GEN")
	@Column(name = "gs_give_level_id", unique = true, nullable = false)
	public Long getGs_give_level_id() {
		return gs_give_level_id;
	}

	public void setGs_give_level_id(Long gs_give_level_id) {
		this.gs_give_level_id = gs_give_level_id;
	}

	@Column(name = "level_name", length = 50)
	public String getLevel_name() {
		return level_name;
	}

	public void setLevel_name(String level_name) {
		this.level_name = level_name;
	}

	@Column(name = "right_radio", precision = 4)
	public Double getRight_radio() {
		return right_radio;
	}

	public void setRight_radio(Double right_radio) {
		this.right_radio = right_radio;
	}

	@Column(name = "valid_day_num")
	public Integer getValid_day_num() {
		return valid_day_num;
	}

	public void setValid_day_num(Integer valid_day_num) {
		this.valid_day_num = valid_day_num;
	}

	@Column(name = "first_valid_day_num")
	public Integer getFirst_valid_day_num() {
		return first_valid_day_num;
	}
	
	public void setFirst_valid_day_num(Integer first_valid_day_num) {
		this.first_valid_day_num = first_valid_day_num;
	}

	@Column(name = "is_def")
	public Integer getIs_def() {
		return is_def;
	}

	public void setIs_def(Integer is_def) {
		this.is_def = is_def;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gsGiveLevel")
	public Set<GsMember> getGsMembers() {
		return gsMembers;
	}

	public void setGsMembers(Set<GsMember> gsMembers) {
		this.gsMembers = gsMembers;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gsGiveLevel")
	public Set<GsTaskDetail> getGsTaskDetails() {
		return gsTaskDetails;
	}

	public void setGsTaskDetails(Set<GsTaskDetail> gsTaskDetails) {
		this.gsTaskDetails = gsTaskDetails;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "endGsGiveLevel")
	public Set<GsGiveLevelRcd> getEndGsGiveLevelRcds() {
		return endGsGiveLevelRcds;
	}

	public void setEndGsGiveLevelRcds(Set<GsGiveLevelRcd> endGsGiveLevelRcds) {
		this.endGsGiveLevelRcds = endGsGiveLevelRcds;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "startGsGiveLevel")
	public Set<GsGiveLevelRcd> getStartGsGiveLevelRcds() {
		return startGsGiveLevelRcds;
	}

	public void setStartGsGiveLevelRcds(Set<GsGiveLevelRcd> startGsGiveLevelRcds) {
		this.startGsGiveLevelRcds = startGsGiveLevelRcds;
	}

}