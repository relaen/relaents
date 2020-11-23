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
 * AskResource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_ask_resource"
    ,catalog="tiandian"
)

public class AskResource extends BasePojo implements java.io.Serializable {


    // Fields    
     private Long ask_resource_id;
     private ImageResource imageResource;
     private BeSaleman beSaleman;


    // Constructors

    /** default constructor */
    public AskResource() {
    }

	/** minimal constructor */
    public AskResource(Long ask_resource_id) {
        this.ask_resource_id = ask_resource_id;
    }
    
    /** full constructor */
    public AskResource(Long ask_resource_id, ImageResource imageResource, BeSaleman beSaleman) {
        this.ask_resource_id = ask_resource_id;
        this.imageResource = imageResource;
        this.beSaleman = beSaleman;
    }

   
    // Property accessors
    @Id 
    @TableGenerator(name = "ASKRESOURCE_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_ASKRESOURCE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ASKRESOURCE_GEN")
    @Column(name="ask_resource_id", unique=true, nullable=false)
    public Long getAsk_resource_id() {
        return this.ask_resource_id;
    }
    
    public void setAsk_resource_id(Long ask_resource_id) {
        this.ask_resource_id = ask_resource_id;
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
    @JoinColumn(name="be_saleman_id")
    public BeSaleman getBeSaleman() {
        return this.beSaleman;
    }
    
    public void setBeSaleman(BeSaleman beSaleman) {
        this.beSaleman = beSaleman;
    }
   
    @Override
	public Object getEntityId() {
		return this.ask_resource_id;
	}

}