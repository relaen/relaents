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
 * Provider entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_company", catalog = "tiandian")
public class Company extends BasePojo implements java.io.Serializable {
	// Fields
	private Long company_id;
	private Area area;
	private CompanyClassify companyClassify;
	private String company_name;
	private String address;
	private String zip_code;
	private String fax_no;
	private Long regist_time;
	private Integer commodity_num = 0;
	private Integer satisfaction_degree = 100;
	private Integer new_commodity_num = 0;
	private Integer attention_num = 0;
	private Integer collection_num = 0;
	private Integer share_num = 0;
	private Integer auth_flag = 0;
	private Integer enabled = 0;
	private Integer off_line = 0; // 线下商家
	private Integer sale_num = 0; // 销售数量
	private Integer is_self = 0; // 自营
	private SaleMan saleMan;
	private Float longitude; // 经度
	private Float latitude; // 纬度
	private Long geohash;
	private String introduce;
	private String app_qrcode;
	private BankCard bankCard;
	private Integer is_show_in_app = 1; // 是否显示在app中
	private String adv_words; // 广告语
	private Set<CompanyCollection> companyCollections = new HashSet<CompanyCollection>(0);
	private Set<AttentionRcd> attentionRcds = new HashSet<AttentionRcd>(0);
	private Set<CompanyAuth> companyAuths = new HashSet<CompanyAuth>(0);
	private Set<CompanyBrandRel> companyBrandRels = new HashSet<CompanyBrandRel>(0);
	private Set<CompanyStatistic> companyStatistics = new HashSet<CompanyStatistic>(0);
	private Set<Adv> advs = new HashSet<Adv>(0);
	private Set<CompanyCheck> companyChecks = new HashSet<CompanyCheck>(0);
	private BussinessInfo bussinessInfo;
	private CompanyWallet companyWallet;
	private Set<Commodity> commodities = new HashSet<Commodity>(0);
	private Set<CompanyManager> companyManagers = new HashSet<CompanyManager>(0);
	private Set<Orders> orders = new HashSet<Orders>(0);
	private Set<CompanyBalance> companyBalances = new HashSet<CompanyBalance>(0);
	private Set<CompanyImg> companyImgs = new HashSet<CompanyImg>(0);
	private Set<CompanyCateRel> companyCateRels = new HashSet<CompanyCateRel>(0);

	// Constructors
	/** default constructor */
	public Company() {
	}

	/** minimal constructor */
	public Company(Long company_id, Long regist_time) {
		this.company_id = company_id;
		this.regist_time = regist_time;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "COMPANY_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COMPANY", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMPANY_GEN")
	@Column(name = "company_id", unique = true, nullable = false)
	public Long getCompany_id() {
		return this.company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
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
	@JoinColumn(name = "company_classify_id")
	public CompanyClassify getCompanyClassify() {
		return companyClassify;
	}

	public void setCompanyClassify(CompanyClassify companyClassify) {
		this.companyClassify = companyClassify;
	}

	@Column(name = "company_name", length = 50)
	public String getCompany_name() {
		return this.company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	@Column(name = "address", length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "zip_code", length = 6)
	public String getZip_code() {
		return this.zip_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	@Column(name = "fax_no", length = 15)
	public String getFax_no() {
		return this.fax_no;
	}

	public void setFax_no(String fax_no) {
		this.fax_no = fax_no;
	}

	@Column(name = "regist_time", nullable = false, length = 19)
	public Long getRegist_time() {
		return this.regist_time;
	}

	public void setRegist_time(Long regist_time) {
		this.regist_time = regist_time;
	}

	public void setGeohash(Long geoHash) {
		this.geohash = geoHash;
	}

	@Column(name = "geohash", nullable = false, length = 19)
	public Long getGeohash() {
		return geohash;
	}

	@Column(name = "commodity_num")
	public Integer getCommodity_num() {
		return this.commodity_num;
	}

	public void setCommodity_num(Integer commodity_num) {
		this.commodity_num = commodity_num;
	}

	@Column(name = "satisfaction_degree")
	public Integer getSatisfaction_degree() {
		return this.satisfaction_degree;
	}

	public void setSatisfaction_degree(Integer satisfaction_degree) {
		this.satisfaction_degree = satisfaction_degree;
	}

	@Column(name = "new_commodity_num")
	public Integer getNew_commodity_num() {
		return this.new_commodity_num;
	}

	public void setNew_commodity_num(Integer new_commodity_num) {
		this.new_commodity_num = new_commodity_num;
	}

	@Column(name = "attention_num")
	public Integer getAttention_num() {
		return this.attention_num;
	}

	public void setAttention_num(Integer attention_num) {
		this.attention_num = attention_num;
	}

	@Column(name = "collection_num")
	public Integer getCollection_num() {
		return this.collection_num;
	}

	public void setCollection_num(Integer collection_num) {
		this.collection_num = collection_num;
	}

	@Column(name = "app_qrcode")
	public String getApp_qrcode() {
		return app_qrcode;
	}

	public void setApp_qrcode(String app_qrcode) {
		this.app_qrcode = app_qrcode;
	}

	@Column(name = "share_num")
	public Integer getShare_num() {
		return this.share_num;
	}

	public void setShare_num(Integer share_num) {
		this.share_num = share_num;
	}

	@Column(name = "auth_flag")
	public Integer getAuth_flag() {
		return this.auth_flag;
	}

	public void setAuth_flag(Integer auth_flag) {
		this.auth_flag = auth_flag;
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

	@Column(name = "enabled")
	public Integer getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	@Column(name = "off_line")
	public Integer getOff_line() {
		return this.off_line;
	}

	public void setOff_line(Integer off_line) {
		this.off_line = off_line;
	}

	@Column(name = "sale_num")
	public Integer getSale_num() {
		return sale_num;
	}

	public void setSale_num(Integer sale_num) {
		this.sale_num = sale_num;
	}

	@Column(name = "introduce")
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	@Column(name = "is_self")
	public Integer getIs_self() {
		return is_self;
	}

	public void setIs_self(Integer is_self) {
		this.is_self = is_self;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sale_man_id")
	public SaleMan getSaleMan() {
		return saleMan;
	}

	public void setSaleMan(SaleMan saleMan) {
		this.saleMan = saleMan;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
	public Set<CompanyCollection> getCompanyCollections() {
		return this.companyCollections;
	}

	public void setCompanyCollections(Set<CompanyCollection> companyCollections) {
		this.companyCollections = companyCollections;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
	public Set<AttentionRcd> getAttentionRcds() {
		return this.attentionRcds;
	}

	public void setAttentionRcds(Set<AttentionRcd> attentionRcds) {
		this.attentionRcds = attentionRcds;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
	public Set<CompanyAuth> getCompanyAuths() {
		return this.companyAuths;
	}

	public void setCompanyAuths(Set<CompanyAuth> companyAuths) {
		this.companyAuths = companyAuths;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
	public Set<CompanyBrandRel> getCompanyBrandRels() {
		return this.companyBrandRels;
	}

	public void setCompanyBrandRels(Set<CompanyBrandRel> CompanyBrandRels) {
		this.companyBrandRels = companyBrandRels;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
	public Set<CompanyStatistic> getCompanyStatistics() {
		return this.companyStatistics;
	}

	public void setCompanyStatistics(Set<CompanyStatistic> companyStatistics) {
		this.companyStatistics = companyStatistics;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
	public Set<Adv> getAdvs() {
		return this.advs;
	}

	public void setAdvs(Set<Adv> advs) {
		this.advs = advs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
	public Set<CompanyCheck> getCompanyChecks() {
		return this.companyChecks;
	}

	public void setCompanyChecks(Set<CompanyCheck> companyChecks) {
		this.companyChecks = companyChecks;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "company")
	public BussinessInfo getBussinessInfo() {
		return this.bussinessInfo;
	}

	public void setBussinessInfo(BussinessInfo bussinessInfo) {
		this.bussinessInfo = bussinessInfo;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bank_card_id")
	public BankCard getBankCard() {
		return bankCard;
	}

	public void setBankCard(BankCard bankCard) {
		this.bankCard = bankCard;
	}

	@Column(name = "is_show_in_app")
	public Integer getIs_show_in_app() {
		return is_show_in_app;
	}

	public void setIs_show_in_app(Integer is_show_in_app) {
		this.is_show_in_app = is_show_in_app;
	}

	@Column(name = "adv_words")
	public String getAdv_words() {
		return adv_words;
	}

	public void setAdv_words(String adv_words) {
		this.adv_words = adv_words;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "company")
	public CompanyWallet getCompanyWallet() {
		return this.companyWallet;
	}

	public void setCompanyWallet(CompanyWallet companyWallet) {
		this.companyWallet = companyWallet;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
	public Set<Commodity> getCommodities() {
		return this.commodities;
	}

	public void setCommodities(Set<Commodity> commodities) {
		this.commodities = commodities;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
	public Set<CompanyManager> getCompanyManagers() {
		return companyManagers;
	}

	public void setCompanyManagers(Set<CompanyManager> companyManagers) {
		this.companyManagers = companyManagers;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
	public Set<Orders> getOrders() {
		return orders;
	}

	public void setOrders(Set<Orders> orders) {
		this.orders = orders;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
	public Set<CompanyBalance> getCompanyBalances() {
		return companyBalances;
	}

	public void setCompanyBalances(Set<CompanyBalance> companyBalances) {
		this.companyBalances = companyBalances;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
	public Set<CompanyImg> getCompanyImgs() {
		return companyImgs;
	}

	public void setCompanyImgs(Set<CompanyImg> companyImgs) {
		this.companyImgs = companyImgs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
	public Set<CompanyCateRel> getCompanyCateRels() {
		return companyCateRels;
	}

	public void setCompanyCateRels(Set<CompanyCateRel> companyCateRels) {
		this.companyCateRels = companyCateRels;
	}

	@Override
	public Object getEntityId() {
		return this.company_id;
	}
}