package dao.pojo;

import javax.persistence.TableGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.HashSet;
import java.util.Set;

// default package
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Invoice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_invoice", catalog = "tiandian")
public class Invoice extends BasePojo implements java.io.Serializable {
	// Fields
	private Long invoice_id;
	private Member member;
	private String invoice_title;
	private String tax_no;
	
	
	// Constructors
	/** default constructor */
	public Invoice() {
	}

	/** minimal constructor */
	public Invoice(Long invoice_id) {
		this.invoice_id = invoice_id;
	}

	/** full constructor */
	public Invoice(Long invoice_id, Member member, String invoice_title, String tax_no) {
		this.invoice_id = invoice_id;
		this.member = member;
		this.invoice_title = invoice_title;
		this.tax_no = tax_no;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "INVOICE_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_INVOICE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "INVOICE_GEN")
	@Column(name = "invoice_id", unique = true, nullable = false)
	public Long getInvoice_id() {
		return this.invoice_id;
	}

	public void setInvoice_id(Long invoice_id) {
		this.invoice_id = invoice_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Column(name = "invoice_title", length = 200)
	public String getInvoice_title() {
		return this.invoice_title;
	}

	public void setInvoice_title(String invoice_title) {
		this.invoice_title = invoice_title;
	}

	@Column(name = "tax_no", length = 50)
	public String getTax_no() {
		return this.tax_no;
	}

	public void setTax_no(String tax_no) {
		this.tax_no = tax_no;
	}
	
	@Override
	public Object getEntityId() {
		return this.invoice_id;
	}
}