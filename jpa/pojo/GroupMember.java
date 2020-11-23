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
 * GroupMember entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_group_member", catalog = "tiandian")
public class GroupMember extends BasePojo implements java.io.Serializable {
	// Fields
	private Long group_member_id;
	private Group group;
	private User user;

	// Constructors
	/** default constructor */
	public GroupMember() {
	}

	/** minimal constructor */
	public GroupMember(Long group_member_id) {
		this.group_member_id = group_member_id;
	}

	/** full constructor */
	public GroupMember(Long group_member_id, Group group, User user) {
		this.group_member_id = group_member_id;
		this.group = group;
		this.user = user;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "GROUPMEMBER_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_GROUPMEMBER", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GROUPMEMBER_GEN")
	@Column(name = "group_member_id", unique = true, nullable = false)
	public Long getGroup_member_id() {
		return this.group_member_id;
	}

	public void setGroup_member_id(Long group_member_id) {
		this.group_member_id = group_member_id;
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
	@JoinColumn(name = "user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public Object getEntityId() {
		return this.group_member_id;
	}
}