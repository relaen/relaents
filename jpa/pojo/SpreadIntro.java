package dao.pojo;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


/**
 * SpreadIntro entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_spread_intro"
    ,catalog="tiandian"
)

public class SpreadIntro extends BasePojo implements java.io.Serializable {


    // Fields    

     private Long spread_intro_id;
     private String title;
     private String content;


    // Constructors

    /** default constructor */
    public SpreadIntro() {
    }

	/** minimal constructor */
    public SpreadIntro(Long spread_intro_id) {
        this.spread_intro_id = spread_intro_id;
    }
    
    /** full constructor */
    public SpreadIntro(Long spread_intro_id, String title, String content) {
        this.spread_intro_id = spread_intro_id;
        this.title = title;
        this.content = content;
    }

   
    // Property accessors
    @Id 
    @TableGenerator(name = "SPREADINTRO_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_SPREADINTRO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SPREADINTRO_GEN")
    @Column(name="spread_intro_id", unique=true, nullable=false)
    public Long getSpread_intro_id() {
        return this.spread_intro_id;
    }
    
    public void setSpread_intro_id(Long spread_intro_id) {
        this.spread_intro_id = spread_intro_id;
    }
    
    @Column(name="title", length=50)

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Column(name="content", length=65535)

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
   
    @Override
	public Object getEntityId() {
		return this.spread_intro_id;
	}

}