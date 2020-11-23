package dao.pojo;
// default package

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
 * CommodityCheck entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_commodity_check", catalog = "tiandian")

public class CommodityCheck implements java.io.Serializable {

	// Fields

	private Long commodity_check_id;
	private Commodity commodity;
	private Member member;
	private Long check_time;
	private String reject_reason;
	private Short is_agree;
	private Long ask_time;
	private String commodity_data;
	private String remarks;

	// Constructors

	/** default constructor */
	public CommodityCheck() {
	}

	/** minimal constructor */
	public CommodityCheck(Long commodity_check_id) {
		this.commodity_check_id = commodity_check_id;
	}

	/** full constructor */
	public CommodityCheck(Long commodity_check_id, Commodity commodity, Member member, Long check_time,
			String reject_reason, Short is_agree, Long ask_time) {
		this.commodity_check_id = commodity_check_id;
		this.commodity = commodity;
		this.member = member;
		this.check_time = check_time;
		this.reject_reason = reject_reason;
		this.is_agree = is_agree;
		this.ask_time = ask_time;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "COMMODITYCHECK_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COMMODITYCHECK", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMMODITYCHECK_GEN")
	@Column(name = "commodity_check_id", unique = true, nullable = false)

	public Long getCommodity_check_id() {
		return this.commodity_check_id;
	}

	public void setCommodity_check_id(Long commodity_check_id) {
		this.commodity_check_id = commodity_check_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commodity_id")

	public Commodity getCommodity() {
		return this.commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "checker_id")

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
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

	@Column(name = "is_agree")

	public Short getIs_agree() {
		return this.is_agree;
	}

	public void setIs_agree(Short is_agree) {
		this.is_agree = is_agree;
	}

	@Column(name = "ask_time")

	public Long getAsk_time() {
		return this.ask_time;
	}

	public void setAsk_time(Long ask_time) {
		this.ask_time = ask_time;
	}

	@Column(name = "commodity_data")
	public String getCommodity_data() {
		return commodity_data;
	}

	public void setCommodity_data(String commodity_data) {
		this.commodity_data = commodity_data;
	}

	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}