package dao.pojo;

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
 * TOrderAddress entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_order_address", catalog = "tiandian")

public class OrderAddress extends BasePojo implements java.io.Serializable {

	// Fields

	private Long order_id;
	private Orders orders;
	private String address;
	private String person;
	private String mobile;
	private String tel;
	private String zipcode;
	private String id_no;

	// Constructors

	/** default constructor */
	public OrderAddress() {
	}

	// Property accessors
	@Id
	@Column(name = "order_id", unique = true, nullable = false)

	public Long getOrder_id() {
		return this.order_id;
	}

	public void setOrder_id(Long orderId) {
		this.order_id = orderId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Orders getOrders() {
		return this.orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	@Column(name = "address", length = 200)

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "person", length = 50)

	public String getPerson() {
		return this.person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	@Column(name = "mobile", length = 11)

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "tel", length = 15)

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "zipcode", length = 15)

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Column(name = "id_no")
	public String getId_no() {
		return id_no;
	}

	public void setId_no(String id_no) {
		this.id_no = id_no;
	}

	@Override
	public Object getEntityId() {
		return this.order_id;
	}
}