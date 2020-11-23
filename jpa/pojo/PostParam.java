package dao.pojo;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * PostParam entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_post_param", catalog = "tiandian")

public class PostParam extends BasePojo implements java.io.Serializable {

	// Fields

	private Long post_param_id;
	private Double min_free_money;
	private Double post_fee;
	private Integer post_type;

	// Constructors

	/** default constructor */
	public PostParam() {
	}

	/** minimal constructor */
	public PostParam(Long post_param_id) {
		this.post_param_id = post_param_id;
	}

	/** full constructor */
	public PostParam(Long post_param_id, Double min_free_money, Double post_fee) {
		this.post_param_id = post_param_id;
		this.min_free_money = min_free_money;
		this.post_fee = post_fee;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "POSTPARAM_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_POSTPARAM", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "POSTPARAM_GEN")
	@Column(name = "post_param_id", unique = true, nullable = false)

	public Long getPost_param_id() {
		return this.post_param_id;
	}

	public void setPost_param_id(Long post_param_id) {
		this.post_param_id = post_param_id;
	}

	@Column(name = "min_free_money", precision = 13)

	public Double getMin_free_money() {
		return this.min_free_money;
	}

	public void setMin_free_money(Double min_free_money) {
		this.min_free_money = min_free_money;
	}

	@Column(name = "post_fee", precision = 13)

	public Double getPost_fee() {
		return this.post_fee;
	}

	public void setPost_fee(Double post_fee) {
		this.post_fee = post_fee;
	}

	@Column(name = "post_type")
	public Integer getPost_type() {
		return post_type;
	}
	
	public void setPost_type(Integer post_type) {
		this.post_type = post_type;
	}

	@Override
	public Object getEntityId() {
		return this.post_param_id;
	}

}