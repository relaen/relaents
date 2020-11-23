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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Resource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_resource", catalog = "tiandian")
public class Resource extends BasePojo implements java.io.Serializable {
	// Fields
	private Long resource_id;
	private ResourceType resourceType;
	private String url;
	private String title;
	private Set<ResourceAuthority> resourceAuthorities = new HashSet<ResourceAuthority>(0);

	// Constructors
	/** default constructor */
	public Resource() {
	}

	/** minimal constructor */
	public Resource(Long resource_id, String url) {
		this.resource_id = resource_id;
		this.url = url;
	}

	/** full constructor */
	public Resource(Long resource_id, ResourceType resourceType, String url, String title,
			Set<ResourceAuthority> resourceAuthorities) {
		this.resource_id = resource_id;
		this.resourceType = resourceType;
		this.url = url;
		this.title = title;
		this.resourceAuthorities = resourceAuthorities;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "RESOURCE_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_RESOURCE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "RESOURCE_GEN")
	@Column(name = "resource_id", unique = true, nullable = false)
	public Long getResource_id() {
		return this.resource_id;
	}

	public void setResource_id(Long resource_id) {
		this.resource_id = resource_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "resource_type_id")
	public ResourceType getResourceType() {
		return this.resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	@Column(name = "url", nullable = false, length = 500)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "title", length = 50)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "resource")
	public Set<ResourceAuthority> getResourceAuthorities() {
		return this.resourceAuthorities;
	}

	public void setResourceAuthorities(Set<ResourceAuthority> resourceAuthorities) {
		this.resourceAuthorities = resourceAuthorities;
	}

	@Override
	public Object getEntityId() {
		return this.resource_id;
	}
}