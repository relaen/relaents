package dao.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * TAgreement entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_agreement", catalog = "tiandian")

public class Agreement extends BasePojo implements java.io.Serializable {

	// Fields

	private Long agreement_id;
	private String title;
	private String content;

	// Constructors

	/** default constructor */
	public Agreement() {
	}

	/** minimal constructor */
	public Agreement(Long agreementId) {
		this.agreement_id = agreementId;
	}

	/** full constructor */
	public Agreement(Long agreementId, String content) {
		this.agreement_id = agreementId;
		this.content = content;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "AGREEMENT_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_AGREEMENT", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "AGREEMENT_GEN")
	@Column(name = "agreement_id", unique = true, nullable = false)

	public Long getAgreement_id() {
		return this.agreement_id;
	}

	public void setAgreement_id(Long agreementId) {
		this.agreement_id = agreementId;
	}

	@Column(name = "title", length = 200)
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public Object getEntityId() {
		return this.agreement_id;
	}
}