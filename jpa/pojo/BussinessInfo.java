package dao.pojo;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * BussinessInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_bussiness_info", catalog = "tiandian")

public class BussinessInfo extends BasePojo implements java.io.Serializable {

	// Fields

	private Long company_id;
	private Company company;
	private String manage_model;
	private String credit_code;
	private String organize_code;
	private String bank_deposit;
	private String enterprise_name;
	private String manage_circle;
	private String bussiness_area;
	private String residence;
	private String company_type;
	private String business_term;
	private String bank;
	private String bank_name;
	private String legal_man_area;
	private String legal_man_name;
	private String legal_man_idno;
	private String write_identity;
	private String legal_man_days;
	private String bank_area;
	private String bank_account;
	private Double regist_money;
	private String certificate_type;

	// Constructors

	/** default constructor */
	public BussinessInfo() {
	}

	/** minimal constructor */
	public BussinessInfo(Long company_id, Company company) {
		this.company_id = company_id;
		this.company = company;
	}

	/** full constructor */
	public BussinessInfo(Long company_id, Company company, String manage_model, String credit_code, String bank_deposit,
			String enterprise_name, String manage_circle, String bussiness_area, String residence, String company_type,
			String business_term, String bank_name, String legal_man_area, String legal_man_name, String legal_man_idno,
			String write_identity, String legal_man_days, String bank_area, String bank_account, Double regist_money,
			String certificate_type) {
		this.company_id = company_id;
		this.company = company;
		this.manage_model = manage_model;
		this.credit_code = credit_code;
		this.bank_deposit = bank_deposit;
		this.enterprise_name = enterprise_name;
		this.manage_circle = manage_circle;
		this.bussiness_area = bussiness_area;
		this.residence = residence;
		this.company_type = company_type;
		this.business_term = business_term;
		this.bank_name = bank_name;
		this.legal_man_area = legal_man_area;
		this.legal_man_name = legal_man_name;
		this.legal_man_idno = legal_man_idno;
		this.write_identity = write_identity;
		this.legal_man_days = legal_man_days;
		this.bank_area = bank_area;
		this.bank_account = bank_account;
		this.regist_money = regist_money;
		this.certificate_type = certificate_type;
	}

	// Property accessors
	@Id
	@Column(name = "company_id", unique = true, nullable = false)

	public Long getCompany_id() {
		return this.company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Column(name = "manage_model", length = 50)

	public String getManage_model() {
		return this.manage_model;
	}

	public void setManage_model(String manage_model) {
		this.manage_model = manage_model;
	}

	@Column(name = "credit_code", length = 50)

	public String getCredit_code() {
		return this.credit_code;
	}

	public void setCredit_code(String credit_code) {
		this.credit_code = credit_code;
	}

	@Column(name = "organize_code", length = 50)
	public String getOrganize_code() {
		return organize_code;
	}

	public void setOrganize_code(String organize_code) {
		this.organize_code = organize_code;
	}

	@Column(name = "bank_deposit", length = 50)

	public String getBank_deposit() {
		return this.bank_deposit;
	}

	public void setBank_deposit(String bank_deposit) {
		this.bank_deposit = bank_deposit;
	}

	@Column(name = "enterprise_name", length = 200)

	public String getEnterprise_name() {
		return this.enterprise_name;
	}

	public void setEnterprise_name(String enterprise_name) {
		this.enterprise_name = enterprise_name;
	}

	@Column(name = "manage_circle", length = 200)

	public String getManage_circle() {
		return this.manage_circle;
	}

	public void setManage_circle(String manage_circle) {
		this.manage_circle = manage_circle;
	}

	@Column(name = "bussiness_area", length = 200)

	public String getBussiness_area() {
		return this.bussiness_area;
	}

	public void setBussiness_area(String bussiness_area) {
		this.bussiness_area = bussiness_area;
	}

	@Column(name = "bank")
	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	@Column(name = "residence", length = 200)

	public String getResidence() {
		return this.residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}

	@Column(name = "company_type", length = 50)

	public String getCompany_type() {
		return this.company_type;
	}

	public void setCompany_type(String company_type) {
		this.company_type = company_type;
	}

	@Column(name = "business_term", length = 50)

	public String getBusiness_term() {
		return this.business_term;
	}

	public void setBusiness_term(String business_term) {
		this.business_term = business_term;
	}

	@Column(name = "bank_name", length = 50)

	public String getBank_name() {
		return this.bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	@Column(name = "legal_man_area", length = 200)

	public String getLegal_man_area() {
		return this.legal_man_area;
	}

	public void setLegal_man_area(String legal_man_area) {
		this.legal_man_area = legal_man_area;
	}

	@Column(name = "legal_man_name", length = 50)

	public String getLegal_man_name() {
		return this.legal_man_name;
	}

	public void setLegal_man_name(String legal_man_name) {
		this.legal_man_name = legal_man_name;
	}

	@Column(name = "legal_man_idno", length = 20)

	public String getLegal_man_idno() {
		return this.legal_man_idno;
	}

	public void setLegal_man_idno(String legal_man_idno) {
		this.legal_man_idno = legal_man_idno;
	}

	@Column(name = "write_identity", length = 50)

	public String getWrite_identity() {
		return this.write_identity;
	}

	public void setWrite_identity(String write_identity) {
		this.write_identity = write_identity;
	}

	@Column(name = "legal_man_days", length = 20)

	public String getLegal_man_days() {
		return this.legal_man_days;
	}

	public void setLegal_man_days(String legal_man_days) {
		this.legal_man_days = legal_man_days;
	}

	@Column(name = "bank_area", length = 50)

	public String getBank_area() {
		return this.bank_area;
	}

	public void setBank_area(String bank_area) {
		this.bank_area = bank_area;
	}

	@Column(name = "bank_account", length = 50)

	public String getBank_account() {
		return this.bank_account;
	}

	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}

	@Column(name = "regist_money", precision = 13)

	public Double getRegist_money() {
		return this.regist_money;
	}

	public void setRegist_money(Double regist_money) {
		this.regist_money = regist_money;
	}

	@Column(name = "certificate_type", length = 50)

	public String getCertificate_type() {
		return this.certificate_type;
	}

	public void setCertificate_type(String certificate_type) {
		this.certificate_type = certificate_type;
	}

	@Override
	public Object getEntityId() {
		return this.company_id;
	}
}