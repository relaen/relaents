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
 * BiAsk entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_bi_ask", catalog = "tiandian")

public class BiAsk extends BasePojo implements java.io.Serializable {
	private Long bi_ask_id;
	private SaleMan saleMan;
	private Long ask_time;
	private Integer ask_num;
	private Integer menber_num;
	private Integer is_agree;
	private String ask_reason;
	private String reject_reason;
	private Long check_time;
	private Member checker;
	private Integer real_num;

	// Constructors

	/** default constructor */
	public BiAsk() {
	}

	/** minimal constructor */
	public BiAsk(Long bi_ask_id) {
		this.bi_ask_id = bi_ask_id;
	}

	/** full constructor */
	public BiAsk(Long bi_ask_id, SaleMan saleMan, Long ask_time, Integer ask_num, Integer is_agree, String ask_reason,
			String reject_reason, Long check_time) {
		this.bi_ask_id = bi_ask_id;
		this.saleMan = saleMan;
		this.ask_time = ask_time;
		this.ask_num = ask_num;
		this.is_agree = is_agree;
		this.ask_reason = ask_reason;
		this.reject_reason = reject_reason;
		this.check_time = check_time;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "BIASK_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_BIASK", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "BIASK_GEN")
	@Column(name = "bi_ask_id", unique = true, nullable = false)

	public Long getBi_ask_id() {
		return this.bi_ask_id;
	}

	public void setBi_ask_id(Long bi_ask_id) {
		this.bi_ask_id = bi_ask_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sale_man_id")

	public SaleMan getSaleMan() {
		return this.saleMan;
	}

	public void setSaleMan(SaleMan saleMan) {
		this.saleMan = saleMan;
	}

	@Column(name = "ask_time")

	public Long getAsk_time() {
		return this.ask_time;
	}

	public void setAsk_time(Long ask_time) {
		this.ask_time = ask_time;
	}

	@Column(name = "ask_num")

	public Integer getAsk_num() {
		return this.ask_num;
	}

	public void setAsk_num(Integer ask_num) {
		this.ask_num = ask_num;
	}

	@Column(name = "is_agree")

	public Integer getIs_agree() {
		return this.is_agree;
	}

	public void setIs_agree(Integer is_agree) {
		this.is_agree = is_agree;
	}

	@Column(name = "ask_reason", length = 500)

	public String getAsk_reason() {
		return this.ask_reason;
	}

	public void setAsk_reason(String ask_reason) {
		this.ask_reason = ask_reason;
	}

	@Column(name = "reject_reason", length = 500)

	public String getReject_reason() {
		return this.reject_reason;
	}

	public void setReject_reason(String reject_reason) {
		this.reject_reason = reject_reason;
	}

	@Column(name = "check_time")

	public Long getCheck_time() {
		return this.check_time;
	}

	public void setCheck_time(Long check_time) {
		this.check_time = check_time;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "check_member_id")
	public Member getChecker() {
		return checker;
	}

	public void setChecker(Member checker) {
		this.checker = checker;
	}

	@Override
	public Object getEntityId() {
		return this.bi_ask_id;
	}

	@Column(name = "menber_num")
	public Integer getMenber_num() {
		return menber_num;
	}

	public void setMenber_num(Integer menber_num) {
		this.menber_num = menber_num;
	}

	@Column(name = "real_num")
	public Integer getReal_num() {
		return real_num;
	}

	public void setReal_num(Integer real_num) {
		this.real_num = real_num;
	}

}