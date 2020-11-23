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
 * RecommendCommodity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_recommend_commodity", catalog = "tiandian")
public class RecommendCommodity extends BasePojo implements java.io.Serializable {
	// Fields
	private Long recommend_commodity_id;
	private Commodity commodity;
	private Integer sort_order;

	// Constructors
	/** default constructor */
	public RecommendCommodity() {
	}

	/** minimal constructor */
	public RecommendCommodity(Long recommend_commodity_id) {
		this.recommend_commodity_id = recommend_commodity_id;
	}

	/** full constructor */
	public RecommendCommodity(Long recommend_commodity_id, Commodity commodity, Integer sort_order) {
		this.recommend_commodity_id = recommend_commodity_id;
		this.commodity = commodity;
		this.sort_order = sort_order;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "RECOMMENDCOMMODITY_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_RECOMMENDCOMMODITY", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "RECOMMENDCOMMODITY_GEN")
	@Column(name = "recommend_commodity_id", unique = true, nullable = false)
	public Long getRecommend_commodity_id() {
		return this.recommend_commodity_id;
	}

	public void setRecommend_commodity_id(Long recommend_commodity_id) {
		this.recommend_commodity_id = recommend_commodity_id;
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
		return this.recommend_commodity_id;
	}
}