package dao.pojo;

// default package
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * Platform entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_platform", catalog = "tiandian")
public class Platform extends BasePojo implements java.io.Serializable {
	// Fields
	private Long platform_id;
	private String serve_tel;
	private String recommend_mobile;
	private String domain;
	private String domain_man;
	private String context;
	private String ip;

	private String regist_type;
	private String regist_company_type;
	private String givebi_type;
	private String regist_manager_type;

	private Integer fen_rate;

	// Constructors
	/** default constructor */
	public Platform() {
	}

	/** minimal constructor */
	public Platform(Long platform_id) {
		this.platform_id = platform_id;
	}

	/** full constructor */
	public Platform(Long platform_id, String serve_tel) {
		this.platform_id = platform_id;
		this.serve_tel = serve_tel;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "PLATFORM_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_PLATFORM", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PLATFORM_GEN")
	@Column(name = "platform_id", unique = true, nullable = false)
	public Long getPlatform_id() {
		return this.platform_id;
	}

	public void setPlatform_id(Long platform_id) {
		this.platform_id = platform_id;
	}

	@Column(name = "serve_tel", length = 15)
	public String getServe_tel() {
		return this.serve_tel;
	}

	public void setServe_tel(String serve_tel) {
		this.serve_tel = serve_tel;
	}

	@Column(name = "recommend_mobile", length = 11)
	public String getRecommend_mobile() {
		return recommend_mobile;
	}

	public void setRecommend_mobile(String recommend_mobile) {
		this.recommend_mobile = recommend_mobile;
	}

	@Column(name = "domain")
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Column(name = "domain_man")
	public String getDomain_man() {
		return domain_man;
	}

	public void setDomain_man(String domain) {
		this.domain_man = domain;
	}

	@Column(name = "context")
	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	@Column(name = "ip")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public Object getEntityId() {
		return this.platform_id;
	}

	@Column(name = "regist_type")
	public String getRegist_type() {
		return regist_type;
	}

	public void setRegist_type(String regist_type) {
		this.regist_type = regist_type;
	}

	@Column(name = "regist_company_type")
	public String getRegist_company_type() {
		return regist_company_type;
	}

	public void setRegist_company_type(String regist_company_type) {
		this.regist_company_type = regist_company_type;
	}

	@Column(name = "givebi_type")
	public String getGivebi_type() {
		return givebi_type;
	}

	public void setGivebi_type(String givebi_type) {
		this.givebi_type = givebi_type;
	}

	@Column(name = "regist_manager_type")
	public String getRegist_manager_type() {
		return regist_manager_type;
	}

	public void setRegist_manager_type(String regist_manager_type) {
		this.regist_manager_type = regist_manager_type;
	}

	@Column(name = "fen_rate")
	public Integer getFen_rate() {
		return fen_rate;
	}

	public void setFen_rate(Integer fen_rate) {
		this.fen_rate = fen_rate;
	}
}