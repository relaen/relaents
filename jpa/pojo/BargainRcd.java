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
 * TBargainRcd entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_bargain_rcd", catalog = "tiandian")

public class BargainRcd extends BasePojo implements java.io.Serializable {

	// Fields

	private Long bargain_rcd_id;
	private BargainMember bargainMember;
	private Long rcd_time;
	private Double price;
	private String openid;

	@Override
	public Object getEntityId() {
		// TODO Auto-generated method stub
		return bargain_rcd_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "BARGAINRCD_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_BARGAINRCD", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "BARGAINRCD_GEN")
	@Column(name = "bargain_rcd_id", unique = true, nullable = false)
	public Long getBargain_rcd_id() {
		return bargain_rcd_id;
	}

	public void setBargain_rcd_id(Long bargain_rcd_id) {
		this.bargain_rcd_id = bargain_rcd_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bargain_member_id")
	public BargainMember getBargainMember() {
		return bargainMember;
	}

	public void setBargainMember(BargainMember bargainMember) {
		this.bargainMember = bargainMember;
	}

	@Column(name = "rcd_time")
	public Long getRcd_time() {
		return rcd_time;
	}

	public void setRcd_time(Long rcd_time) {
		this.rcd_time = rcd_time;
	}

	@Column(name = "price", precision = 13)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "openid", length = 64)
	public String getOpenid() {
		return this.openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

}