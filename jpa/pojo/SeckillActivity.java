package dao.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * TSeckill entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_seckill_activity", catalog = "tiandian")

public class SeckillActivity extends BasePojo implements java.io.Serializable {

	// Fields

	private Long seckill_activity_id;
	private String main_img;
	private String title;

	@Override
	public Object getEntityId() {
		return seckill_activity_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "SECKILLACTIVITY_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_SECKILLACTIVITY", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SECKILLACTIVITY_GEN")
	@Column(name = "seckill_activity_id", unique = true, nullable = false)
	public Long getSeckill_activity_id() {
		return seckill_activity_id;
	}

	public void setSeckill_activity_id(Long seckill_activity_id) {
		this.seckill_activity_id = seckill_activity_id;
	}

	@Column(name = "main_img", length = 500)
	public String getMain_img() {
		return main_img;
	}

	public void setMain_img(String main_img) {
		this.main_img = main_img;
	}

	@Column(name = "title", length = 50)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}