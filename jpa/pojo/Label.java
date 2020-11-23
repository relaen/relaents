package dao.pojo;

import javax.persistence.TableGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
// default package
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Label entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_label", catalog = "tiandian")
public class Label extends BasePojo implements java.io.Serializable {
	// Fields
	private Long label_id;
	private String label_name;
	private Long user_count;
	private String remarks;
	private Set<Member> members = new HashSet<Member>(0);

	// Constructors
	/** default constructor */
	public Label() {
	}

	/** minimal constructor */
	public Label(Long label_id) {
		this.label_id = label_id;
	}

	

	// Property accessors
	@Id
	@TableGenerator(name = "LABEL_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_LABEL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "LABEL_GEN")
	@Column(name = "label_id", unique = true, nullable = false)
	public Long getLabel_id() {
		return this.label_id;
	}

	public void setLabel_id(Long label_id) {
		this.label_id = label_id;
	}

	@Column(name = "label_name", length = 50)
	public String getLabel_name() {
		return this.label_name;
	}

	public void setLabel_name(String label_name) {
		this.label_name = label_name;
	}

	@Column(name = "user_count")
	public Long getUser_count() {
		return this.user_count;
	}

	public void setUser_count(Long user_count) {
		this.user_count = user_count;
	}

	@Column(name = "remarks", length = 500)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remark) {
		this.remarks = remark;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "label")
	public Set<Member> getMembers() {
		return this.members;
	}

	public void setMembers(Set<Member> members) {
		this.members = members;
	}

	@Override
	public Object getEntityId() {
		return this.label_id;
	}
}