package dao.pojo;
// default package

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


/**
 * ReturnAddress entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_return_address"
    ,catalog="tiandian"
)

public class ReturnAddress extends BasePojo implements java.io.Serializable {


    // Fields    

     private Long return_address_id;
     private Area area;
     private String address;
     private String link_man;
     private String tel;
     private String mobile;
     private Set<Warehouse> warehouses = new HashSet<Warehouse>(0);


    // Constructors

    /** default constructor */
    public ReturnAddress() {
    }

	/** minimal constructor */
    public ReturnAddress(Long return_address_id) {
        this.return_address_id = return_address_id;
    }
    
    /** full constructor */
    public ReturnAddress(Long return_address_id, Area area, String address, String link_man, String tel, String mobile, Set<Warehouse> warehouses) {
        this.return_address_id = return_address_id;
        this.area = area;
        this.address = address;
        this.link_man = link_man;
        this.tel = tel;
        this.mobile = mobile;
        this.warehouses = warehouses;
    }

   
    // Property accessors
    @Id 
    @TableGenerator(name = "RETURNADDRESS_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_RETURNADDRESS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "RETURNADDRESS_GEN")
    @Column(name="return_address_id", unique=true, nullable=false)

    public Long getReturn_address_id() {
        return this.return_address_id;
    }
    
    public void setReturn_address_id(Long return_address_id) {
        this.return_address_id = return_address_id;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="area_id")

    public Area getArea() {
        return this.area;
    }
    
    public void setArea(Area area) {
        this.area = area;
    }
    
    @Column(name="address", length=200)

    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    @Column(name="link_man", length=50)

    public String getLink_man() {
        return this.link_man;
    }
    
    public void setLink_man(String link_man) {
        this.link_man = link_man;
    }
    
    @Column(name="tel", length=15)

    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }
    
    @Column(name="mobile", length=11)

    public String getMobile() {
        return this.mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="returnAddress")

    public Set<Warehouse> getWarehouses() {
        return this.warehouses;
    }
    
    public void setWarehouses(Set<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }
   
    @Override
	public Object getEntityId() {
		return this.return_address_id;
	}
}