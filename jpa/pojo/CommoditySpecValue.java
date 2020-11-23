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
 * CommoditySpecValue entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_commodity_spec_value"
    ,catalog="tiandian"
)

public class CommoditySpecValue extends BasePojo implements java.io.Serializable {
    
	// Fields    

     private Long commodity_spec_value_id;
     private SpecValue specValue;
     private CommoditySpec commoditySpec;


    // Constructors

    /** default constructor */
    public CommoditySpecValue() {
    }

	/** minimal constructor */
    public CommoditySpecValue(Long commodity_spec_value_id) {
        this.commodity_spec_value_id = commodity_spec_value_id;
    }
    
    /** full constructor */
    public CommoditySpecValue(Long commodity_spec_value_id, SpecValue specValue, CommoditySpec commoditySpec) {
        this.commodity_spec_value_id = commodity_spec_value_id;
        this.specValue = specValue;
        this.commoditySpec = commoditySpec;
    }

   
    // Property accessors
    @Id 
    @TableGenerator(name = "COMMODITYSPECVALUE_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COMMODITYSPECVALUE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMMODITYSPECVALUE_GEN")
    @Column(name="commodity_spec_value_id", unique=true, nullable=false)
    public Long getCommodity_spec_value_id() {
        return this.commodity_spec_value_id;
    }
    
    public void setCommodity_spec_value_id(Long commodity_spec_value_id) {
        this.commodity_spec_value_id = commodity_spec_value_id;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="spec_value_id")

    public SpecValue getSpecValue() {
        return this.specValue;
    }
    
    public void setSpecValue(SpecValue specValue) {
        this.specValue = specValue;
    }
	
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="commodity_spec_id")
    public CommoditySpec getCommoditySpec() {
        return this.commoditySpec;
    }
    
    public void setCommoditySpec(CommoditySpec commoditySpec) {
        this.commoditySpec = commoditySpec;
    }
   
    @Override
    public Object getEntityId() {
    	return this.commodity_spec_value_id;
    }
    
}