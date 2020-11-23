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
 * UserLevel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_user_level", catalog = "tiandian")
public class UserLevel extends BasePojo implements java.io.Serializable {
	// Fields
	private Long user_level_id;
	private String user_level_name;
	private Long min_grow_value;
	private Long max_grow_value;
	private Integer valid_days;
	private Double right_ratio;
	private String img_url;
	private Set<Member> members = new HashSet<Member>(0);

	// Constructors
	/** default constructor */
	public UserLevel() {
	}

	/** minimal constructor */
	public UserLevel(Long user_level_id) {
		this.user_level_id = user_level_id;
	}

	/** full constructor */
	public UserLevel(Long user_level_id, String user_level_name, Long min_grow_value, Long max_grow_value,
			Integer valid_days, Double right_ratio, Set<Member> members) {
		this.user_level_id = user_level_id;
		this.user_level_name = user_level_name;
		this.min_grow_value = min_grow_value;
		this.max_grow_value = max_grow_value;
		this.valid_days = valid_days;
		this.right_ratio = right_ratio;
		this.members = members;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "USERLEVEL_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_USERLEVEL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "USERLEVEL_GEN")
	@Column(name = "user_level_id", unique = true, nullable = false)
	public Long getUser_level_id() {
		return this.user_level_id;
	}

	public void setUser_level_id(Long user_level_id) {
		this.user_level_id = user_level_id;
	}

	@Column(name = "user_level_name", length = 50)
	public String getUser_level_name() {
		return this.user_level_name;
	}

	public void setUser_level_name(String user_level_name) {
		this.user_level_name = user_level_name;
	}

	@Column(name = "min_grow_value")
	public Long getMin_grow_value() {
		return this.min_grow_value;
	}

	public void setMin_grow_value(Long min_grow_value) {
		this.min_grow_value = min_grow_value;
	}

	@Column(name = "max_grow_value")
	public Long getMax_grow_value() {
		return this.max_grow_value;
	}

	public void setMax_grow_value(Long max_grow_value) {
		this.max_grow_value = max_grow_value;
	}

	@Column(name = "valid_days")
	public Integer getValid_days() {
		return this.valid_days;
	}

	public void setValid_days(Integer valid_days) {
		this.valid_days = valid_days;
	}

	@Column(name = "right_ratio", precision = 4)
	public Double getRight_ratio() {
		return this.right_ratio;
	}

	public void setRight_ratio(Double right_ratio) {
		this.right_ratio = right_ratio;
	}

	@Column(name = "img_url")
	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userLevel")
	public Set<Member> getMembers() {
		return this.members;
	}

	public void setMembers(Set<Member> members) {
		this.members = members;
	}

	@Override
	public Object getEntityId() {
		return this.user_level_id;
	}
}