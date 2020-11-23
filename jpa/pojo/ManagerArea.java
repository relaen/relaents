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
 * ManagerArea entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_manager_area"
    ,catalog="tiandian"
)

public class ManagerArea extends BasePojo implements java.io.Serializable {
    private Long manager_area_id;
    private Area area;
    private SaleMan saleMan;
    // Constructors

    /** default constructor */
    public ManagerArea() {
    }

	/** minimal constructor */
    public ManagerArea(Long manager_area_id) {
        this.manager_area_id = manager_area_id;
    }
    
    /** full constructor */
    public ManagerArea(Long manager_area_id, Area area, SaleMan saleMan) {
        this.manager_area_id = manager_area_id;
        this.area = area;
        this.saleMan = saleMan;
    }
   
    // Property accessors
    @Id 
    @TableGenerator(name = "MANAGERAREA_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_MANAGERAREA", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MANAGERAREA_GEN")
    @Column(name="manager_area_id", unique=true, nullable=false)

    public Long getManager_area_id() {
        return this.manager_area_id;
    }
    
    public void setManager_area_id(Long manager_area_id) {
        this.manager_area_id = manager_area_id;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="area_id")

    public Area getArea() {
        return this.area;
    }
    
    public void setArea(Area area) {
        this.area = area;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="sale_man_id")

    public SaleMan getSaleMan() {
        return this.saleMan;
    }
    
    public void setSaleMan(SaleMan saleMan) {
        this.saleMan = saleMan;
    }
   
    @Override
    public Object getEntityId() {
    	return super.getEntityId();
    }
}