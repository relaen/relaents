package dao.pojo;
// default package

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
 * BeSaleman entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_be_saleman", catalog = "tiandian")

public class BeSaleman extends BasePojo implements java.io.Serializable {

	// Fields

	private Long be_saleman_id;
	private Member member;
	private Member checker;
	private Long ask_time;
	private Integer is_agree;
	private Long check_time;
	private String ask_reason;
	private String reject_reason;
	private Integer ask_type;
	private Float longitude; // 经度
	private Float latitude; // 纬度
	private String area_ids; // 申请区域
	private Set<AskResource> askResources = new HashSet<AskResource>(0);

	// Constructors

	/** default constructor */
	public BeSaleman() {
	}

	/** minimal constructor */
	public BeSaleman(Long be_saleman_id) {
		this.be_saleman_id = be_saleman_id;
	}

	/** full constructor */
	public BeSaleman(Long be_saleman_id, Member member, Long ask_time, Integer is_agree, Long check_time,
			String reject_reason, Integer ask_type) {
		this.be_saleman_id = be_saleman_id;
		this.member = member;
		this.ask_time = ask_time;
		this.is_agree = is_agree;
		this.check_time = check_time;
		this.reject_reason = reject_reason;
		this.ask_type = ask_type;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "BESALEMAN_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_BESALEMAN", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "BESALEMAN_GEN")
	@Column(name = "be_saleman_id", unique = true, nullable = false)

	public Long getBe_saleman_id() {
		return this.be_saleman_id;
	}

	public void setBe_saleman_id(Long be_saleman_id) {
		this.be_saleman_id = be_saleman_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "check_member_id")
	public Member getChecker() {
		return checker;
	}

	public void setChecker(Member checker) {
		this.checker = checker;
	}

	@Column(name = "ask_time")

	public Long getAsk_time() {
		return this.ask_time;
	}

	public void setAsk_time(Long ask_time) {
		this.ask_time = ask_time;
	}

	@Column(name = "is_agree")

	public Integer getIs_agree() {
		return this.is_agree;
	}

	public void setIs_agree(Integer is_agree) {
		this.is_agree = is_agree;
	}

	@Column(name = "check_time")

	public Long getCheck_time() {
		return this.check_time;
	}

	public void setCheck_time(Long check_time) {
		this.check_time = check_time;
	}

	@Column(name = "reject_reason", length = 500)

	public String getReject_reason() {
		return this.reject_reason;
	}

	public void setReject_reason(String reject_reason) {
		this.reject_reason = reject_reason;
	}

	@Column(name = "ask_type")

	public Integer getAsk_type() {
		return this.ask_type;
	}

	public void setAsk_type(Integer ask_type) {
		this.ask_type = ask_type;
	}

	@Column(name = "longitude")
	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	@Column(name = "latitude")
	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	@Column(name = "ask_reason")
	public String getAsk_reason() {
		return ask_reason;
	}

	public void setAsk_reason(String ask_reason) {
		this.ask_reason = ask_reason;
	}

	@Column(name = "area_ids")
	public String getArea_ids() {
		return area_ids;
	}

	public void setArea_ids(String area_ids) {
		this.area_ids = area_ids;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "beSaleman")
	public Set<AskResource> getAskResources() {
		return askResources;
	}

	public void setAskResources(Set<AskResource> askResources) {
		this.askResources = askResources;
	}

	@Override
	public Object getEntityId() {
		return this.be_saleman_id;
	}
}