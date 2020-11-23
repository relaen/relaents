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
 * TBargain entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_bargain", catalog = "tiandian")

public class Bargain extends BasePojo implements java.io.Serializable {

	// Fields

	private Long bargain_id;
	private PromotionCommodity promotionCommodity;
	private String price_num_data;

	@Override
	public Object getEntityId() {
		// TODO Auto-generated method stub
		return bargain_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "BARGAIN_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_BARGAIN", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "BARGAIN_GEN")
	@Column(name = "bargain_id", unique = true, nullable = false)
	public Long getBargain_id() {
		return bargain_id;
	}

	public void setBargain_id(Long bargain_id) {
		this.bargain_id = bargain_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "promotion_commodity_id")
	public PromotionCommodity getPromotionCommodity() {
		return promotionCommodity;
	}

	public void setPromotionCommodity(PromotionCommodity promotionCommodity) {
		this.promotionCommodity = promotionCommodity;
	}

	@Column(name = "price_num_data", length = 500)
	public String getPrice_num_data() {
		return price_num_data;
	}

	public void setPrice_num_data(String price_num_data) {
		this.price_num_data = price_num_data;
	}

}