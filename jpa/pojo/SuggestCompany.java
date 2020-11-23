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
 * SuggestCompany entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_suggest_company"
    ,catalog="tiandian"
)

public class SuggestCompany extends BasePojo implements java.io.Serializable {
    // Fields    

     private Long suggest_company_id;
     private Company company;


    // Constructors

    /** default constructor */
    public SuggestCompany() {
    }

	/** minimal constructor */
    public SuggestCompany(Long suggest_company_id) {
        this.suggest_company_id = suggest_company_id;
    }
    
    /** full constructor */
    public SuggestCompany(Long suggest_company_id, Company company) {
        this.suggest_company_id = suggest_company_id;
        this.company = company;
    }

   
    // Property accessors
    @Id 
    @TableGenerator(name = "SUGGESTCOMPANY_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_SUGGESTCOMPANY", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SUGGESTCOMPANY_GEN")
    @Column(name="suggest_company_id", unique=true, nullable=false)

    public Long getSuggest_company_id() {
        return this.suggest_company_id;
    }
    
    public void setSuggest_company_id(Long suggest_company_id) {
        this.suggest_company_id = suggest_company_id;
    }
	
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="company_id")
    public Company getCompany() {
        return this.company;
    }
    
    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
	public Object getEntityId() {
		return this.suggest_company_id;
	}
}