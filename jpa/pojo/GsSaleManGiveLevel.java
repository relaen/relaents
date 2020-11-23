package dao.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * GsSaleManGiveLevel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_gs_sale_man_give_level", catalog = "tiandian")

public class GsSaleManGiveLevel extends BasePojo implements java.io.Serializable {

	// Fields

	private Long gs_sale_man_give_level_id;
	private Integer valid_day_num;
	
	@Override
	public Object getEntityId() {
		// TODO Auto-generated method stub
		return gs_sale_man_give_level_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "GSSALEMANGIVELEVEL_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_GSSALEMANGIVELEVEL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GSSALEMANGIVELEVEL_GEN")
	@Column(name = "gs_sale_man_give_level_id", unique = true, nullable = false)
	public Long getGs_sale_man_give_level_id() {
		return gs_sale_man_give_level_id;
	}

	public void setGs_sale_man_give_level_id(Long gs_sale_man_give_level_id) {
		this.gs_sale_man_give_level_id = gs_sale_man_give_level_id;
	}

	@Column(name = "valid_day_num")
	public Integer getValid_day_num() {
		return valid_day_num;
	}

	public void setValid_day_num(Integer valid_day_num) {
		this.valid_day_num = valid_day_num;
	}

}