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
 * CompanyImg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_company_img"
    ,catalog="tiandian"
)

public class CompanyImg extends BasePojo implements java.io.Serializable {


    // Fields    

     private Long company_img_id;
     private ImageResource imageResource;
     private Company company;
     private Integer img_type=5; // 1：logo；2：营业执照；3：法人身份证正面；4、法人身份证反面；5、展示图；6、组织机构代码证；7：代理人身份证正面图id；8：代理人身份证反面图id；9:法人授权书;


    // Constructors

    /** default constructor */
    public CompanyImg() {
    }

	/** minimal constructor */
    public CompanyImg(Long company_img_id) {
        this.company_img_id = company_img_id;
    }
    
    /** full constructor */
    public CompanyImg(Long company_img_id, ImageResource imageResource, Company company) {
        this.company_img_id = company_img_id;
        this.imageResource = imageResource;
        this.company = company;
    }

   
    // Property accessors
    @Id 
    @TableGenerator(name = "COMPANYIMG_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COMPANYIMG", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMPANYIMG_GEN")
	@Column(name="company_img_id", unique=true, nullable=false)

    public Long getCompany_img_id() {
        return this.company_img_id;
    }
    
    public void setCompany_img_id(Long company_img_id) {
        this.company_img_id = company_img_id;
    }
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="image_resource_id")

    public ImageResource getImageResource() {
        return this.imageResource;
    }
    
    public void setImageResource(ImageResource imageResource) {
        this.imageResource = imageResource;
    }
    
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="company_id")
    public Company getCompany() {
        return this.company;
    }
    
    public void setCompany(Company company) {
        this.company = company;
    }
    
    @Column(name="img_type")
    public Integer getImg_type() {
		return img_type;
	}

	public void setImg_type(Integer img_type) {
		this.img_type = img_type;
	}

	@Override
	public Object getEntityId() {
		return this.company_img_id;
	}

}