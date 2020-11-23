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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * CommodityComment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_commodity_comment", catalog = "tiandian")
public class CommodityComment extends BasePojo implements java.io.Serializable {
	// Fields
	private Long commodity_comment_id;
	private Commodity commodity;
	private OrderCommodity orderCommodity;
	private String content;
	private Double score;
	private Integer level;
	private Integer has_img;
	private Long comment_time;
	private Set<CommodityCommentRes> commodityCommentReses = new HashSet<CommodityCommentRes>(0);

	// Property accessors
	@Id
	@TableGenerator(name = "COMMODITYCOMMENT_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COMMODITYCOMMENT", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMMODITYCOMMENT_GEN")
	@Column(name = "commodity_comment_id", unique = true, nullable = false)
	public Long getCommodity_comment_id() {
		return this.commodity_comment_id;
	}

	public void setCommodity_comment_id(Long commodity_comment_id) {
		this.commodity_comment_id = commodity_comment_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commodity_id")
	public Commodity getCommodity() {
		return this.commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_commodity_id")
	public OrderCommodity getOrderCommodity() {
		return this.orderCommodity;
	}

	public void setOrderCommodity(OrderCommodity orderCommodity) {
		this.orderCommodity = orderCommodity;
	}

	@Column(name = "content", length = 500)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "score", precision = 4, scale = 1)
	public Double getScore() {
		return this.score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	@Column(name = "level")
	public Integer getLevel() {
		return level;
	}
	
	public void setLevel(Integer level) {
		this.level = level;
	}

	@Column(name = "has_img")
	public Integer getHas_img() {
		return has_img;
	}
	
	public void setHas_img(Integer has_img) {
		this.has_img = has_img;
	}

	@Column(name = "comment_time")
	public Long getComment_time() {
		return comment_time;
	}

	public void setComment_time(Long comment_time) {
		this.comment_time = comment_time;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commodityComment")
	public Set<CommodityCommentRes> getCommodityCommentReses() {
		return this.commodityCommentReses;
	}

	public void setCommodityCommentReses(Set<CommodityCommentRes> commodityCommentReses) {
		this.commodityCommentReses = commodityCommentReses;
	}

	@Override
	public Object getEntityId() {
		return this.commodity_comment_id;
	}
}