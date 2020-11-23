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
 * FeedbackType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_feedback_type"
    ,catalog="tiandian"
)

public class FeedbackType extends BasePojo implements java.io.Serializable {


    // Fields    

     private Long feedback_type_id;
     private String type_name;
     private Set<Feedback> feedbacks = new HashSet<Feedback>(0);


    // Constructors

    /** default constructor */
    public FeedbackType() {
    }

	/** minimal constructor */
    public FeedbackType(Long feedback_type_id) {
        this.feedback_type_id = feedback_type_id;
    }
    
    /** full constructor */
    public FeedbackType(Long feedback_type_id, String type_name, Set<Feedback> feedbacks) {
        this.feedback_type_id = feedback_type_id;
        this.type_name = type_name;
        this.feedbacks = feedbacks;
    }

   
    // Property accessors
    @Id 
    @TableGenerator(name = "FEEDBACKTYPE_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_FEEDBACKTYPE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FEEDBACKTYPE_GEN")
    @Column(name="feedback_type_id", unique=true, nullable=false)

    public Long getFeedback_type_id() {
        return this.feedback_type_id;
    }
    
    public void setFeedback_type_id(Long feedback_type_id) {
        this.feedback_type_id = feedback_type_id;
    }
    
    @Column(name="type_name", length=50)

    public String getType_name() {
        return this.type_name;
    }
    
    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="feedbackType")

    public Set<Feedback> getFeedbacks() {
        return this.feedbacks;
    }
    
    public void setFeedbacks(Set<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }
   
    @Override
	public Object getEntityId() {
		return this.feedback_type_id;
	}
}