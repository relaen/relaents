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
 * Menu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_menu", catalog = "tiandian")
public class Menu extends BasePojo implements java.io.Serializable {
	// Fields
	private Long menu_id;
	private Menu menu;
	private String title;
	private String url;
	private Set<Menu> menus = new HashSet<Menu>(0);
	private Set<GroupMenu> groupMenus = new HashSet<GroupMenu>(0);

	// Constructors
	/** default constructor */
	public Menu() {
	}

	/** minimal constructor */
	public Menu(Long menu_id, String title) {
		this.menu_id = menu_id;
		this.title = title;
	}

	/** full constructor */
	public Menu(Long menu_id, Menu menu, String title, String url, Set<Menu> menus, Set<GroupMenu> groupMenus) {
		this.menu_id = menu_id;
		this.menu = menu;
		this.title = title;
		this.url = url;
		this.menus = menus;
		this.groupMenus = groupMenus;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "MENU_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_MENU", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MENU_GEN")
	@Column(name = "menu_id", unique = true, nullable = false)
	public Long getMenu_id() {
		return this.menu_id;
	}

	public void setMenu_id(Long menu_id) {
		this.menu_id = menu_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@Column(name = "title", nullable = false, length = 50)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "url", length = 500)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "menu")
	public Set<Menu> getMenus() {
		return this.menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "menu")
	public Set<GroupMenu> getGroupMenus() {
		return this.groupMenus;
	}

	public void setGroupMenus(Set<GroupMenu> groupMenus) {
		this.groupMenus = groupMenus;
	}

	@Override
	public Object getEntityId() {
		return this.menu_id;
	}
}