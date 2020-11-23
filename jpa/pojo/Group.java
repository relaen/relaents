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
 * Group entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_group", catalog = "tiandian")
public class Group extends BasePojo implements java.io.Serializable {
	// Fields
	private Long group_id;
	private String group_name;
	private String remarks;
	private Set<GroupMenu> groupMenus = new HashSet<GroupMenu>(0);
	private Set<GroupAuthority> groupAuthorities = new HashSet<GroupAuthority>(0);
	private Set<GroupMember> groupMembers = new HashSet<GroupMember>(0);

	// Constructors
	/** default constructor */
	public Group() {
	}

	/** minimal constructor */
	public Group(Long group_id, String group_name) {
		this.group_id = group_id;
		this.group_name = group_name;
	}

	/** full constructor */
	public Group(Long group_id, String group_name, String remarks, Set<GroupMenu> groupMenus,
			Set<GroupAuthority> groupAuthorities, Set<GroupMember> groupMembers) {
		this.group_id = group_id;
		this.group_name = group_name;
		this.remarks = remarks;
		this.groupMenus = groupMenus;
		this.groupAuthorities = groupAuthorities;
		this.groupMembers = groupMembers;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "GROUP_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_GROUP", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GROUP_GEN")
	@Column(name = "group_id", unique = true, nullable = false)
	public Long getGroup_id() {
		return this.group_id;
	}

	public void setGroup_id(Long group_id) {
		this.group_id = group_id;
	}

	@Column(name = "group_name", nullable = false, length = 50)
	public String getGroup_name() {
		return this.group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	@Column(name = "remarks", length = 200)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
	public Set<GroupMenu> getGroupMenus() {
		return this.groupMenus;
	}

	public void setGroupMenus(Set<GroupMenu> groupMenus) {
		this.groupMenus = groupMenus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
	public Set<GroupAuthority> getGroupAuthorities() {
		return this.groupAuthorities;
	}

	public void setGroupAuthorities(Set<GroupAuthority> groupAuthorities) {
		this.groupAuthorities = groupAuthorities;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
	public Set<GroupMember> getGroupMembers() {
		return this.groupMembers;
	}

	public void setGroupMembers(Set<GroupMember> groupMembers) {
		this.groupMembers = groupMembers;
	}

	@Override
	public Object getEntityId() {
		return this.group_id;
	}
}