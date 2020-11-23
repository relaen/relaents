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
 * GroupAuthority entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_group_authority", catalog = "tiandian")
public class GroupAuthority extends BasePojo implements java.io.Serializable {
	// Fields
	private Long group_authority_id;
	private Group group;
	private Authority authority;

	// Constructors
	/** default constructor */
	public GroupAuthority() {
	}

	/** minimal constructor */
	public GroupAuthority(Long group_authority_id, Authority authority) {
		this.group_authority_id = group_authority_id;
		this.authority = authority;
	}

	/** full constructor */
	public GroupAuthority(Long group_authority_id, Group group, Authority authority) {
		this.group_authority_id = group_authority_id;
		this.group = group;
		this.authority = authority;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "GROUPAUTHORITY_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_GROUPAUTHORITY", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GROUPAUTHORITY_GEN")
	@Column(name = "group_authority_id", unique = true, nullable = false)
	public Long getGroup_authority_id() {
		return this.group_authority_id;
	}

	public void setGroup_authority_id(Long group_authority_id) {
		this.group_authority_id = group_authority_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id")
	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "authority", nullable = false)
	public Authority getAuthority() {
		return this.authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	@Override
	public Object getEntityId() {
		return this.group_authority_id;
	}
}