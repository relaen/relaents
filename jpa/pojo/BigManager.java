package dao.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "t_big_manager", catalog = "tiandian")
public class BigManager extends BasePojo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long big_manager_id;
	private SaleMan saleMan;

	private Double ratio = 0d;

	@Id
	@TableGenerator(name = "BIGMANAGER_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_WALLET", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "BIGMANAGER_GEN")
	@Column(name = "big_manager_id", unique = true, nullable = false)
	public Long getBig_manager_id() {
		return big_manager_id;
	}

	public void setBig_manager_id(Long big_manager_id) {
		this.big_manager_id = big_manager_id;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sale_man_id")
	public SaleMan getSaleMan() {
		return saleMan;
	}

	public void setSaleMan(SaleMan saleMan) {
		this.saleMan = saleMan;
	}

	@Column(name = "ratio")
	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}
}
