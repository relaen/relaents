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
 * AskSpreadCompanyRes entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_ask_spread_company_res"
    ,catalog="tiandian"
)

public class AskSpreadCompanyRes extends BasePojo implements java.io.Serializable {


    // Fields    

     private Long ask_spread_company_res_id;
     private AskSpreadCompany askSpreadCompany;
     private ImageResource imageResource;


    // Constructors

    /** default constructor */
    public AskSpreadCompanyRes() {
    }

	/** minimal constructor */
    public AskSpreadCompanyRes(Long ask_spread_company_res_id) {
        this.ask_spread_company_res_id = ask_spread_company_res_id;
    }
    
    /** full constructor */
    public AskSpreadCompanyRes(Long ask_spread_company_res_id, AskSpreadCompany askSpreadCompany, ImageResource imageResource) {
        this.ask_spread_company_res_id = ask_spread_company_res_id;
        this.askSpreadCompany = askSpreadCompany;
        this.imageResource = imageResource;
    }

   
    // Property accessors
    @Id 
    @TableGenerator(name = "ASKSPREADCOMPANY_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_ASKSPREADCOMPANY", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ASKSPREADCOMPANY_GEN")
    @Column(name="ask_spread_company_res_id", unique=true, nullable=false)

    public Long getAsk_spread_company_res_id() {
        return this.ask_spread_company_res_id;
    }
    
    public void setAsk_spread_company_res_id(Long ask_spread_company_res_id) {
        this.ask_spread_company_res_id = ask_spread_company_res_id;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="ask_spread_company_id")

    public AskSpreadCompany getAskSpreadCompany() {
        return this.askSpreadCompany;
    }
    
    public void setAskSpreadCompany(AskSpreadCompany askSpreadCompany) {
        this.askSpreadCompany = askSpreadCompany;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="image_resource_id")

    public ImageResource getImageResource() {
        return this.imageResource;
    }
    
    public void setImageResource(ImageResource imageResource) {
        this.imageResource = imageResource;
    }
   
    @Override
	public Object getEntityId() {
		return this.ask_spread_company_res_id;
	}
}