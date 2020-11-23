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
 * CommodityCollection entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_commodity_collection", catalog = "tiandian")
public class CommodityCollection extends BasePojo implements java.io.Serializable {
	// Fields
	private Long commodity_collection_id;
	private Commodity commodity;
	private Member member;
	private Long collect_time;

	// Constructors
	/** default constructor */
	public CommodityCollection() {
	}

	/** minimal constructor */
	public CommodityCollection(Long commodity_collection_id, Long collect_time) {
		this.commodity_collection_id = commodity_collection_id;
		this.collect_time = collect_time;
	}

	/** full constructor */
	public CommodityCollection(Long commodity_collection_id, Commodity commodity, Member member,
			Long collect_time) {
		this.commodity_collection_id = commodity_collection_id;
		this.commodity = commodity;
		this.member = member;
		this.collect_time = collect_time;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "COMMODITYCOLLECTION_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COMMODITYCOLLECTION", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMMODITYCOLLECTION_GEN")
	@Column(name = "commodity_collection_id", unique = true, nullable = false)
	public Long getCommodity_collection_id() {
		return this.commodity_collection_id;
	}

	public void setCommodity_collection_id(Long commodity_collection_id) {
		this.commodity_collection_id = commodity_collection_id;
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
	@JoinColumn(name = "member_id")
	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Column(name = "collect_time", nullable = false, length = 19)
	public Long getCollect_time() {
		return this.collect_time;
	}

	public void setCollect_time(Long collect_time) {
		this.collect_time = collect_time;
	}

	@Override
	public Object getEntityId() {
		return this.commodity_collection_id;
	}
}