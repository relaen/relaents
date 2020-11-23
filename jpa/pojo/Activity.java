package dao.pojo;

// default package
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * Activity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_activity")
public class Activity extends BasePojo implements java.io.Serializable {
	// Fields
	private Long activity_id;
	private String activity_title;
	private Long start_time;
	private Long end_time;
	private Integer activity_type;
	private Integer enabled = 1;
	private String link_url;
	private Integer link_type; // 链接类型，默认链接商品列表，1：链接link_url路由

	private Set<ActivityCommodity> activityCommodities = new HashSet<ActivityCommodity>(0);
	private Set<ActivityImg> activityImgs = new HashSet<ActivityImg>(0);

	// Constructors
	/** default constructor */
	public Activity() {
	}

	/** minimal constructor */
	public Activity(Long activity_id, Long start_time, Long end_time) {
		this.activity_id = activity_id;
		this.start_time = start_time;
		this.end_time = end_time;
	}

	/** full constructor */
	public Activity(Long activity_id, String activity_title, Long start_time, Long end_time,
			Set<ActivityCommodity> activityCommodities, Set<ActivityImg> activityImgs) {
		this.activity_id = activity_id;
		this.activity_title = activity_title;
		this.start_time = start_time;
		this.end_time = end_time;
		this.activityCommodities = activityCommodities;
		this.activityImgs = activityImgs;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "ACTIVITY_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_ACTIVITY", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ACTIVITY_GEN")
	@Column(name = "activity_id", unique = true, nullable = false)
	public Long getActivity_id() {
		return this.activity_id;
	}

	public void setActivity_id(Long activity_id) {
		this.activity_id = activity_id;
	}

	@Column(name = "activity_title", length = 200)
	public String getActivity_title() {
		return this.activity_title;
	}

	public void setActivity_title(String activity_title) {
		this.activity_title = activity_title;
	}

	@Column(name = "start_time", nullable = false, length = 19)
	public Long getStart_time() {
		return this.start_time;
	}

	public void setStart_time(Long start_time) {
		this.start_time = start_time;
	}

	@Column(name = "end_time", nullable = false, length = 19)
	public Long getEnd_time() {
		return this.end_time;
	}

	public void setEnd_time(Long end_time) {
		this.end_time = end_time;
	}

	@Column(name = "activity_type")
	public Integer getActivity_type() {
		return activity_type;
	}

	public void setActivity_type(Integer activity_type) {
		this.activity_type = activity_type;
	}

	@Column(name = "enabled")
	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	@Column(name = "link_url", length = 500)
	public String getLink_url() {
		return link_url;
	}

	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}
	
	@Column(name = "link_type")
	public Integer getLink_type() {
		return link_type;
	}
	
	public void setLink_type(Integer link_type) {
		this.link_type = link_type;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "activity")
	public Set<ActivityCommodity> getActivityCommodities() {
		return this.activityCommodities;
	}

	public void setActivityCommodities(Set<ActivityCommodity> activityCommodities) {
		this.activityCommodities = activityCommodities;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "activity")
	public Set<ActivityImg> getActivityImgs() {
		return this.activityImgs;
	}

	public void setActivityImgs(Set<ActivityImg> activityImgs) {
		this.activityImgs = activityImgs;
	}

	@Override
	public Object getEntityId() {
		return this.activity_id;
	}
}