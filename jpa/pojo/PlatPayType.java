package dao.pojo;
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
 * PlatPayType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_plat_pay_type"
    ,catalog="tiandian"
)

public class PlatPayType  implements java.io.Serializable {


    // Fields    

     private Long plat_pay_type_id;
     private String type_name;
     private String remarks;
     private Set<PlatPayRcd> platPayRcds = new HashSet<PlatPayRcd>(0);


    // Constructors

    /** default constructor */
    public PlatPayType() {
    }

	/** minimal constructor */
    public PlatPayType(Long plat_pay_type_id) {
        this.plat_pay_type_id = plat_pay_type_id;
    }
    
    /** full constructor */
    public PlatPayType(Long plat_pay_type_id, String type_name, String remarks, Set<PlatPayRcd> platPayRcds) {
        this.plat_pay_type_id = plat_pay_type_id;
        this.type_name = type_name;
        this.remarks = remarks;
        this.platPayRcds = platPayRcds;
    }

   
    // Property accessors
    @Id 
    
    @Column(name="plat_pay_type_id", unique=true, nullable=false)

    public Long getPlat_pay_type_id() {
        return this.plat_pay_type_id;
    }
    
    public void setPlat_pay_type_id(Long plat_pay_type_id) {
        this.plat_pay_type_id = plat_pay_type_id;
    }
    
    @Column(name="type_name", length=50)

    public String getType_name() {
        return this.type_name;
    }
    
    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
    
    @Column(name="remarks", length=200)

    public String getRemarks() {
        return this.remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="platPayType")

    public Set<PlatPayRcd> getPlatPayRcds() {
        return this.platPayRcds;
    }
    
    public void setPlatPayRcds(Set<PlatPayRcd> platPayRcds) {
        this.platPayRcds = platPayRcds;
    }
   








}