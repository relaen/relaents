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
 * Authority entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_authority", catalog = "tiandian")
public class Authority extends BasePojo implements java.io.Serializable {
	// Fields
	private String authority;
	private String name;
	private Set<GroupAuthority> groupAuthorities = new HashSet<GroupAuthority>(0);
	private Set<ResourceAuthority> resourceAuthorities = new HashSet<ResourceAuthority>(0);

	// Constructors
	/** default constructor */
	public Authority() {
	}

	/** minimal constructor */
	public Authority(String authority) {
		this.authority = authority;
	}

	/** full constructor */
	public Authority(String authority, Set<GroupAuthority> groupAuthorities,
			Set<ResourceAuthority> resourceAuthorities) {
		this.authority = authority;
		this.groupAuthorities = groupAuthorities;
		this.resourceAuthorities = resourceAuthorities;
	}

	// Property accessors
	@Id
	@Column(name = "authority", unique = true, nullable = false, length = 50)
	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	@Column(name = "name", length = 50)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "authority")
	public Set<GroupAuthority> getGroupAuthorities() {
		return this.groupAuthorities;
	}

	public void setGroupAuthorities(Set<GroupAuthority> groupAuthorities) {
		this.groupAuthorities = groupAuthorities;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "authority")
	public Set<ResourceAuthority> getResourceAuthorities() {
		return this.resourceAuthorities;
	}

	public void setResourceAuthorities(Set<ResourceAuthority> resourceAuthorities) {
		this.resourceAuthorities = resourceAuthorities;
	}

	@Override
	public Object getEntityId() {
		return this.authority;
	}
}