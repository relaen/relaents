package dao.pojo;

import javax.persistence.TableGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
// default package
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * GrowRcd entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_grow_rcd", catalog = "tiandian")
public class GrowRcd extends BasePojo implements java.io.Serializable {
	// Fields
	private Long grow_value_id;
	private GrowType growType;
	private Member member;
	private Integer value;
	private Long create_time;

	// Constructors
	/** default constructor */
	public GrowRcd() {
	}

	/** minimal constructor */
	public GrowRcd(Long grow_value_id) {
		this.grow_value_id = grow_value_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "GROWRCD_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_GROWRCD", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GROWRCD_GEN")
	@Column(name = "grow_value_id", unique = true, nullable = false)
	public Long getGrow_value_id() {
		return this.grow_value_id;
	}

	public void setGrow_value_id(Long grow_value_id) {
		this.grow_value_id = grow_value_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "grow_type_id")
	public GrowType getGrowType() {
		return this.growType;
	}

	public void setGrowType(GrowType growType) {
		this.growType = growType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Column(name = "value")
	public Integer getValue() {
		return this.value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Column(name = "create_time")
	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}	

	@Override
	public Object getEntityId() {
		return this.grow_value_id;
	}
}