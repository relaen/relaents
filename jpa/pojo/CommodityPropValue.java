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
 * CommodityPropValue entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_commodity_prop_value"
    ,catalog="tiandian"
)

public class CommodityPropValue extends BasePojo implements java.io.Serializable {


    // Fields    

     private Long commodity_prop_value_id;
     private Commodity commodity;
     private CommodityProp commodityProp;
     private String prop_value;


    // Constructors

    /** default constructor */
    public CommodityPropValue() {
    }

	/** minimal constructor */
    public CommodityPropValue(Long commodity_prop_value_id, String prop_value) {
        this.commodity_prop_value_id = commodity_prop_value_id;
        this.prop_value = prop_value;
    }
    
    // Property accessors
    @Id 
    @TableGenerator(name = "COMMODITYPROPVALUE_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COMMODITYPROPVALUE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMMODITYPROPVALUE_GEN")
	@Column(name="commodity_prop_value_id", unique=true, nullable=false)
    public Long getCommodity_prop_value_id() {
        return this.commodity_prop_value_id;
    }
    
    public void setCommodity_prop_value_id(Long commodity_prop_value_id) {
        this.commodity_prop_value_id = commodity_prop_value_id;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="commodity_id")

    public Commodity getCommodity() {
        return this.commodity;
    }
    
    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="commodity_prop_id")

    public CommodityProp getCommodityProp() {
        return this.commodityProp;
    }
    
    public void setCommodityProp(CommodityProp commodityProp) {
        this.commodityProp = commodityProp;
    }
    
    @Column(name="prop_value", nullable=false, length=500)

    public String getProp_value() {
        return this.prop_value;
    }
    
    public void setProp_value(String prop_value) {
        this.prop_value = prop_value;
    }
   
    @Override
    public Object getEntityId() {
    	return this.commodity_prop_value_id;
    }


}