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
 * CompanyCateRel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_company_cate_rel"
    ,catalog="tiandian"
)

public class CompanyCateRel extends BasePojo implements java.io.Serializable {


    // Fields    

     private Long company_cate_rel_id;
     private Company company;
     private CompanyCate companyCate;


    // Constructors

    /** default constructor */
    public CompanyCateRel() {
    }

	/** minimal constructor */
    public CompanyCateRel(Long company_cate_rel_id) {
        this.company_cate_rel_id = company_cate_rel_id;
    }
    
    /** full constructor */
    public CompanyCateRel(Long company_cate_rel_id, Company company, CompanyCate companyCate) {
        this.company_cate_rel_id = company_cate_rel_id;
        this.company = company;
        this.companyCate = companyCate;
    }

   
    // Property accessors
    @Id 
    @TableGenerator(name = "COMPANYCATEREL_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COMPANYCATEREL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMPANYCATEREL_GEN")
    @Column(name="company_cate_rel_id", unique=true, nullable=false)

    public Long getCompany_cate_rel_id() {
        return this.company_cate_rel_id;
    }
    
    public void setCompany_cate_rel_id(Long company_cate_rel_id) {
        this.company_cate_rel_id = company_cate_rel_id;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="company_id")

    public Company getCompany() {
        return this.company;
    }
    
    public void setCompany(Company company) {
        this.company = company;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="company_cate_id")

    public CompanyCate getCompanyCate() {
        return this.companyCate;
    }
    
    public void setCompanyCate(CompanyCate companyCate) {
        this.companyCate = companyCate;
    }
   
    @Override
   	public Object getEntityId() {
   		return this.company_cate_rel_id;
   	}
}