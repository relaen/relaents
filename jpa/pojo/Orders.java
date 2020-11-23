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
 * Orders entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_orders", catalog = "tiandian")
public class Orders extends BasePojo implements java.io.Serializable {
	// Fields
	private Long order_id;
	private OrderOther orderOther;
	private OrderState orderState;
	private Member member;
	private String flow_no;
	private String trade_no;
	private Double order_money = 0d;
	private Double pay_money;
	private Double final_money;
	private Double bi_money = 0d;
	private Double dian_yuan = 0d;
	private Double final_yuan = 0d;
	private Integer dian_bi = 0;
	private Integer final_dianbi = 0;
	private Integer dian_fen = 0;
	private Integer final_fen = 0;
	private Double post_fee = 0d;
	private Integer post_fee_type = 1; // 邮费类型 1现金 2点元 3点分
	private Long create_time;
	private Long finish_time;
	private Long pay_time;
	private Long dead_time;
	private String close_reason;
	private Integer has_taken_invoice = 0;
	private Long remind_time;
	private Integer company_delete = 0;
	private Integer purchaser_delete = 0;
	private String remarks;
	private Integer give_fen = 0;
	private Integer real_give_fen = 0;
	private Integer need_invoice = 0;
	private Integer may_return = 0;
	private Integer may_exchange = 0;
	private Integer is_balancecompany = 0; // 是否已结算给商家
	private Integer is_balancesupplier = 0; // 是否已结算给供应商
	private Integer post_type = 3; // 1自提 2同城 3快递
	private String pay_code; // 支付码
	private Integer offline_pay = 0; // 是否线下支付
	private Integer devide1 = 0; // 是否已完成第一次分配
	private Integer devide2 = 0; // 是否已完成第二次分配
	private Long assign_free_time = 0l; // 收益分配解冻时间
	private Integer is_return = 0; // 是否有退货
	private Integer client_pay = 0; // 客户端支付成功标志
	private OrderAddress orderAddress;
	private OrderInvoice orderInvoice;
	private Company company;
	private Warehouse warehouse;
	private Seckill seckill;
	private PromotionCommodity promotionCommodity;
	private String post_code_img;
	private Integer is_show_in_app = 1; // 是否显示在app中
	private Integer is_fen = 0; // 是否点分
	private Integer is_unfrozen = 0; // 是否解冻
	private Long auto_receive_time; // 自动确认收货时间
	private Integer is_first_extend_receive_time = 0; // 是否已经第一次延长了收货时间

	private Set<ReturnInfo> returnInfos = new HashSet<ReturnInfo>(0);
	private Set<OrderStateRcd> orderStateRcds = new HashSet<OrderStateRcd>(0);
	private Set<ProfitAssign> profitAssigns = new HashSet<ProfitAssign>(0);

	private Set<PostInfo> postInfos = new HashSet<PostInfo>(0);
	private Set<OrderCommodity> orderCommodities = new HashSet<OrderCommodity>(0);
	private Set<PayRcd> payRcds = new HashSet<PayRcd>(0);

	// Constructors
	/** default constructor */
	public Orders() {
	}

	/** minimal constructor */
	public Orders(Long order_id, Long create_time, Long finish_time, Long remind_time) {
		this.order_id = order_id;
		this.create_time = create_time;
		this.finish_time = finish_time;
		this.remind_time = remind_time;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "ORDERS_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_ORDERS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ORDERS_GEN")
	@Column(name = "order_id", unique = true, nullable = false)
	public Long getOrder_id() {
		return this.order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "orders")
	public OrderOther getOrderOther() {
		return orderOther;
	}

	public void setOrderOther(OrderOther orderOther) {
		this.orderOther = orderOther;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_state_id")
	public OrderState getOrderState() {
		return this.orderState;
	}

	public void setOrderState(OrderState orderState) {
		this.orderState = orderState;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Column(name = "flow_no", length = 50)
	public String getFlow_no() {
		return this.flow_no;
	}

	public void setFlow_no(String flow_no) {
		this.flow_no = flow_no;
	}

	@Column(name = "trade_no", length = 32)
	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	@Column(name = "order_money", precision = 13)
	public Double getOrder_money() {
		return this.order_money;
	}

	public void setOrder_money(Double order_money) {
		this.order_money = order_money;
	}

	@Column(name = "pay_money", precision = 13)
	public Double getPay_money() {
		return this.pay_money;
	}

	public void setPay_money(Double pay_money) {
		this.pay_money = pay_money;
	}

	@Column(name = "final_money", precision = 13)
	public Double getFinal_money() {
		return final_money;
	}

	public void setFinal_money(Double final_money) {
		this.final_money = final_money;
	}

	@Column(name = "dian_yuan", precision = 13)
	public Double getDian_yuan() {
		return this.dian_yuan;
	}

	public void setDian_yuan(Double dian_yuan) {
		this.dian_yuan = dian_yuan;
	}

	@Column(name = "final_yuan", precision = 13)
	public Double getFinal_yuan() {
		return final_yuan;
	}

	public void setFinal_yuan(Double final_yuan) {
		this.final_yuan = final_yuan;
	}

	@Column(name = "dian_bi")
	public Integer getDian_bi() {
		return this.dian_bi;
	}

	public void setDian_bi(Integer dian_bi) {
		this.dian_bi = dian_bi;
	}

	@Column(name = "final_dianbi")
	public Integer getFinal_dianbi() {
		return final_dianbi;
	}

	public void setFinal_dianbi(Integer final_dianbi) {
		this.final_dianbi = final_dianbi;
	}

	@Column(name = "dian_fen")
	public Integer getDian_fen() {
		return this.dian_fen;
	}

	public void setDian_fen(Integer dian_fen) {
		this.dian_fen = dian_fen;
	}

	@Column(name = "final_fen")
	public Integer getFinal_fen() {
		return final_fen;
	}

	public void setFinal_fen(Integer final_fen) {
		this.final_fen = final_fen;
	}

	@Column(name = "post_fee", precision = 13)
	public Double getPost_fee() {
		return this.post_fee;
	}

	public void setPost_fee(Double post_fee) {
		this.post_fee = post_fee;
	}

	@Column(name = "post_fee_type")
	public Integer getPost_fee_type() {
		return post_fee_type;
	}

	public void setPost_fee_type(Integer post_fee_type) {
		this.post_fee_type = post_fee_type;
	}

	@Column(name = "create_time")
	public Long getCreate_time() {
		return this.create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	@Column(name = "client_pay")
	public Integer getClient_pay() {
		return client_pay;
	}

	public void setClient_pay(Integer client_pay) {
		this.client_pay = client_pay;
	}

	@Column(name = "finish_time")
	public Long getFinish_time() {
		return this.finish_time;
	}

	public void setFinish_time(Long finish_time) {
		this.finish_time = finish_time;
	}

	@Column(name = "pay_time")
	public Long getPay_time() {
		return pay_time;
	}

	public void setPay_time(Long pay_time) {
		this.pay_time = pay_time;
	}

	@Column(name = "close_reason", length = 500)
	public String getClose_reason() {
		return this.close_reason;
	}

	public void setClose_reason(String close_reason) {
		this.close_reason = close_reason;
	}

	@Column(name = "has_taken_invoice")
	public Integer getHas_taken_invoice() {
		return this.has_taken_invoice;
	}

	public void setHas_taken_invoice(Integer has_taken_invoice) {
		this.has_taken_invoice = has_taken_invoice;
	}

	@Column(name = "remind_time", nullable = false, length = 19)
	public Long getRemind_time() {
		return this.remind_time;
	}

	public void setRemind_time(Long remind_time) {
		this.remind_time = remind_time;
	}

	@Column(name = "company_delete")
	public Integer getCompany_delete() {
		return this.company_delete;
	}

	public void setCompany_delete(Integer company_delete) {
		this.company_delete = company_delete;
	}

	@Column(name = "purchaser_delete")
	public Integer getPurchaser_delete() {
		return this.purchaser_delete;
	}

	public void setPurchaser_delete(Integer purchaser_delete) {
		this.purchaser_delete = purchaser_delete;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "orders")
	public OrderAddress getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(OrderAddress orderAddress) {
		this.orderAddress = orderAddress;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orders")
	public Set<ReturnInfo> getReturnInfos() {
		return returnInfos;
	}

	public void setReturnInfos(Set<ReturnInfo> returnInfos) {
		this.returnInfos = returnInfos;
	}

	@Column(name = "remarks", length = 500)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "give_fen")
	public Integer getGive_fen() {
		return this.give_fen;
	}

	public void setGive_fen(Integer give_fen) {
		this.give_fen = give_fen;
	}

	@Column(name = "need_invoice")
	public Integer getNeed_invoice() {
		return this.need_invoice;
	}

	public void setNeed_invoice(Integer need_invoice) {
		this.need_invoice = need_invoice;
	}

	@Column(name = "may_return")
	public Integer getMay_return() {
		return may_return;
	}

	public void setMay_return(Integer may_return) {
		this.may_return = may_return;
	}

	@Column(name = "may_exchange")
	public Integer getMay_exchange() {
		return may_exchange;
	}

	public void setMay_exchange(Integer may_exchange) {
		this.may_exchange = may_exchange;
	}

	@Column(name = "bi_money")
	public Double getBi_money() {
		return bi_money;
	}

	public void setBi_money(Double bi_money) {
		this.bi_money = bi_money;
	}

	@Column(name = "pay_code")
	public String getPay_code() {
		return pay_code;
	}

	public void setPay_code(String pay_code) {
		this.pay_code = pay_code;
	}

	@Column(name = "offline_pay")
	public Integer getOffline_pay() {
		return offline_pay;
	}

	public void setOffline_pay(Integer offline_pay) {
		this.offline_pay = offline_pay;
	}

	@Column(name = "post_type")
	public Integer getPost_type() {
		return post_type;
	}

	public void setPost_type(Integer post_type) {
		this.post_type = post_type;
	}

	@Column(name = "dead_time")
	public Long getDead_time() {
		return dead_time;
	}

	public void setDead_time(Long dead_time) {
		this.dead_time = dead_time;
	}

	@Column(name = "devide1")
	public Integer getDevide1() {
		return devide1;
	}

	public void setDevide1(Integer devide1) {
		this.devide1 = devide1;
	}

	@Column(name = "devide2")
	public Integer getDevide2() {
		return devide2;
	}

	public void setDevide2(Integer devide2) {
		this.devide2 = devide2;
	}

	@Column(name = "assign_free_time")
	public Long getAssign_free_time() {
		return assign_free_time;
	}

	public void setAssign_free_time(Long assign_free_time) {
		this.assign_free_time = assign_free_time;
	}

	@Column(name = "is_return")
	public Integer getIs_return() {
		return is_return;
	}

	public void setIs_return(Integer is_return) {
		this.is_return = is_return;
	}

	@Column(name = "is_balancecompany")
	public Integer getIs_balancecompany() {
		return is_balancecompany;
	}

	public void setIs_balancecompany(Integer is_balancecompany) {
		this.is_balancecompany = is_balancecompany;
	}

	@Column(name = "is_balancesupplier")
	public Integer getIs_balancesupplier() {
		return is_balancesupplier;
	}

	public void setIs_balancesupplier(Integer is_balancesupplier) {
		this.is_balancesupplier = is_balancesupplier;
	}

	@Column(name = "real_give_fen")
	public Integer getReal_give_fen() {
		return real_give_fen;
	}

	public void setReal_give_fen(Integer real_give_fen) {
		this.real_give_fen = real_give_fen;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "warehouse_id")
	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seckill_id")
	public Seckill getSeckill() {
		return seckill;
	}

	public void setSeckill(Seckill seckill) {
		this.seckill = seckill;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "promotion_commodity_id")
	public PromotionCommodity getPromotionCommodity() {
		return promotionCommodity;
	}

	public void setPromotionCommodity(PromotionCommodity promotionCommodity) {
		this.promotionCommodity = promotionCommodity;
	}

	@Column(name = "post_code_img", length = 500)
	public String getPost_code_img() {
		return post_code_img;
	}

	public void setPost_code_img(String post_code_img) {
		this.post_code_img = post_code_img;
	}

	@Column(name = "is_show_in_app")
	public Integer getIs_show_in_app() {
		return is_show_in_app;
	}

	public void setIs_show_in_app(Integer is_show_in_app) {
		this.is_show_in_app = is_show_in_app;
	}

	@Column(name = "is_fen")
	public Integer getIs_fen() {
		return is_fen;
	}

	public void setIs_fen(Integer is_fen) {
		this.is_fen = is_fen;
	}

	@Column(name = "is_unfrozen")
	public Integer getIs_unfrozen() {
		return is_unfrozen;
	}

	public void setIs_unfrozen(Integer is_unfrozen) {
		this.is_unfrozen = is_unfrozen;
	}

	@Column(name = "auto_receive_time")
	public Long getAuto_receive_time() {
		return auto_receive_time;
	}

	public void setAuto_receive_time(Long auto_receive_time) {
		this.auto_receive_time = auto_receive_time;
	}

	@Column(name = "is_first_extend_receive_time")
	public Integer getIs_first_extend_receive_time() {
		return is_first_extend_receive_time;
	}

	public void setIs_first_extend_receive_time(Integer is_first_extend_receive_time) {
		this.is_first_extend_receive_time = is_first_extend_receive_time;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orders")
	public Set<OrderStateRcd> getOrderStateRcds() {
		return this.orderStateRcds;
	}

	public void setOrderStateRcds(Set<OrderStateRcd> orderStateRcds) {
		this.orderStateRcds = orderStateRcds;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orders")
	public Set<ProfitAssign> getProfitAssigns() {
		return this.profitAssigns;
	}

	public void setProfitAssigns(Set<ProfitAssign> profitAssigns) {
		this.profitAssigns = profitAssigns;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "orders")
	public OrderInvoice getOrderInvoice() {
		return orderInvoice;
	}

	public void setOrderInvoice(OrderInvoice orderInvoice) {
		this.orderInvoice = orderInvoice;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orders")
	public Set<PostInfo> getPostInfos() {
		return this.postInfos;
	}

	public void setPostInfos(Set<PostInfo> postInfos) {
		this.postInfos = postInfos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orders")
	public Set<OrderCommodity> getOrderCommodities() {
		return this.orderCommodities;
	}

	public void setOrderCommodities(Set<OrderCommodity> orderCommodities) {
		this.orderCommodities = orderCommodities;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orders")
	public Set<PayRcd> getPayRcds() {
		return payRcds;
	}

	public void setPayRcds(Set<PayRcd> payRcds) {
		this.payRcds = payRcds;
	}

	@Override
	public Object getEntityId() {
		return this.order_id;
	}
}