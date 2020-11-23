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
 * GivebiRcd entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_givebi_rcd", catalog = "tiandian")

public class GivebiRcd extends BasePojo implements java.io.Serializable {

	// Fields

	private Long givebi_rcd_id;
	private SaleMan saleMan;
	private Member member;
	private Long give_time;
	private Integer give_num;
	private Long get_time;
	private String get_code;
	private Long dead_time;
	private String remarks;
	private String wx_url_code;
	private Integer enabled;
	private Integer surplus_num;
	private Set<GivebiRes> givebiReses = new HashSet<GivebiRes>(0);

	// Constructors

	/** default constructor */
	public GivebiRcd() {
	}

	/** minimal constructor */
	public GivebiRcd(Long givebi_rcd_id) {
		this.givebi_rcd_id = givebi_rcd_id;
	}

	/** full constructor */
	public GivebiRcd(Long givebi_rcd_id, SaleMan saleMan, Member member, Long give_time, Integer give_num,
			String remarks) {
		this.givebi_rcd_id = givebi_rcd_id;
		this.saleMan = saleMan;
		this.member = member;
		this.give_time = give_time;
		this.give_num = give_num;
		this.remarks = remarks;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "GIVEBIRCD_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_GIVEBIRCD", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GIVEBIRCD_GEN")
	@Column(name = "givebi_rcd_id", unique = true, nullable = false)

	public Long getGivebi_rcd_id() {
		return this.givebi_rcd_id;
	}

	public void setGivebi_rcd_id(Long givebi_rcd_id) {
		this.givebi_rcd_id = givebi_rcd_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sale_man_id")

	public SaleMan getSaleMan() {
		return this.saleMan;
	}

	public void setSaleMan(SaleMan saleMan) {
		this.saleMan = saleMan;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Column(name = "give_time")

	public Long getGive_time() {
		return this.give_time;
	}

	public void setGive_time(Long give_time) {
		this.give_time = give_time;
	}

	@Column(name = "give_num")

	public Integer getGive_num() {
		return this.give_num;
	}

	public void setGive_num(Integer give_num) {
		this.give_num = give_num;
	}
	@Column(name = "remarks")
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "get_time")
	public Long getGet_time() {
		return get_time;
	}

	public void setGet_time(Long get_time) {
		this.get_time = get_time;
	}

	@Column(name = "get_code", length = 500)
	public String getGet_code() {
		return get_code;
	}

	public void setGet_code(String get_code) {
		this.get_code = get_code;
	}

	@Column(name = "dead_time", length = 500)
	public Long getDead_time() {
		return dead_time;
	}

	public void setDead_time(Long dead_time) {
		this.dead_time = dead_time;
	}

	@Column(name = "wx_url_code", length = 200)
	public String getWx_url_code() {
		return wx_url_code;
	}

	public void setWx_url_code(String wx_url_code) {
		this.wx_url_code = wx_url_code;
	}

	@Column(name = "enabled")
	public Integer getEnabled() {
		return enabled;
	}
	
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	@Column(name = "surplus_num")
	public Integer getSurplus_num() {
		return surplus_num;
	}
	
	public void setSurplus_num(Integer surplus_num) {
		this.surplus_num = surplus_num;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "givebiRcd")
	public Set<GivebiRes> getGivebiReses() {
		return givebiReses;
	}

	public void setGivebiReses(Set<GivebiRes> givebiReses) {
		this.givebiReses = givebiReses;
	}

	@Override
	public Object getEntityId() {
		return this.givebi_rcd_id;
	}
}