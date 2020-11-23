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
 * GsSaleMan entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_gs_sale_man", catalog = "tiandian")

public class GsSaleMan extends BasePojo implements java.io.Serializable {

	// Fields

	private Long sale_man_id;
	private GsSaleManGiveLevel gsSaleManGiveLevel;
	private SaleMan saleMan;
	private Double fen_cur;
	private Double fen_total;
	private Double fen_grant;
	private Long start_time;
	private Long end_time;

	@Override
	public Object getEntityId() {
		// TODO Auto-generated method stub
		return sale_man_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "GSSALEMAN_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_GSSALEMAN", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GSSALEMAN_GEN")
	@Column(name = "sale_man_id", unique = true, nullable = false)
	public Long getSale_man_id() {
		return sale_man_id;
	}

	public void setSale_man_id(Long sale_man_id) {
		this.sale_man_id = sale_man_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gs_sale_man_give_level_id")

	public GsSaleManGiveLevel getGsSaleManGiveLevel() {
		return gsSaleManGiveLevel;
	}

	public void setGsSaleManGiveLevel(GsSaleManGiveLevel gsSaleManGiveLevel) {
		this.gsSaleManGiveLevel = gsSaleManGiveLevel;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sale_man_id", unique = true, nullable = false, insertable = false, updatable = false)
	public SaleMan getSaleMan() {
		return saleMan;
	}
	
	public void setSaleMan(SaleMan saleMan) {
		this.saleMan = saleMan;
	}

	@Column(name = "fen_cur", precision = 13)
	public Double getFen_cur() {
		return fen_cur;
	}

	public void setFen_cur(Double fen_cur) {
		this.fen_cur = fen_cur;
	}

	@Column(name = "fen_total", precision = 13)
	public Double getFen_total() {
		return fen_total;
	}

	public void setFen_total(Double fen_total) {
		this.fen_total = fen_total;
	}

	@Column(name = "fen_grant", precision = 13)
	public Double getFen_grant() {
		return fen_grant;
	}

	public void setFen_grant(Double fen_grant) {
		this.fen_grant = fen_grant;
	}

	@Column(name = "start_time")
	public Long getStart_time() {
		return start_time;
	}

	public void setStart_time(Long start_time) {
		this.start_time = start_time;
	}

	@Column(name = "end_time")
	public Long getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Long end_time) {
		this.end_time = end_time;
	}

}