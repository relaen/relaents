package dao.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Sequence entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sequence", catalog = "tiandian")

public class Sequence implements java.io.Serializable {

	// Fields

	private String seqName;
	private Long seqCount;

	// Constructors

	/** default constructor */
	public Sequence() {
	}

	/** full constructor */
	public Sequence(String seqName, Long seqCount) {
		this.seqName = seqName;
		this.seqCount = seqCount;
	}

	// Property accessors
	@Id
	@Column(name = "SEQ_NAME", unique = true, nullable = false, length = 50)

	public String getSeqName() {
		return this.seqName;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}

	@Column(name = "SEQ_COUNT", nullable = false)

	public Long getSeqCount() {
		return this.seqCount;
	}

	public void setSeqCount(Long seqCount) {
		this.seqCount = seqCount;
	}

}