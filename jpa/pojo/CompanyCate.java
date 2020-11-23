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
 * CompanyCate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_company_cate"
    ,catalog="tiandian"
)

public class CompanyCate extends BasePojo implements java.io.Serializable {


    // Fields    

     private Long company_cate_id;
     private String cate_name;
     private String cate_img;
     private Set<CompanyCateRel> companyCateRels = new HashSet<CompanyCateRel>(0);


    // Constructors

    /** default constructor */
    public CompanyCate() {
    }

	/** minimal constructor */
    public CompanyCate(Long company_cate_id) {
        this.company_cate_id = company_cate_id;
    }
    
    /** full constructor */
    public CompanyCate(Long company_cate_id, String cate_name, String cate_img, Set<CompanyCateRel> companyCateRels) {
        this.company_cate_id = company_cate_id;
        this.cate_name = cate_name;
        this.cate_img = cate_img;
        this.companyCateRels = companyCateRels;
    }

   
    // Property accessors
    @Id 
    @TableGenerator(name = "COMPANYCATE_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COMPANYCATE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMPANYCATE_GEN")
    @Column(name="company_cate_id", unique=true, nullable=false)

    public Long getCompany_cate_id() {
        return this.company_cate_id;
    }
    
    public void setCompany_cate_id(Long company_cate_id) {
        this.company_cate_id = company_cate_id;
    }
    
    @Column(name="cate_name", length=50)

    public String getCate_name() {
        return this.cate_name;
    }
    
    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }
    
    @Column(name="cate_img", length=500)

    public String getCate_img() {
        return this.cate_img;
    }
    
    public void setCate_img(String cate_img) {
        this.cate_img = cate_img;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="companyCate")

    public Set<CompanyCateRel> getCompanyCateRels() {
        return this.companyCateRels;
    }
    
    public void setCompanyCateRels(Set<CompanyCateRel> companyCateRels) {
        this.companyCateRels = companyCateRels;
    }
   

    @Override
	public Object getEntityId() {
		return this.company_cate_id;
	}

}