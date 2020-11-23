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
 * ResourceType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_resource_type", catalog = "tiandian")
public class ResourceType extends BasePojo implements java.io.Serializable {
	// Fields
	private Long resource_type_id;
	private String type_name;
	private Set<Resource> resources = new HashSet<Resource>(0);

	// Constructors
	/** default constructor */
	public ResourceType() {
	}

	/** minimal constructor */
	public ResourceType(Long resource_type_id) {
		this.resource_type_id = resource_type_id;
	}

	/** full constructor */
	public ResourceType(Long resource_type_id, String type_name, Set<Resource> resources) {
		this.resource_type_id = resource_type_id;
		this.type_name = type_name;
		this.resources = resources;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "RESOURCETYPE_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_RESOURCETYPE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "RESOURCETYPE_GEN")
	@Column(name = "resource_type_id", unique = true, nullable = false)
	public Long getResource_type_id() {
		return this.resource_type_id;
	}

	public void setResource_type_id(Long resource_type_id) {
		this.resource_type_id = resource_type_id;
	}

	@Column(name = "type_name", length = 50)
	public String getType_name() {
		return this.type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "resourceType")
	public Set<Resource> getResources() {
		return this.resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	@Override
	public Object getEntityId() {
		return this.resource_type_id;
	}
}