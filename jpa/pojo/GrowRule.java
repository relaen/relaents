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
 * GrowRule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_grow_rule"
    ,catalog="tiandian"
)

public class GrowRule extends BasePojo  implements java.io.Serializable {


    // Fields    

     private Long grow_rule_id;
     private GrowType growType;
     private Double low_money;
     private Double high_money;
     private Integer cycle_count;
     private Double cycle_money;
     private Integer min_days;
     private Integer max_days;
     private Integer rule_type;
     private Integer continue_days;
     private Integer grow_value;
     private Integer blankdays;
     private Double consume_scale = 1d;


    // Constructors

    /** default constructor */
    public GrowRule() {
    }

	/** minimal constructor */
    public GrowRule(Long grow_rule_id) {
        this.grow_rule_id = grow_rule_id;
    }
    
    // Property accessors
    @Id 
    @TableGenerator(name = "GROWRULE_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_GROWRULE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GROWRULE_GEN")
    @Column(name="grow_rule_id", unique=true, nullable=false)

    public Long getGrow_rule_id() {
        return this.grow_rule_id;
    }
    
    public void setGrow_rule_id(Long grow_rule_id) {
        this.grow_rule_id = grow_rule_id;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="grow_type_id")

    public GrowType getGrowType() {
        return this.growType;
    }
    
    public void setGrowType(GrowType growType) {
        this.growType = growType;
    }
    
    @Column(name="low_money", precision=13)

    public Double getLow_money() {
        return this.low_money;
    }
    
    public void setLow_money(Double low_money) {
        this.low_money = low_money;
    }
    
    @Column(name="high_money", precision=13)

    public Double getHigh_money() {
        return this.high_money;
    }
    
    public void setHigh_money(Double high_money) {
        this.high_money = high_money;
    }
    
    @Column(name="cycle_count")

    public Integer getCycle_count() {
        return this.cycle_count;
    }
    
    public void setCycle_count(Integer cycle_count) {
        this.cycle_count = cycle_count;
    }
    
    @Column(name="cycle_money", precision=13)

    public Double getCycle_money() {
        return this.cycle_money;
    }
    
    public void setCycle_money(Double cycle_money) {
        this.cycle_money = cycle_money;
    }
    
    @Column(name="min_days")

    public Integer getMin_days() {
        return this.min_days;
    }
    
    public void setMin_days(Integer min_days) {
        this.min_days = min_days;
    }
    
    @Column(name="max_days")

    public Integer getMax_days() {
        return this.max_days;
    }
    
    public void setMax_days(Integer max_days) {
        this.max_days = max_days;
    }
    
    @Column(name="rule_type")

    public Integer getRule_type() {
        return this.rule_type;
    }
    
    public void setRule_type(Integer rule_type) {
        this.rule_type = rule_type;
    }
    
    @Column(name="continue_days")

    public Integer getContinue_days() {
        return this.continue_days;
    }
    
    public void setContinue_days(Integer continue_days) {
        this.continue_days = continue_days;
    }
    
    @Column(name="grow_value")

    public Integer getGrow_value() {
        return this.grow_value;
    }
    
    public void setGrow_value(Integer grow_value) {
        this.grow_value = grow_value;
    }
    
    @Column(name="blankdays")

    public Integer getBlankdays() {
        return this.blankdays;
    }
    
    public void setBlankdays(Integer blankdays) {
        this.blankdays = blankdays;
    }
    
    
    @Column(name="consume_scale")
    public Double getConsume_scale() {
		return consume_scale;
	}

	public void setConsume_scale(Double consume_scale) {
		this.consume_scale = consume_scale;
	}

	@Override
	public Object getEntityId() {
		return this.grow_rule_id;
	}
}