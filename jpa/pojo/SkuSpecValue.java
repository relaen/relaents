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
 * SkuSpecValue entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_sku_spec_value"
    ,catalog="tiandian"
)

public class SkuSpecValue extends BasePojo implements java.io.Serializable {


    // Fields    

     private Long sku_spec_value_id;
     private SpecValue specValue;
     private Sku sku;


    // Constructors

    /** default constructor */
    public SkuSpecValue() {
    }

	/** minimal constructor */
    public SkuSpecValue(Long sku_spec_value_id) {
        this.sku_spec_value_id = sku_spec_value_id;
    }
    
    /** full constructor */
    public SkuSpecValue(Long sku_spec_value_id, SpecValue specValue, Sku sku) {
        this.sku_spec_value_id = sku_spec_value_id;
        this.specValue = specValue;
        this.sku = sku;
    }

   
    // Property accessors
    @Id 
    @TableGenerator(name = "SKUSPECVALUE_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_SKUSPECVALUE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SKUSPECVALUE_GEN")
    @Column(name="sku_spec_value_id", unique=true, nullable=false)

    public Long getSku_spec_value_id() {
        return this.sku_spec_value_id;
    }
    
    public void setSku_spec_value_id(Long sku_spec_value_id) {
        this.sku_spec_value_id = sku_spec_value_id;
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
        @JoinColumn(name="sku_id")

    public Sku getSku() {
        return this.sku;
    }
    
    public void setSku(Sku sku) {
        this.sku = sku;
    }
   
    @Override
    public Object getEntityId() {
    	return this.getSku_spec_value_id();
    }

}