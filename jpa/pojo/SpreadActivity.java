package dao.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * TSpreadActivity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_spread_activity", catalog = "tiandian")

public class SpreadActivity extends BasePojo implements java.io.Serializable {

	// Fields

	private Long spread_activity_id;
	private String spread_activity_no;
	private String spread_name;
	private String spread_link;

	@Override
	public Object getEntityId() {
		// TODO Auto-generated method stub
		return spread_activity_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "SPREADACTIVITY_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_SPREADACTIVITY", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SPREADACTIVITY_GEN")
	@Column(name = "spread_activity_id", unique = true, nullable = false)
	public Long getSpread_activity_id() {
		return spread_activity_id;
	}

	public void setSpread_activity_id(Long spread_activity_id) {
		this.spread_activity_id = spread_activity_id;
	}

	@Column(name = "spread_activity_no", length = 20)
	public String getSpread_activity_no() {
		return spread_activity_no;
	}

	public void setSpread_activity_no(String spread_activity_no) {
		this.spread_activity_no = spread_activity_no;
	}

	@Column(name = "spread_name", length = 50)
	public String getSpread_name() {
		return spread_name;
	}

	public void setSpread_name(String spread_name) {
		this.spread_name = spread_name;
	}

	@Column(name = "spread_link", length = 50)
	public String getSpread_link() {
		return spread_link;
	}

	public void setSpread_link(String spread_link) {
		this.spread_link = spread_link;
	}

}