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
 * GroupMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_group_menu", catalog = "tiandian")
public class GroupMenu extends BasePojo implements java.io.Serializable {
	// Fields
	private Long group_menu_id;
	private Group group;
	private Menu menu;

	// Constructors
	/** default constructor */
	public GroupMenu() {
	}

	/** minimal constructor */
	public GroupMenu(Long group_menu_id) {
		this.group_menu_id = group_menu_id;
	}

	/** full constructor */
	public GroupMenu(Long group_menu_id, Group group, Menu menu) {
		this.group_menu_id = group_menu_id;
		this.group = group;
		this.menu = menu;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "GROUPMENU_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_GROUPMENU", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GROUPMENU_GEN")
	@Column(name = "group_menu_id", unique = true, nullable = false)
	public Long getGroup_menu_id() {
		return this.group_menu_id;
	}

	public void setGroup_menu_id(Long group_menu_id) {
		this.group_menu_id = group_menu_id;
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
	@JoinColumn(name = "menu_id")
	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@Override
	public Object getEntityId() {
		return this.group_menu_id;
	}
}