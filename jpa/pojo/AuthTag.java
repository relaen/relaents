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
 * AuthTag entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_auth_tag", catalog = "tiandian")
public class AuthTag extends BasePojo implements java.io.Serializable {
	// Fields
	private Long auth_tag_id;
	private String auth_tag_name;
	private String tag_img;
	private String remarks;
	private Set<CompanyAuth> companyAuths = new HashSet<CompanyAuth>(0);

	// Constructors
	/** default constructor */
	public AuthTag() {
	}

	/** minimal constructor */
	public AuthTag(Long auth_tag_id) {
		this.auth_tag_id = auth_tag_id;
	}

	/** full constructor */
	public AuthTag(Long auth_tag_id, String auth_tag_name, String tag_img, String remarks,
			Set<CompanyAuth> companyAuths) {
		this.auth_tag_id = auth_tag_id;
		this.auth_tag_name = auth_tag_name;
		this.tag_img = tag_img;
		this.remarks = remarks;
		this.companyAuths = companyAuths;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "AUTHTAG_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_AUTHTAG", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "AUTHTAG_GEN")
	@Column(name = "auth_tag_id", unique = true, nullable = false)
	public Long getAuth_tag_id() {
		return this.auth_tag_id;
	}

	public void setAuth_tag_id(Long auth_tag_id) {
		this.auth_tag_id = auth_tag_id;
	}

	@Column(name = "auth_tag_name", length = 50)
	public String getAuth_tag_name() {
		return this.auth_tag_name;
	}

	public void setAuth_tag_name(String auth_tag_name) {
		this.auth_tag_name = auth_tag_name;
	}

	@Column(name = "tag_img", length = 500)
	public String getTag_img() {
		return this.tag_img;
	}

	public void setTag_img(String tag_img) {
		this.tag_img = tag_img;
	}

	@Column(name = "remarks", length = 200)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "authTag")
	public Set<CompanyAuth> getCompanyAuths() {
		return this.companyAuths;
	}

	public void setCompanyAuths(Set<CompanyAuth> companyAuths) {
		this.companyAuths = companyAuths;
	}

	@Override
	public Object getEntityId() {
		return this.auth_tag_id;
	}
}