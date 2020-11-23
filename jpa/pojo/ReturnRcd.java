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
 * ReturnProcess entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_return_rcd", catalog = "tiandian")
public class ReturnRcd extends BasePojo implements java.io.Serializable {
	// Fields
	private Long return_rcd_id;
	private ReturnInfo returnInfo;
	private ReturnState returnState;
	private Long create_time;

	// Constructors
	/** default constructor */
	public ReturnRcd() {
	}

	/** minimal constructor */
	public ReturnRcd(Long return_rcd_id) {
		this.return_rcd_id = return_rcd_id;
	}

	
	// Property accessors
	@Id
	@TableGenerator(name = "RETURNPROCESS_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_RETURNPROCESS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "RETURNPROCESS_GEN")
	@Column(name = "return_rcd_id", unique = true, nullable = false)
	public Long getReturn_rcd_id() {
		return this.return_rcd_id;
	}

	public void setReturn_rcd_id(Long return_rcd_id) {
		this.return_rcd_id = return_rcd_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "return_info")
	public ReturnInfo getReturnInfo() {
		return this.returnInfo;
	}

	public void setReturnInfo(ReturnInfo returnInfo) {
		this.returnInfo = returnInfo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "return_state_id")
	public ReturnState getReturnState() {
		return this.returnState;
	}

	public void setReturnState(ReturnState returnState) {
		this.returnState = returnState;
	}

	@Column(name = "create_time")
	public Long getCreate_time() {
		return this.create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	@Override
	public Object getEntityId() {
		return this.return_rcd_id;
	}
}