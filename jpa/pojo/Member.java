package dao.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
// default package
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Member entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_member", catalog = "tiandian")
public class Member extends BasePojo implements java.io.Serializable {
	// Fields
	private Long member_id;
	private User user;
	private GsMember gsMember;
	private MemberRightRatio memberRightRatio;
	private SpreadMember spreadMember;
	private UserLevel userLevel;
	private Label label;
	private String head_img;
	private String nick_name;
	private Long birthday;
	private Integer sex;
	private Long regist_time;
	private String email;
	private String real_name;
	private String id_no;
	private String idno_imgs;
	private String mobile;
	private String tel;
	private Integer enabled = 1;
	private String level_date;
	private Integer grow_value = 0;
	private Integer on_cash_grow = 0;
	private Integer off_cash_grow = 0;
	private Integer on_yuan_grow = 0;
	private Integer off_yuan_grow = 0;
	private Integer on_fen_grow = 0;
	private Integer rf_grow = 0;
	private Integer cost_grow = 0;
	private Integer login_grow = 0;
	private Wallet wallet;
	private Integer continue_signin_days = 1;
	private SaleMan saleMan;
	private SaleMan companySpreader;
	private SaleMan managerSpreader;
	private Integer is_saleman = 0;
	private Integer is_manager = 0;
	private Integer is_company_manager = 0;
	private String openid;
	private Integer is_profit_no_fen = 0; // 是否发放无点分佣金
	private String spread_activity_member_no; // 注册时输入的推广活动号
	private Set<CommodityCollection> commodityCollections = new HashSet<CommodityCollection>(0);
	private Set<GrowRcd> growRcds = new HashSet<GrowRcd>(0);
	private Set<Address> addresses = new HashSet<Address>(0);
	private Set<MemberMessage> memberMessages = new HashSet<MemberMessage>(0);
	private Set<Invoice> invoices = new HashSet<Invoice>(0);
	private Set<CompanyCollection> companyCollections = new HashSet<CompanyCollection>(0);
	private Set<AttentionRcd> attentionRcds = new HashSet<AttentionRcd>(0);
	private Set<Signin> signins = new HashSet<Signin>(0);
	private Set<Orders> orderses = new HashSet<Orders>(0);
	private Set<ShoppingCart> shoppingCarts = new HashSet<ShoppingCart>(0);
	private Set<CompanyManager> companyManagers = new HashSet<CompanyManager>(0);
	private Set<GrowTime> growTimes = new HashSet<GrowTime>(0);
	private Set<GivebiRcd> givebiRcds = new HashSet<GivebiRcd>(0);
	private Set<BeSaleman> beSalemans = new HashSet<BeSaleman>(0);
	private Set<BeSaleman> beChecks = new HashSet<BeSaleman>(0);
	private Set<BiAsk> biAskChecks = new HashSet<BiAsk>(0);
	private Set<PayAccount> payAccounts = new HashSet<PayAccount>(0);
	private Set<Feedback> feedbacks = new HashSet<Feedback>(0);
	private Set<CashRcd> CashRcds = new HashSet<CashRcd>(0);
	private Set<AskSpreadCompany> askSpreadCompanies = new HashSet<AskSpreadCompany>(0);
	private Set<CompanyCheck> companyAsks = new HashSet<CompanyCheck>(0);
	private Set<CompanyCheck> companyChecks = new HashSet<CompanyCheck>(0);
	private Set<CommodityCheck> commodityChecks = new HashSet<CommodityCheck>(0);

	// Constructors
	/** default constructor */
	public Member() {
	}

	// Property accessors
	@Id
	@Column(name = "member_id", unique = true, nullable = false)
	public Long getMember_id() {
		return this.member_id;
	}

	public void setMember_id(Long member_id) {
		this.member_id = member_id;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "member")
	public GsMember getGsMember() {
		return gsMember;
	}

	public void setGsMember(GsMember gsMember) {
		this.gsMember = gsMember;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "member")
	public MemberRightRatio getMemberRightRatio() {
		return memberRightRatio;
	}

	public void setMemberRightRatio(MemberRightRatio memberRightRatio) {
		this.memberRightRatio = memberRightRatio;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "member")
	public SpreadMember getSpreadMember() {
		return spreadMember;
	}

	public void setSpreadMember(SpreadMember spreadMember) {
		this.spreadMember = spreadMember;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_level_id")
	public UserLevel getUserLevel() {
		return this.userLevel;
	}

	public void setUserLevel(UserLevel userLevel) {
		this.userLevel = userLevel;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "label_id")
	public Label getLabel() {
		return this.label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	@Column(name = "head_img", length = 500)
	public String getHead_img() {
		return this.head_img;
	}

	public void setHead_img(String head_img) {
		this.head_img = head_img;
	}

	@Column(name = "nick_name", length = 50)
	public String getNick_name() {
		return this.nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	@Column(name = "regist_time", length = 50)
	public Long getRegist_time() {
		return regist_time;
	}

	public void setRegist_time(Long regist_time) {
		this.regist_time = regist_time;
	}

	@Column(name = "birthday")
	public Long getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Long birthday) {
		this.birthday = birthday;
	}

	@Column(name = "sex")
	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "real_name", length = 50)
	public String getReal_name() {
		return this.real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	@Column(name = "id_no", length = 20)
	public String getId_no() {
		return this.id_no;
	}

	public void setId_no(String id_no) {
		this.id_no = id_no;
	}

	@Column(name = "is_manager")
	public Integer getIs_manager() {
		return is_manager;
	}

	public void setIs_manager(Integer is_manager) {
		this.is_manager = is_manager;
	}

	@Column(name = "is_saleman")
	public Integer getIs_saleman() {
		return is_saleman;
	}

	public void setIs_saleman(Integer is_saleman) {
		this.is_saleman = is_saleman;
	}

	@Column(name = "is_company_manager")
	public Integer getIs_company_manager() {
		return is_company_manager;
	}

	public void setIs_company_manager(Integer is_company_manager) {
		this.is_company_manager = is_company_manager;
	}

	@Column(name = "openid", length = 64)
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Column(name = "mobile", length = 11)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "tel", length = 15)
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "enabled")
	public Integer getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	@Column(name = "level_date", length = 8)
	public String getLevel_date() {
		return this.level_date;
	}

	public void setLevel_date(String level_date) {
		this.level_date = level_date;
	}

	@Column(name = "grow_value")
	public Integer getGrow_value() {
		return this.grow_value;
	}

	public void setGrow_value(Integer grow_value) {
		this.grow_value = grow_value;
	}

	@Column(name = "on_cash_grow")
	public Integer getOn_cash_grow() {
		return this.on_cash_grow;
	}

	public void setOn_cash_grow(Integer on_cash_grow) {
		this.on_cash_grow = on_cash_grow;
	}

	@Column(name = "off_cash_grow")
	public Integer getOff_cash_grow() {
		return this.off_cash_grow;
	}

	public void setOff_cash_grow(Integer off_cash_grow) {
		this.off_cash_grow = off_cash_grow;
	}

	@Column(name = "on_yuan_grow")
	public Integer getOn_yuan_grow() {
		return this.on_yuan_grow;
	}

	public void setOn_yuan_grow(Integer on_yuan_grow) {
		this.on_yuan_grow = on_yuan_grow;
	}

	@Column(name = "off_yuan_grow")
	public Integer getOff_yuan_grow() {
		return this.off_yuan_grow;
	}

	public void setOff_yuan_grow(Integer off_yuan_grow) {
		this.off_yuan_grow = off_yuan_grow;
	}

	@Column(name = "on_fen_grow")
	public Integer getOn_fen_grow() {
		return this.on_fen_grow;
	}

	public void setOn_fen_grow(Integer on_fen_grow) {
		this.on_fen_grow = on_fen_grow;
	}

	@Column(name = "rf_grow")
	public Integer getRf_grow() {
		return this.rf_grow;
	}

	public void setRf_grow(Integer rf_grow) {
		this.rf_grow = rf_grow;
	}

	@Column(name = "cost_grow")
	public Integer getCost_grow() {
		return this.cost_grow;
	}

	public void setCost_grow(Integer cost_grow) {
		this.cost_grow = cost_grow;
	}

	@Column(name = "idno_imgs")
	public String getIdno_imgs() {
		return idno_imgs;
	}

	public void setIdno_imgs(String idno_imgs) {
		this.idno_imgs = idno_imgs;
	}

	@Column(name = "login_grow")
	public Integer getLogin_grow() {
		return this.login_grow;
	}

	public void setLogin_grow(Integer login_grow) {
		this.login_grow = login_grow;
	}

	@Column(name = "continue_signin_days")
	public Integer getContinue_signin_days() {
		return this.continue_signin_days;
	}

	public void setContinue_signin_days(Integer continue_signin_days) {
		this.continue_signin_days = continue_signin_days;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
	public Set<CompanyManager> getCompanyManagers() {
		return companyManagers;
	}

	public void setCompanyManagers(Set<CompanyManager> companyManagers) {
		this.companyManagers = companyManagers;
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
	@JoinColumn(name = "company_spreader_id")
	public SaleMan getCompanySpreader() {
		return companySpreader;
	}

	public void setCompanySpreader(SaleMan companySpreader) {
		this.companySpreader = companySpreader;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manager_spreader_id")
	public SaleMan getManagerSpreader() {
		return managerSpreader;
	}

	public void setManagerSpreader(SaleMan managerSpreader) {
		this.managerSpreader = managerSpreader;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "member")
	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	@Column(name = "is_profit_no_fen")
	public Integer getIs_profit_no_fen() {
		return is_profit_no_fen;
	}

	public void setIs_profit_no_fen(Integer is_profit_no_fen) {
		this.is_profit_no_fen = is_profit_no_fen;
	}

	@Column(name = "spread_activity_member_no")
	public String getSpread_activity_member_no() {
		return spread_activity_member_no;
	}

	public void setSpread_activity_member_no(String spread_activity_member_no) {
		this.spread_activity_member_no = spread_activity_member_no;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
	public Set<CommodityCollection> getCommodityCollections() {
		return this.commodityCollections;
	}

	public void setCommodityCollections(Set<CommodityCollection> commodityCollections) {
		this.commodityCollections = commodityCollections;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
	public Set<GrowRcd> getGrowRcds() {
		return this.growRcds;
	}

	public void setGrowRcds(Set<GrowRcd> growRcds) {
		this.growRcds = growRcds;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
	public Set<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
	public Set<MemberMessage> getMemberMessages() {
		return memberMessages;
	}
	
	public void setMemberMessages(Set<MemberMessage> memberMessages) {
		this.memberMessages = memberMessages;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
	public Set<Invoice> getInvoices() {
		return this.invoices;
	}

	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
	public Set<CompanyCollection> getCompanyCollections() {
		return this.companyCollections;
	}

	public void setCompanyCollections(Set<CompanyCollection> companyCollections) {
		this.companyCollections = companyCollections;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
	public Set<AttentionRcd> getAttentionRcds() {
		return this.attentionRcds;
	}

	public void setAttentionRcds(Set<AttentionRcd> attentionRcds) {
		this.attentionRcds = attentionRcds;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
	public Set<Signin> getSignins() {
		return this.signins;
	}

	public void setSignins(Set<Signin> signins) {
		this.signins = signins;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
	public Set<Orders> getOrderses() {
		return this.orderses;
	}

	public void setOrderses(Set<Orders> orderses) {
		this.orderses = orderses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
	public Set<ShoppingCart> getShoppingCarts() {
		return this.shoppingCarts;
	}

	public void setShoppingCarts(Set<ShoppingCart> shoppingCarts) {
		this.shoppingCarts = shoppingCarts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
	public Set<GrowTime> getGrowTimes() {
		return growTimes;
	}

	public void setGrowTimes(Set<GrowTime> growTimes) {
		this.growTimes = growTimes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
	public Set<GivebiRcd> getGivebiRcds() {
		return givebiRcds;
	}

	public void setGivebiRcds(Set<GivebiRcd> givebiRcds) {
		this.givebiRcds = givebiRcds;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
	public Set<BeSaleman> getBeSalemans() {
		return beSalemans;
	}

	public void setBeSalemans(Set<BeSaleman> beSalemans) {
		this.beSalemans = beSalemans;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "checker")
	public Set<BeSaleman> getBeChecks() {
		return beChecks;
	}

	public void setBeChecks(Set<BeSaleman> beChecks) {
		this.beChecks = beChecks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "checker")

	public Set<BiAsk> getBiAskChecks() {
		return biAskChecks;
	}

	public void setBiAskChecks(Set<BiAsk> biAskChecks) {
		this.biAskChecks = biAskChecks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
	public Set<PayAccount> getPayAccounts() {
		return payAccounts;
	}

	public void setPayAccounts(Set<PayAccount> payAccounts) {
		this.payAccounts = payAccounts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
	public Set<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(Set<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "checker")
	public Set<CashRcd> getCashRcds() {
		return CashRcds;
	}

	public void setCashRcds(Set<CashRcd> cashRcds) {
		CashRcds = cashRcds;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
	public Set<AskSpreadCompany> getAskSpreadCompanies() {
		return askSpreadCompanies;
	}

	public void setAskSpreadCompanies(Set<AskSpreadCompany> askSpreadCompanies) {
		this.askSpreadCompanies = askSpreadCompanies;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "checker")
	public Set<CompanyCheck> getCompanyChecks() {
		return companyChecks;
	}

	public void setCompanyChecks(Set<CompanyCheck> companyChecks) {
		this.companyChecks = companyChecks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
	public Set<CompanyCheck> getCompanyAsks() {
		return companyAsks;
	}

	public void setCompanyAsks(Set<CompanyCheck> companyAsks) {
		this.companyAsks = companyAsks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
	public Set<CommodityCheck> getCommodityChecks() {
		return commodityChecks;
	}

	public void setCommodityChecks(Set<CommodityCheck> commodityChecks) {
		this.commodityChecks = commodityChecks;
	}

	@Override
	public Object getEntityId() {
		return this.member_id;
	}
}