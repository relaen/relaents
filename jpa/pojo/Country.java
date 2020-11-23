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
 * ReturnProcess entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_country", catalog = "tiandian")
public class Country extends BasePojo implements java.io.Serializable {

	// Fields
	private Long country_id;
	private Area area;
	private String country_flag;
	private String country_name;
	private Integer is_can_exchange;
	private Integer is_can_invoice;
	private Integer is_need_idno;
	private String country_img;
	private String country_home_img;
	private String country_title;
	private Integer enabled;

	@Override
	public Object getEntityId() {
		return this.country_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "COUNTRY_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COUNTRY", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COUNTRY_GEN")
	@Column(name = "country_id", unique = true, nullable = false)
	public Long getCountry_id() {
		return country_id;
	}

	public void setCountry_id(Long country_id) {
		this.country_id = country_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_id")
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@Column(name = "country_flag")
	public String getCountry_flag() {
		return country_flag;
	}

	public void setCountry_flag(String country_flag) {
		this.country_flag = country_flag;
	}

	@Column(name = "country_name")
	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	@Column(name = "is_can_exchange")
	public Integer getIs_can_exchange() {
		return is_can_exchange;
	}

	public void setIs_can_exchange(Integer is_can_exchange) {
		this.is_can_exchange = is_can_exchange;
	}

	@Column(name = "is_can_invoice")
	public Integer getIs_can_invoice() {
		return is_can_invoice;
	}

	public void setIs_can_invoice(Integer is_can_invoice) {
		this.is_can_invoice = is_can_invoice;
	}

	@Column(name = "is_need_idno")
	public Integer getIs_need_idno() {
		return is_need_idno;
	}

	public void setIs_need_idno(Integer is_need_idno) {
		this.is_need_idno = is_need_idno;
	}

	@Column(name = "country_img")
	public String getCountry_img() {
		return country_img;
	}

	public void setCountry_img(String country_img) {
		this.country_img = country_img;
	}

	@Column(name = "country_home_img")
	public String getCountry_home_img() {
		return country_home_img;
	}

	public void setCountry_home_img(String country_home_img) {
		this.country_home_img = country_home_img;
	}

	@Column(name = "country_title")
	public String getCountry_title() {
		return country_title;
	}

	public void setCountry_title(String country_title) {
		this.country_title = country_title;
	}

	@Column(name = "enabled")
	public Integer getEnabled() {
		return enabled;
	}
	
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

}