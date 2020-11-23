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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


/**
 * FlowType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_flow_type"
    ,catalog="tiandian"
)

public class FlowType extends BasePojo implements java.io.Serializable {


    // Fields    

     private Long flow_type_id;
     private String type_name;
     private Integer belongto;
     private Double num;
     private String trigger_name;
     private String method_name;
     private String simple_name;
     private String remarks;
     
     private Set<WalletFlow> walletFlows = new HashSet<WalletFlow>(0);
     private Set<FlowRule> flowRules = new HashSet<FlowRule>(0);

    // Constructors

    /** default constructor */
    public FlowType() {
    }

	/** minimal constructor */
    public FlowType(Long flow_type_id) {
        this.flow_type_id = flow_type_id;
    }
    
    /** full constructor */
    public FlowType(Long flow_type_id, String type_name, String remarks, Set<WalletFlow> walletFlows) {
        this.flow_type_id = flow_type_id;
        this.type_name = type_name;
        this.remarks = remarks;
        this.walletFlows = walletFlows;
    }

   
    // Property accessors
    @Id 
    @TableGenerator(name = "FLOWTYPE_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_FLOWTYPE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FLOWTYPE_GEN")
    @Column(name="flow_type_id", unique=true, nullable=false)

    public Long getFlow_type_id() {
        return this.flow_type_id;
    }
    
    public void setFlow_type_id(Long flow_type_id) {
        this.flow_type_id = flow_type_id;
    }
    
    @Column(name="type_name", length=50)

    public String getType_name() {
        return this.type_name;
    }
    
    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
    
    
    @Column(name="belongto")
    public Integer getBelongto() {
		return belongto;
	}

	public void setBelongto(Integer belongto) {
		this.belongto = belongto;
	}

	@Column(name="remarks", length=500)

    public String getRemarks() {
        return this.remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    @Column(name="simple_name", length=50)
    public String getSimple_name() {
		return simple_name;
	}

	public void setSimple_name(String simple_name) {
		this.simple_name = simple_name;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="flowType")
    public Set<WalletFlow> getWalletFlows() {
        return this.walletFlows;
    }
    
    public void setWalletFlows(Set<WalletFlow> walletFlows) {
        this.walletFlows = walletFlows;
    }
    
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="flowType")
    public Set<FlowRule> getFlowRules() {
		return flowRules;
	}

	public void setFlowRules(Set<FlowRule> flowRules) {
		this.flowRules = flowRules;
	}

	@Column(name="num")
    public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}

	@Column(name="trigger_name")
	public String getTrigger_name() {
		return trigger_name;
	}

	public void setTrigger_name(String trigger_name) {
		this.trigger_name = trigger_name;
	}
	
	@Column(name = "method_name", length = 50)
	public String getMethod_name() {
		return method_name;
	}

	public void setMethod_name(String method_name) {
		this.method_name = method_name;
	}
	@Override
	public Object getEntityId() {
		return this.flow_type_id;
	}
}