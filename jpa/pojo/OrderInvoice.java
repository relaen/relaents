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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * OrderInvoice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_order_invoice", catalog = "tiandian")
public class OrderInvoice extends BasePojo implements java.io.Serializable {
	// Fields
	private Long order_id;
	private Orders orders;
	private String content;
	private String invoice_title;
	private String tax_no;
	private Long invoice_time;
	private String invoice_type;
	private String invoice_no;
	private Double invoice_money;
	private InvoiceContent invoiceContent;
	private String email;
	// Constructors
	/** default constructor */
	public OrderInvoice() {
	}

	// Property accessors
	@Id
	@Column(name = "order_id", unique = true, nullable = false)
	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Orders getOrders() {
		return this.orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	
	@Column(name = "content", length = 200)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "invoice_title", length = 200)
	public String getInvoice_title() {
		return this.invoice_title;
	}

	public void setInvoice_title(String invoice_title) {
		this.invoice_title = invoice_title;
	}

	@Column(name = "tax_no", length = 200)
	public String getTax_no() {
		return this.tax_no;
	}

	public void setTax_no(String tax_no) {
		this.tax_no = tax_no;
	}

	@Column(name = "invoice_time", nullable = false, length = 19)
	public Long getInvoice_time() {
		return this.invoice_time;
	}

	public void setInvoice_time(Long invoice_time) {
		this.invoice_time = invoice_time;
	}

	@Column(name = "invoice_type")
	public String getInvoice_type() {
		return this.invoice_type;
	}

	public void setInvoice_type(String invoice_type) {
		this.invoice_type = invoice_type;
	}

	@Column(name = "invoice_no")
	public String getInvoice_no() {
		return invoice_no;
	}

	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}

	@Column(name = "invoice_money")
	public Double getInvoice_money() {
		return invoice_money;
	}

	public void setInvoice_money(Double invoice_money) {
		this.invoice_money = invoice_money;
	}
	
	
	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "invoice_content_id")
	public InvoiceContent getInvoiceContent() {
		return invoiceContent;
	}

	public void setInvoiceContent(InvoiceContent invoiceContent) {
		this.invoiceContent = invoiceContent;
	}

	@Override
	public Object getEntityId() {
		return this.order_id;
	}
}