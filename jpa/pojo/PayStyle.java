package dao.pojo;

import javax.persistence.TableGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
// default package
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * PayStyle entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_pay_style", catalog = "tiandian")
public class PayStyle extends BasePojo implements java.io.Serializable {
	// Fields
	private Long pay_style_id;
	private String pay_style_name;
	private String simple_name;
	private String pay_method;
	private String pay_class;
	private String app_pay_class;
	private Integer enabled;
	private Integer need_account;
	private String img_url;
	
	private Set<PayRcd> payRcds = new HashSet<PayRcd>(0);
	private Set<PayAccount> payAccounts = new HashSet<PayAccount>(0);
	// Constructors
	/** default constructor */
	public PayStyle() {
	}

	/** minimal constructor */
	public PayStyle(Long pay_style_id) {
		this.pay_style_id = pay_style_id;
	}

	/** full constructor */
	public PayStyle(Long pay_style_id, String pay_style_name, Set<CashRcd> cashRcds, Set<PayRcd> payRcds) {
		this.pay_style_id = pay_style_id;
		this.pay_style_name = pay_style_name;
		this.payRcds = payRcds;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "PAYSTYLE_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_PAYSTYLE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PAYSTYLE_GEN")
	@Column(name = "pay_style_id", unique = true, nullable = false)
	public Long getPay_style_id() {
		return this.pay_style_id;
	}

	public void setPay_style_id(Long pay_style_id) {
		this.pay_style_id = pay_style_id;
	}

	@Column(name = "pay_style_name", length = 50)
	public String getPay_style_name() {
		return this.pay_style_name;
	}

	public void setPay_style_name(String pay_style_name) {
		this.pay_style_name = pay_style_name;
	}
	
	
	@Column(name = "simple_name", length = 50)
	public String getSimple_name() {
		return simple_name;
	}

	public void setSimple_name(String simple_name) {
		this.simple_name = simple_name;
	}

	@Column(name = "pay_method", length = 50)
	public String getPay_method() {
		return pay_method;
	}

	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}

	@Column(name = "pay_class", length = 50)	
	public String getPay_class() {
		return pay_class;
	}

	public void setPay_class(String pay_class) {
		this.pay_class = pay_class;
	}

	@Column(name = "app_pay_class", length = 20)
	public String getApp_pay_class() {
		return app_pay_class;
	}

	public void setApp_pay_class(String app_pay_class) {
		this.app_pay_class = app_pay_class;
	}

	@Column(name = "enabled")
	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	@Column(name = "need_account", length = 50)
	public Integer getNeed_account() {
		return need_account;
	}

	public void setNeed_account(Integer need_account) {
		this.need_account = need_account;
	}
	
	@Column(name = "img_url", length = 500)
	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "payStyle")
	public Set<PayRcd> getPayRcds() {
		return this.payRcds;
	}

	public void setPayRcds(Set<PayRcd> payRcds) {
		this.payRcds = payRcds;
	}
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "payStyle")
	public Set<PayAccount> getPayAccounts() {
		return payAccounts;
	}

	public void setPayAccounts(Set<PayAccount> payAccounts) {
		this.payAccounts = payAccounts;
	}

	@Override
	public Object getEntityId() {
		return this.pay_style_id;
	}
}