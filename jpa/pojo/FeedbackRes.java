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
 * FeedbackRes entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_feedback_res"
    ,catalog="tiandian"
)

public class FeedbackRes extends BasePojo implements java.io.Serializable {


    // Fields    

     private Long feedback_res_id;
     private Feedback feedback;
     private ImageResource imageResource;


    // Constructors

    /** default constructor */
    public FeedbackRes() {
    }

	/** minimal constructor */
    public FeedbackRes(Long feedback_res_id) {
        this.feedback_res_id = feedback_res_id;
    }
    
    /** full constructor */
    public FeedbackRes(Long feedback_res_id, Feedback feedback, ImageResource imageResource) {
        this.feedback_res_id = feedback_res_id;
        this.feedback = feedback;
        this.imageResource = imageResource;
    }

   
    // Property accessors
    @Id 
    @TableGenerator(name = "FEEDBACKRES_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_FEEDBACKRES", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FEEDBACKRES_GEN")
    @Column(name="feedback_res_id", unique=true, nullable=false)

    public Long getFeedback_res_id() {
        return this.feedback_res_id;
    }
    
    public void setFeedback_res_id(Long feedback_res_id) {
        this.feedback_res_id = feedback_res_id;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="feedback_id")

    public Feedback getFeedback() {
        return this.feedback;
    }
    
    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
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
		return this.feedback_res_id;
	}
}