package dao.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * GsExeTime entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_gs_exe_time", catalog = "tiandian")

public class GsExeTime extends BasePojo implements java.io.Serializable {

	// Fields

	private Long gs_exe_time;

	@Override
	public Object getEntityId() {
		// TODO Auto-generated method stub
		return gs_exe_time;
	}

	// Property accessors
	@Id
	@Column(name = "gs_exe_time", unique = true, nullable = false)
	public Long getGs_exe_time() {
		return gs_exe_time;
	}

	public void setGs_exe_time(Long gs_exe_time) {
		this.gs_exe_time = gs_exe_time;
	}

}