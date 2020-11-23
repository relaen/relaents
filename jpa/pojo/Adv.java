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
 * Adv entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_adv", catalog = "tiandian")
public class Adv extends BasePojo implements java.io.Serializable {
	// Fields
	private Long adv_id;
	private Company company;
	private Commodity commodity;
	private Integer adv_type;
	private Long start_time;
	private Long end_time;
	private Integer sort_order;
	private String adv_html;
	private String adv_title;
	private Integer adv_loc;
	private Integer enabled=1;
	private Set<AdvImg> advImgs = new HashSet<AdvImg>(0);

	// Constructors
	/** default constructor */
	public Adv() {
	}

	/** minimal constructor */
	public Adv(Long ads_id, Long start_time, Long end_time) {
		this.adv_id = ads_id;
		this.start_time = start_time;
		this.end_time = end_time;
	}


	// Property accessors
	@Id
	@TableGenerator(name = "ADV_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_ADV", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ADV_GEN")
	@Column(name = "adv_id", unique = true, nullable = false)
	public Long getAdv_id() {
		return this.adv_id;
	}

	public void setAdv_id(Long ads_id) {
		this.adv_id = ads_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commodity_id")
	public Commodity getCommodity() {
		return this.commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	@Column(name = "adv_type")
	public Integer getAdv_type() {
		return this.adv_type;
	}

	public void setAdv_type(Integer ads_type) {
		this.adv_type = ads_type;
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

	@Column(name = "sort_order")
	public Integer getSort_order() {
		return this.sort_order;
	}

	public void setSort_order(Integer sort_order) {
		this.sort_order = sort_order;
	}

	@Column(name = "adv_html", length = 500)
	public String getAdv_html() {
		return this.adv_html;
	}

	public void setAdv_html(String ads_html) {
		this.adv_html = ads_html;
	}

	@Column(name = "adv_title", length = 200)
	public String getAdv_title() {
		return this.adv_title;
	}

	public void setAdv_title(String ads_title) {
		this.adv_title = ads_title;
	}

	@Column(name = "enabled")
	public Integer getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	
	@Column(name = "adv_loc")
	public Integer getAdv_loc() {
		return adv_loc;
	}

	public void setAdv_loc(Integer adv_loc) {
		this.adv_loc = adv_loc;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "adv")
	public Set<AdvImg> getAdvImgs() {
		return this.advImgs;
	}

	public void setAdvImgs(Set<AdvImg> advImgs) {
		this.advImgs = advImgs;
	}

	@Override
	public Object getEntityId() {
		return this.adv_id;
	}
}