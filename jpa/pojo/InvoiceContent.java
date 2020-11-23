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
 * InvoiceContent entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_invoice_content", catalog = "tiandian")
public class InvoiceContent extends BasePojo implements java.io.Serializable {
	// Fields
	private Long invoice_content_id;
	private String content;
	private String remarks;
	private Set<OrderInvoice> orderInvoices = new HashSet<OrderInvoice>(0);
	
	// Constructors
	/** default constructor */
	public InvoiceContent() {
	}

	/** minimal constructor */
	public InvoiceContent(Long invoice_content_id) {
		this.invoice_content_id = invoice_content_id;
	}


	// Property accessors
	@Id
	@TableGenerator(name = "INVOICECONTENT_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_INVOICECONTENT", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "INVOICECONTENT_GEN")
	@Column(name = "invoice_content_id", unique = true, nullable = false)
	public Long getInvoice_content_id() {
		return this.invoice_content_id;
	}

	public void setInvoice_content_id(Long invoice_content_id) {
		this.invoice_content_id = invoice_content_id;
	}

	@Column(name = "content", length = 200)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "remarks", length = 500)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "invoiceContent")
	public Set<OrderInvoice> getOrderInvoices() {
		return orderInvoices;
	}

	public void setOrderInvoices(Set<OrderInvoice> orderInvoices) {
		this.orderInvoices = orderInvoices;
	}

	@Override
	public Object getEntityId() {
		return this.invoice_content_id;
	}
}