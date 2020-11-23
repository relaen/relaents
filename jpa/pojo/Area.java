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
 * Area entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_area"
    ,catalog="tiandian"
)

public class Area extends BasePojo  implements java.io.Serializable {


    // Fields    

     private Long area_id;
     private Area area;
     private String area_name;
     private String areacode;
     private String zipcode;
     private Integer depth;
     private Integer is_open;		//是否开通
     private Set<Area> areas = new HashSet<Area>(0);
    // Constructors

    /** default constructor */
    public Area() {
    }

	/** minimal constructor */
    public Area(Long area_id) {
        this.area_id = area_id;
    }
    
    /** full constructor */
    public Area(Long area_id, Area area, String area_name, String areacode, String zipcode, Integer depth, Set<Area> areas) {
        this.area_id = area_id;
        this.area = area;
        this.area_name = area_name;
        this.areacode = areacode;
        this.zipcode = zipcode;
        this.depth = depth;
        this.areas = areas;
    }

   
    // Property accessors
    @Id 
    @TableGenerator(name = "AREA_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_AREA", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "AREA_GEN")
    @Column(name="area_id", unique=true, nullable=false)
    public Long getArea_id() {
        return this.area_id;
    }
    
    public void setArea_id(Long area_id) {
        this.area_id = area_id;
    }
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="parent_id")

    public Area getArea() {
        return this.area;
    }
    
    public void setArea(Area area) {
        this.area = area;
    }
    
    @Column(name="area_name", length=50)

    public String getArea_name() {
        return this.area_name;
    }
    
    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }
    
    @Column(name="areacode", length=4)

    public String getAreacode() {
        return this.areacode;
    }
    
    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }
    
    @Column(name="zipcode", length=6)

    public String getZipcode() {
        return this.zipcode;
    }
    
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    
    @Column(name="depth")

    public Integer getDepth() {
        return this.depth;
    }
    
    public void setDepth(Integer depth) {
        this.depth = depth;
    }
    
    
    @Column(name="is_open")
    public Integer getIs_open() {
		return is_open;
	}

	public void setIs_open(Integer is_open) {
		this.is_open = is_open;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="area")
    public Set<Area> getAreas() {
        return this.areas;
    }
    
    public void setAreas(Set<Area> areas) {
        this.areas = areas;
    }
   
	@Override
	public Object getEntityId() {
		return this.area_id;
	}

}