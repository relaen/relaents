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
 * FlowRule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_flow_rule"
    ,catalog="tiandian"
)

public class FlowRule extends BasePojo  implements java.io.Serializable {

    // Fields    

     private Long flow_rule_id;
     private FlowType flowType;
     private Integer continue_days;
     private Integer rule_type=1;
     private Integer value;


    // Constructors

    /** default constructor */
    public FlowRule() {
    }

	/** minimal constructor */
    public FlowRule(Long flow_rule_id) {
        this.flow_rule_id = flow_rule_id;
    }
    
    // Property accessors
    @Id 
    @TableGenerator(name = "FLOWRULE_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_FLOWRULE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FLOWRULE_GEN")
    @Column(name="flow_rule_id", unique=true, nullable=false)
    public Long getFlow_rule_id() {
        return this.flow_rule_id;
    }
    
    public void setFlow_rule_id(Long flow_rule_id) {
        this.flow_rule_id = flow_rule_id;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="flow_type_id")

    public FlowType getFlowType() {
        return this.flowType;
    }
    
    public void setFlowType(FlowType flowType) {
        this.flowType = flowType;
    }
    
    @Column(name="continue_days")

    public Integer getContinue_days() {
        return this.continue_days;
    }
    
    public void setContinue_days(Integer continue_days) {
        this.continue_days = continue_days;
    }
    
   
    @Column(name="rule_type")
    public Integer getRule_type() {
        return this.rule_type;
    }
    
    public void setRule_type(Integer rule_type) {
        this.rule_type = rule_type;
    }
    
    @Column(name="value")

    public Integer getValue() {
        return this.value;
    }
    
    public void setValue(Integer value) {
        this.value = value;
    }
   
    @Override
	public Object getEntityId() {
		return this.flow_rule_id;
	}
}