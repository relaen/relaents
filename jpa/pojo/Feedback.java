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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


/**
 * Feedback entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_feedback"
    ,catalog="tiandian"
)

public class Feedback extends BasePojo implements java.io.Serializable {


    // Fields    

     private Long feedback_id;
     private FeedbackType feedbackType;
     private String content;
     private Member member;
     private Long feedback_time;
     private Set<FeedbackRes> feedbackReses = new HashSet<FeedbackRes>(0);


    // Constructors

    /** default constructor */
    public Feedback() {
    }

	/** minimal constructor */
    public Feedback(Long feedback_id) {
        this.feedback_id = feedback_id;
    }
    
    /** full constructor */
    public Feedback(Long feedback_id, FeedbackType feedbackType, String content, Set<FeedbackRes> feedbackReses) {
        this.feedback_id = feedback_id;
        this.feedbackType = feedbackType;
        this.content = content;
        this.feedbackReses = feedbackReses;
    }

   
    // Property accessors
    @Id 
    @TableGenerator(name = "FEEDBACK_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_FEEDBACK", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FEEDBACK_GEN")
    @Column(name="feedback_id", unique=true, nullable=false)

    public Long getFeedback_id() {
        return this.feedback_id;
    }
    
    public void setFeedback_id(Long feedback_id) {
        this.feedback_id = feedback_id;
    }
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="feedback_type_id")

    public FeedbackType getFeedbackType() {
        return this.feedbackType;
    }
    
    public void setFeedbackType(FeedbackType feedbackType) {
        this.feedbackType = feedbackType;
    }
    
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="member_id")
    public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Column(name="content", length=500)

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="feedback")

    public Set<FeedbackRes> getFeedbackReses() {
        return this.feedbackReses;
    }
    
    public void setFeedbackReses(Set<FeedbackRes> feedbackReses) {
        this.feedbackReses = feedbackReses;
    }
    
    
    @Column(name="feedback_time", length=500)
    public Long getFeedback_time() {
		return feedback_time;
	}

	public void setFeedback_time(Long feedback_time) {
		this.feedback_time = feedback_time;
	}

	@Override
	public Object getEntityId() {
		return this.feedback_id;
	}
}