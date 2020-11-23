package dao.pojo;

import javax.persistence.TableGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
// default package
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ResourceAuthority entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_resource_authority", catalog = "tiandian")
public class ResourceAuthority extends BasePojo implements java.io.Serializable {
	// Fields
	private Long resource_authority_id;
	private Authority authority;
	private Resource resource;

	// Constructors
	/** default constructor */
	public ResourceAuthority() {
	}

	/** minimal constructor */
	public ResourceAuthority(Long resource_authority_id) {
		this.resource_authority_id = resource_authority_id;
	}

	/** full constructor */
	public ResourceAuthority(Long resource_authority_id, Authority authority, Resource resource) {
		this.resource_authority_id = resource_authority_id;
		this.authority = authority;
		this.resource = resource;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "RESOURCEAUTHORITY_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_RESOURCEAUTHORITY", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "RESOURCEAUTHORITY_GEN")
	@Column(name = "resource_authority_id", unique = true, nullable = false)
	public Long getResource_authority_id() {
		return this.resource_authority_id;
	}

	public void setResource_authority_id(Long resource_authority_id) {
		this.resource_authority_id = resource_authority_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "authority")
	public Authority getAuthority() {
		return this.authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "resource_id")
	public Resource getResource() {
		return this.resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	@Override
	public Object getEntityId() {
		return this.resource_authority_id;
	}
}