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
 * AskSpreadCompany entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_ask_spread_company"
    ,catalog="tiandian"
)

public class AskSpreadCompany extends BasePojo implements java.io.Serializable {


    // Fields    

     private Long ask_spread_company_id;
     private Member member; // 审核人
     private SaleMan saleMan; // 申请人
     private Long ask_time;
     private Integer is_agree;
     private Long check_time;
     private String reject_reason;
     private String ask_reason;
     private Area area;
     private Set<AskSpreadCompanyRes> askSpreadCompanyReses = new HashSet<AskSpreadCompanyRes>(0);


    // Constructors

    /** default constructor */
    public AskSpreadCompany() {
    }

	/** minimal constructor */
    public AskSpreadCompany(Long ask_spread_company_id) {
        this.ask_spread_company_id = ask_spread_company_id;
    }
    
    /** full constructor */
    public AskSpreadCompany(Long ask_spread_company_id, Member member, SaleMan saleMan, Long ask_time, Integer is_agree, Long check_time, String reject_reason, String ask_reason, Set<AskSpreadCompanyRes> askSpreadCompanyReses) {
        this.ask_spread_company_id = ask_spread_company_id;
        this.member = member;
        this.saleMan = saleMan;
        this.ask_time = ask_time;
        this.is_agree = is_agree;
        this.check_time = check_time;
        this.reject_reason = reject_reason;
        this.ask_reason = ask_reason;
        this.askSpreadCompanyReses = askSpreadCompanyReses;
    }

   
    // Property accessors
    @Id 
    @TableGenerator(name = "ASKSPREADCOMPANYRES_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_ASKSPREADCOMPANYRES", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ASKSPREADCOMPANYRES_GEN")
    @Column(name="ask_spread_company_id", unique=true, nullable=false)

    public Long getAsk_spread_company_id() {
        return this.ask_spread_company_id;
    }
    
    public void setAsk_spread_company_id(Long ask_spread_company_id) {
        this.ask_spread_company_id = ask_spread_company_id;
    }
	
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="checker_id")
    public Member getMember() {
        return this.member;
    }
    
    public void setMember(Member member) {
        this.member = member;
    }
    
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="sale_man_id")
    public SaleMan getSaleMan() {
        return this.saleMan;
    }
    
    public void setSaleMan(SaleMan saleMan) {
        this.saleMan = saleMan;
    }
    
    @Column(name="ask_time")

    public Long getAsk_time() {
        return this.ask_time;
    }
    
    public void setAsk_time(Long ask_time) {
        this.ask_time = ask_time;
    }
    
    @Column(name="is_agree")

    public Integer getIs_agree() {
        return this.is_agree;
    }
    
    public void setIs_agree(Integer is_agree) {
        this.is_agree = is_agree;
    }
    
    @Column(name="check_time")

    public Long getCheck_time() {
        return this.check_time;
    }
    
    public void setCheck_time(Long check_time) {
        this.check_time = check_time;
    }
    
    @Column(name="reject_reason", length=500)

    public String getReject_reason() {
        return this.reject_reason;
    }
    
    public void setReject_reason(String reject_reason) {
        this.reject_reason = reject_reason;
    }
    
    @Column(name="ask_reason", length=500)

    public String getAsk_reason() {
        return this.ask_reason;
    }
    
    public void setAsk_reason(String ask_reason) {
        this.ask_reason = ask_reason;
    }
    
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="area_id")
    public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="askSpreadCompany")

    public Set<AskSpreadCompanyRes> getAskSpreadCompanyReses() {
        return this.askSpreadCompanyReses;
    }
    
    public void setAskSpreadCompanyReses(Set<AskSpreadCompanyRes> askSpreadCompanyReses) {
        this.askSpreadCompanyReses = askSpreadCompanyReses;
    }
   
    @Override
	public Object getEntityId() {
		return this.ask_spread_company_id;
	}
}