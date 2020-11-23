package dao.pojo;

import javax.persistence.TableGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
// default package
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * DevideParamId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_devide_param", catalog = "tiandian")
public class DevideParam extends BasePojo implements java.io.Serializable {
	// Fields
	private Long devide_param_id=1l;
	private Double plat_ratio;
	private Double big_manager_ratio;
	private Double manager_ratio;	//区域经理总的比例
	private Double manager_ratio1;	//绩效比例
	private Double sale_man_ratio;
	private Double shop_spread_ratio;
	private Double recommender_ratio;
	private Double no_fen_devide;
	// Constructors
	/** default constructor */
	public DevideParam() {
	}

	/** full constructor */
	public DevideParam(Long devide_param_id, Double plat_ratio, Double big_manager_ratio, Double manager_ratio,
			Double sale_man_ratio) {
		this.devide_param_id = devide_param_id;
		this.plat_ratio = plat_ratio;
		this.big_manager_ratio = big_manager_ratio;
		this.manager_ratio = manager_ratio;
		this.sale_man_ratio = sale_man_ratio;
	}

	// Property accessors
	@Id
	@Column(name = "devide_param_id", unique = true, nullable = false)
	public Long getDevide_param_id() {
		return this.devide_param_id;
	}

	public void setDevide_param_id(Long devide_param_id) {
		this.devide_param_id = devide_param_id;
	}

	@Column(name = "plat_ratio", precision = 4)
	public Double getPlat_ratio() {
		return this.plat_ratio;
	}

	public void setPlat_ratio(Double plat_ratio) {
		this.plat_ratio = plat_ratio;
	}

	@Column(name = "big_manager_ratio", precision = 4)
	public Double getBig_manager_ratio() {
		return this.big_manager_ratio;
	}

	public void setBig_manager_ratio(Double big_manager_ratio) {
		this.big_manager_ratio = big_manager_ratio;
	}

	@Column(name = "manager_ratio", precision = 4)
	public Double getManager_ratio() {
		return this.manager_ratio;
	}

	public void setManager_ratio(Double manager_ratio) {
		this.manager_ratio = manager_ratio;
	}

	@Column(name = "sale_man_ratio", precision = 4)
	public Double getSale_man_ratio() {
		return this.sale_man_ratio;
	}

	public void setSale_man_ratio(Double sale_man_ratio) {
		this.sale_man_ratio = sale_man_ratio;
	}

	@Column(name = "shop_spread_ratio", precision = 4)
	public Double getShop_spread_ratio() {
		return shop_spread_ratio;
	}

	public void setShop_spread_ratio(Double shop_spread_ratio) {
		this.shop_spread_ratio = shop_spread_ratio;
	}
	
	
	@Column(name = "recommender_ratio", precision = 4)
	public Double getRecommender_ratio() {
		return recommender_ratio;
	}

	public void setRecommender_ratio(Double recommender_ratio) {
		this.recommender_ratio = recommender_ratio;
	}
	
	@Column(name = "no_fen_devide", precision = 4)
	public Double getNo_fen_devide() {
		return no_fen_devide;
	}

	public void setNo_fen_devide(Double no_fen_devide) {
		this.no_fen_devide = no_fen_devide;
	}
	
	
	@Column(name = "manager_ratio1", precision = 4)
	public Double getManager_ratio1() {
		return manager_ratio1;
	}

	public void setManager_ratio1(Double manager_ratio1) {
		this.manager_ratio1 = manager_ratio1;
	}

	@Override
	public Object getEntityId() {
		return this.devide_param_id;
	}
}