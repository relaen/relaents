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
 * GivebiRes entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_givebi_res"
    ,catalog="tiandian"
)

public class GivebiRes extends BasePojo implements java.io.Serializable {
    // Fields    
    private Long givebi_res_id;
    private ImageResource imageResource;
    private GivebiRcd givebiRcd;

    // Constructors

    /** default constructor */
    public GivebiRes() {
    }

	/** minimal constructor */
    public GivebiRes(Long givebi_res_id) {
        this.givebi_res_id = givebi_res_id;
    }
    
    /** full constructor */
    public GivebiRes(Long givebi_res_id, ImageResource imageResource, GivebiRcd givebiRcd) {
        this.givebi_res_id = givebi_res_id;
        this.imageResource = imageResource;
        this.givebiRcd = givebiRcd;
    }

   
    // Property accessors
    @Id 
    @TableGenerator(name = "GIVEBIRES_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_GIVEBIRES", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GIVEBIRES_GEN")
    @Column(name="givebi_res_id", unique=true, nullable=false)
    public Long getGivebi_res_id() {
        return this.givebi_res_id;
    }
    
    public void setGivebi_res_id(Long givebi_res_id) {
        this.givebi_res_id = givebi_res_id;
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
        @JoinColumn(name="givebi_rcd_id")

    public GivebiRcd getGivebiRcd() {
        return this.givebiRcd;
    }
    
    public void setGivebiRcd(GivebiRcd givebiRcd) {
        this.givebiRcd = givebiRcd;
    }
   
    @Override
	public Object getEntityId() {
		return this.givebi_res_id;
	}
}