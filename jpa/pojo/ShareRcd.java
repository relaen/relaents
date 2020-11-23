package dao.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * TShareRcd entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_share_rcd", catalog = "tiandian")

public class ShareRcd extends BasePojo implements java.io.Serializable {

	// Fields

	private Long share_rcd_id;
	private PromotionCommodity promotionCommodity;
	private Commodity commodity;
	private Seckill seckill;
	private Member member;
	private Long share_time;
	private Integer type; // 0商品，1活动，2秒杀

	@Override
	public Object getEntityId() {
		// TODO Auto-generated method stub
		return share_rcd_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "SHARERCD_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_SHARERCD", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SHARERCD_GEN")
	@Column(name = "share_rcd_id", unique = true, nullable = false)
	public Long getShare_rcd_id() {
		return share_rcd_id;
	}

	public void setShare_rcd_id(Long share_rcd_id) {
		this.share_rcd_id = share_rcd_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "promotion_commodity_id")
	public PromotionCommodity getPromotionCommodity() {
		return promotionCommodity;
	}

	public void setPromotionCommodity(PromotionCommodity promotionCommodity) {
		this.promotionCommodity = promotionCommodity;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commodity_id")
	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seckill_id")
	public Seckill getSeckill() {
		return seckill;
	}

	public void setSeckill(Seckill seckill) {
		this.seckill = seckill;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Column(name = "share_time")
	public Long getShare_time() {
		return share_time;
	}

	public void setShare_time(Long share_time) {
		this.share_time = share_time;
	}

	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}