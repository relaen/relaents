package dao.pojo;

// default package
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * Address entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_address", catalog = "tiandian")
public class Address extends BasePojo implements java.io.Serializable {
	// Fields
	private Long address_id;
	private Member member;
	private String address_name;
	private String zipcode;
	private String mobile;
	private String mobile1;
	private String link_man;
	private Integer is_default = 0;
	private String address;
	private String id_no;

	// Constructors
	/** default constructor */
	public Address() {
	}

	/** minimal constructor */
	public Address(Long address_id) {
		this.address_id = address_id;
	}

	/** full constructor */
	public Address(Long address_id, Member member, String address_name, String zipcode, String mobile, String mobile1,
			String link_man, Integer is_default) {
		this.address_id = address_id;
		this.member = member;
		this.address_name = address_name;
		this.zipcode = zipcode;
		this.mobile = mobile;
		this.mobile1 = mobile1;
		this.link_man = link_man;
		this.is_default = is_default;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "ADDRESS_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_ADDRESS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ADDRESS_GEN")
	@Column(name = "address_id", unique = true, nullable = false)
	public Long getAddress_id() {
		return this.address_id;
	}

	public void setAddress_id(Long address_id) {
		this.address_id = address_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Column(name = "address_name", length = 500)
	public String getAddress_name() {
		return this.address_name;
	}

	public void setAddress_name(String address_name) {
		this.address_name = address_name;
	}

	@Column(name = "zipcode", length = 6)
	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Column(name = "mobile", length = 11)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "mobile1", length = 15)
	public String getMobile1() {
		return this.mobile1;
	}

	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}

	@Column(name = "link_man", length = 50)
	public String getLink_man() {
		return this.link_man;
	}

	public void setLink_man(String link_man) {
		this.link_man = link_man;
	}

	@Column(name = "is_default")
	public Integer getIs_default() {
		return this.is_default;
	}

	public void setIs_default(Integer is_default) {
		this.is_default = is_default;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
		return this.address_id;
	}
}