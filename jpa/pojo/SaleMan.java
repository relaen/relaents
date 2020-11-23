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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * SaleMan entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_sale_man", catalog = "tiandian")

public class SaleMan extends BasePojo implements java.io.Serializable {

	// Fields

	private Long sale_man_id;
	private GsSaleMan gsSaleMan; // 成长体系
	private SaleMan saleMan; // 上级
	private SaleMan recommender; // 区域经理推荐人
	private SaleMan sourceMan; // 晋升前的来源人
	private Area area;
	private Member member;
	private String sale_man_no;
	private String member_img;
	private String company_img;
	private Integer sale_man_type = 1; // 1推广大使 2区域经理 3大区经理
	private Long create_time; // 成为时间

	private String member_code;
	private String wx_url;
	private String wx_url_code;
	private Long wx_url_time;
	private String wx_company_url;
	private String wx_company_url_code;
	private Long wx_company_url_time;
	private Set<GivebiRcd> givebiRcds = new HashSet<GivebiRcd>(0);
	private Set<BiAsk> biAsks = new HashSet<BiAsk>(0);
	private Set<Company> companies = new HashSet<Company>(0);
	private Set<CashRcd> cashRcds = new HashSet<CashRcd>(0);
	private Set<ProfitAssign> profitAssigns = new HashSet<ProfitAssign>(0);
	private Set<AskSpreadCompany> askSpreadCompanies = new HashSet<AskSpreadCompany>(0);

	// Constructors

	/** default constructor */
	public SaleMan() {
	}

	// Property accessors
	@Id
	@TableGenerator(name = "SALEMAN_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_SALEMAN", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SALEMAN_GEN")
	@Column(name = "sale_man_id", unique = true, nullable = false)
	public Long getSale_man_id() {
		return this.sale_man_id;
	}

	public void setSale_man_id(Long sale_man_id) {
		this.sale_man_id = sale_man_id;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "saleMan")
	public GsSaleMan getGsSaleMan() {
		return gsSaleMan;
	}
	
	public void setGsSaleMan(GsSaleMan gsSaleMan) {
		this.gsSaleMan = gsSaleMan;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manager_id")

	public SaleMan getSaleMan() {
		return this.saleMan;
	}

	public void setSaleMan(SaleMan saleMan) {
		this.saleMan = saleMan;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "recommender_id")

	public SaleMan getRecommender() {
		return this.recommender;
	}

	public void setRecommender(SaleMan recommender) {
		this.recommender = recommender;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "source_id")
	public SaleMan getSourceMan() {
		return sourceMan;
	}

	public void setSourceMan(SaleMan sourceMan) {
		this.sourceMan = sourceMan;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_id")

	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Column(name = "sale_man_no", length = 20)

	public String getSale_man_no() {
		return this.sale_man_no;
	}

	public void setSale_man_no(String sale_man_no) {
		this.sale_man_no = sale_man_no;
	}

	@Column(name = "sale_man_type")
	public Integer getSale_man_type() {
		return sale_man_type;
	}

	public void setSale_man_type(Integer sale_man_type) {
		this.sale_man_type = sale_man_type;
	}

	@Column(name = "create_time")
	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	public String getMember_img() {
		return this.member_img;
	}

	public void setMember_img(String member_img) {
		this.member_img = member_img;
	}

	@Column(name = "company_img", length = 500)

	public String getCompany_img() {
		return this.company_img;
	}

	public void setCompany_img(String company_img) {
		this.company_img = company_img;
	}

	@Column(name = "member_code", length = 32)
	public String getMember_code() {
		return member_code;
	}

	public void setMember_code(String member_code) {
		this.member_code = member_code;
	}

	@Column(name = "wx_url", length = 500)
	public String getWx_url() {
		return wx_url;
	}

	public void setWx_url(String wx_url) {
		this.wx_url = wx_url;
	}

	@Column(name = "wx_url_code")
	public String getWx_url_code() {
		return wx_url_code;
	}

	public void setWx_url_code(String wx_url_code) {
		this.wx_url_code = wx_url_code;
	}

	@Column(name = "wx_url_time")
	public Long getWx_url_time() {
		return wx_url_time;
	}

	public void setWx_url_time(Long wx_url_time) {
		this.wx_url_time = wx_url_time;
	}

	@Column(name = "wx_company_url", length = 500)
	public String getWx_company_url() {
		return wx_company_url;
	}

	public void setWx_company_url(String wx_company_url) {
		this.wx_company_url = wx_company_url;
	}

	@Column(name = "wx_company_url_code")
	public String getWx_company_url_code() {
		return wx_company_url_code;
	}

	public void setWx_company_url_code(String wx_company_url_code) {
		this.wx_company_url_code = wx_company_url_code;
	}

	@Column(name = "wx_company_url_time")
	public Long getWx_company_url_time() {
		return wx_company_url_time;
	}

	public void setWx_company_url_time(Long wx_company_url_time) {
		this.wx_company_url_time = wx_company_url_time;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "saleMan")
	public Set<GivebiRcd> getGivebiRcds() {
		return this.givebiRcds;
	}

	public void setGivebiRcds(Set<GivebiRcd> givebiRcds) {
		this.givebiRcds = givebiRcds;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "saleMan")

	public Set<BiAsk> getBiAsks() {
		return this.biAsks;
	}

	public void setBiAsks(Set<BiAsk> biAsks) {
		this.biAsks = biAsks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "saleMan")

	public Set<Company> getCompanies() {
		return this.companies;
	}

	public void setCompanies(Set<Company> companies) {
		this.companies = companies;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "saleMan")

	public Set<CashRcd> getCashRcds() {
		return this.cashRcds;
	}

	public void setCashRcds(Set<CashRcd> cashRcds) {
		this.cashRcds = cashRcds;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "saleMan")

	public Set<ProfitAssign> getProfitAssigns() {
		return this.profitAssigns;
	}

	public void setProfitAssigns(Set<ProfitAssign> profitAssigns) {
		this.profitAssigns = profitAssigns;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "saleMan")

	public Set<AskSpreadCompany> getAskSpreadCompanies() {
		return this.askSpreadCompanies;
	}

	public void setAskSpreadCompanies(Set<AskSpreadCompany> askSpreadCompanies) {
		this.askSpreadCompanies = askSpreadCompanies;
	}

	@Override
	public Object getEntityId() {
		return this.sale_man_id;
	}

}