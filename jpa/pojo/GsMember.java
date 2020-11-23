package dao.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * TGsMember entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_gs_member", catalog = "tiandian")

public class GsMember extends BasePojo implements java.io.Serializable {

	// Fields

	private Long member_id;
	private Member member;
	private GsMemberLevel gsMemberLevel;
	private GsGiveLevel gsGiveLevel;
	private GsGiveLevel prevGsGiveLevel;
	private Double dian_fen;
	private Double start_dian_fen;
	private Long start_time;
	private Long end_time;
	private Long prev_start_time;
	private Set<GsMemberLevelRcd> gsMemberLevelRcds = new HashSet<GsMemberLevelRcd>(0);
	private Set<GsTaskDetailMember> gsTaskDetailMembers = new HashSet<GsTaskDetailMember>(0);
	private Set<GsGiveLevelRcd> gsGiveLevelRcds = new HashSet<GsGiveLevelRcd>(0);

	@Override
	public Object getEntityId() {
		// TODO Auto-generated method stub
		return member_id;
	}

	// Property accessors
	@Id
	@Column(name = "member_id", unique = true, nullable = false)
	public Long getMember_id() {
		return member_id;
	}

	public void setMember_id(Long member_id) {
		this.member_id = member_id;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gs_member_level_id")
	public GsMemberLevel getGsMemberLevel() {
		return gsMemberLevel;
	}

	public void setGsMemberLevel(GsMemberLevel gsMemberLevel) {
		this.gsMemberLevel = gsMemberLevel;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gs_give_level_id")
	public GsGiveLevel getGsGiveLevel() {
		return gsGiveLevel;
	}

	public void setGsGiveLevel(GsGiveLevel gsGiveLevel) {
		this.gsGiveLevel = gsGiveLevel;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prev_gs_give_level_id")
	public GsGiveLevel getPrevGsGiveLevel() {
		return prevGsGiveLevel;
	}

	public void setPrevGsGiveLevel(GsGiveLevel prevGsGiveLevel) {
		this.prevGsGiveLevel = prevGsGiveLevel;
	}

	@Column(name = "dian_fen", precision = 13)
	public Double getDian_fen() {
		return dian_fen;
	}

	public void setDian_fen(Double dian_fen) {
		this.dian_fen = dian_fen;
	}

	@Column(name = "start_dian_fen", precision = 13)
	public Double getStart_dian_fen() {
		return start_dian_fen;
	}

	public void setStart_dian_fen(Double start_dian_fen) {
		this.start_dian_fen = start_dian_fen;
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

	@Column(name = "prev_start_time")
	public Long getPrev_start_time() {
		return prev_start_time;
	}

	public void setPrev_start_time(Long prev_start_time) {
		this.prev_start_time = prev_start_time;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gsMember")
	public Set<GsMemberLevelRcd> getGsMemberLevelRcds() {
		return gsMemberLevelRcds;
	}

	public void setGsMemberLevelRcds(Set<GsMemberLevelRcd> gsMemberLevelRcds) {
		this.gsMemberLevelRcds = gsMemberLevelRcds;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gsMember")
	public Set<GsTaskDetailMember> getGsTaskDetailMembers() {
		return gsTaskDetailMembers;
	}

	public void setGsTaskDetailMembers(Set<GsTaskDetailMember> gsTaskDetailMembers) {
		this.gsTaskDetailMembers = gsTaskDetailMembers;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gsMember")
	public Set<GsGiveLevelRcd> getGsGiveLevelRcds() {
		return gsGiveLevelRcds;
	}

	public void setGsGiveLevelRcds(Set<GsGiveLevelRcd> gsGiveLevelRcds) {
		this.gsGiveLevelRcds = gsGiveLevelRcds;
	}

}