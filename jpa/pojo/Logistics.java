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
 * Logistics entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_logistics", catalog = "tiandian")
public class Logistics extends BasePojo implements java.io.Serializable {
	// Fields
	private Long logistics_id;
	private String exp_name;
	private String simple_name;
	private String url;
	private String phone;
	private String img_url;
	private Integer enabled;
	
	private Set<PostInfo> postInfos = new HashSet<PostInfo>(0);

	// Constructors
	/** default constructor */
	public Logistics() {
	}

	/** minimal constructor */
	public Logistics(Long logistics_id) {
		this.logistics_id = logistics_id;
	}
	// Property accessors
	@Id
	@TableGenerator(name = "LOGISTICS_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_LOGISTICS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "LOGISTICS_GEN")
	@Column(name = "logistics_id", unique = true, nullable = false)
	public Long getLogistics_id() {
		return this.logistics_id;
	}

	public void setLogistics_id(Long logistics_id) {
		this.logistics_id = logistics_id;
	}


	@Column(name = "exp_name", length = 50)
	public String getExp_name() {
		return exp_name;
	}

	public void setExp_name(String exp_name) {
		this.exp_name = exp_name;
	}
	@Column(name = "simple_name", length = 50)
	public String getSimple_name() {
		return simple_name;
	}

	public void setSimple_name(String simple_name) {
		this.simple_name = simple_name;
	}

	@Column(name = "url", length = 50)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "phone", length = 50)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "img_url", length = 50)
	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	
	@Column(name = "enabled", length = 50)
	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "logistics")
	public Set<PostInfo> getPostInfos() {
		return this.postInfos;
	}
	
	public void setPostInfos(Set<PostInfo> postInfos) {
		this.postInfos = postInfos;
	}

	@Override
	public Object getEntityId() {
		return this.logistics_id;
	}
}