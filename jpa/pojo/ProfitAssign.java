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
 * ProfitAssign entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_profit_assign"
    ,catalog="tiandian"
)

public class ProfitAssign extends BasePojo implements java.io.Serializable {


    // Fields    

     private Long profit_assign_id;
     private Orders orders;
     private DevideType devideType;
     private SaleMan saleMan;
     private Long assign_time;
     private Double money;
     private Double ratio;


    // Constructors

    /** default constructor */
    public ProfitAssign() {
    }

	/** minimal constructor */
    public ProfitAssign(Long profit_assign_id) {
        this.profit_assign_id = profit_assign_id;
    }
    
    /** full constructor */
    public ProfitAssign(Long profit_assign_id, Orders orders, DevideType devideType, SaleMan saleMan, Long assign_time, Double money, Double ratio) {
        this.profit_assign_id = profit_assign_id;
        this.orders = orders;
        this.devideType = devideType;
        this.saleMan = saleMan;
        this.assign_time = assign_time;
        this.money = money;
        this.ratio = ratio;
    }

   
    // Property accessors
    @Id 
    @TableGenerator(name = "PROFITASSIGN_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_PROFITASSIGN", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PROFITASSIGN_GEN")
	@Column(name="profit_assign_id", unique=true, nullable=false)

    public Long getProfit_assign_id() {
        return this.profit_assign_id;
    }
    
    public void setProfit_assign_id(Long profit_assign_id) {
        this.profit_assign_id = profit_assign_id;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="order_id")

    public Orders getOrders() {
        return this.orders;
    }
    
    public void setOrders(Orders orders) {
        this.orders = orders;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="devide_type_id")

    public DevideType getDevideType() {
        return this.devideType;
    }
    
    public void setDevideType(DevideType devideType) {
        this.devideType = devideType;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="sale_man_id")

    public SaleMan getSaleMan() {
        return this.saleMan;
    }
    
    public void setSaleMan(SaleMan saleMan) {
        this.saleMan = saleMan;
    }
    
    @Column(name="assign_time")

    public Long getAssign_time() {
        return this.assign_time;
    }
    
    public void setAssign_time(Long assign_time) {
        this.assign_time = assign_time;
    }
    
    @Column(name="money", precision=13)

    public Double getMoney() {
        return this.money;
    }
    
    public void setMoney(Double money) {
        this.money = money;
    }
    
    @Column(name="ratio", precision=5, scale=3)

    public Double getRatio() {
        return this.ratio;
    }
    
    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }
   
    @Override
	public Object getEntityId() {
		return this.profit_assign_id;
	}
}