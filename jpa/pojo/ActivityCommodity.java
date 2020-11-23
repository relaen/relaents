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
 * ActivityCommodity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_activity_commodity")
public class ActivityCommodity extends BasePojo implements java.io.Serializable {
	// Fields
	private Long activity_commodity_id;
	private Activity activity;
	private Commodity commodity;
	private Integer sort_order;

	// Constructors
	/** default constructor */
	public ActivityCommodity() {
	}

	/** minimal constructor */
	public ActivityCommodity(Long activity_commodity_id) {
		this.activity_commodity_id = activity_commodity_id;
	}

	/** full constructor */
	public ActivityCommodity(Long activity_commodity_id, Activity activity, Commodity commodity, Integer sort_order) {
		this.activity_commodity_id = activity_commodity_id;
		this.activity = activity;
		this.commodity = commodity;
		this.sort_order = sort_order;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "ACTIVITYCOMMODITY_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_ACTIVITYCOMMODITY", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ACTIVITYCOMMODITY_GEN")
	@Column(name = "activity_commodity_id", unique = true, nullable = false)
	public Long getActivity_commodity_id() {
		return this.activity_commodity_id;
	}

	public void setActivity_commodity_id(Long activity_commodity_id) {
		this.activity_commodity_id = activity_commodity_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "activity_id")
	public Activity getActivity() {
		return this.activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commodity_id")
	public Commodity getCommodity() {
		return this.commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	@Column(name = "sort_order")
	public Integer getSort_order() {
		return this.sort_order;
	}

	public void setSort_order(Integer sort_order) {
		this.sort_order = sort_order;
	}

	@Override
	public Object getEntityId() {
		return this.activity_commodity_id;
	}
}