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
 * GrowTime entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_grow_time"
    ,catalog="tiandian"
)

public class GrowTime extends BasePojo implements java.io.Serializable {


    // Fields    

     private Long grow_time_id;
     private Member member;
     private Long recency_time;
     private Long frequency_time;
     private Long signin_time;

    // Constructors

    /** default constructor */
    public GrowTime() {
    }

	/** minimal constructor */
    public GrowTime(Long grow_time_id, Member member) {
        this.grow_time_id = grow_time_id;
        this.member = member;
    }
    
    /** full constructor */
    public GrowTime(Long grow_time_id, Member member, Long recency_time, Long frequency_time) {
        this.grow_time_id = grow_time_id;
        this.member = member;
        this.recency_time = recency_time;
        this.frequency_time = frequency_time;
    }

   
    // Property accessors
    @Id 
    @TableGenerator(name = "GROWTIME_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_GROWTIME", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GROWTIME_GEN")
	@Column(name="grow_time_id", unique=true, nullable=false)
    public Long getGrow_time_id() {
        return this.grow_time_id;
    }
    
    public void setGrow_time_id(Long grow_time_id) {
        this.grow_time_id = grow_time_id;
    }
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="member_id", nullable=false)

    public Member getMember() {
        return this.member;
    }
    
    public void setMember(Member member) {
        this.member = member;
    }
    
    @Column(name="recency_time")

    public Long getRecency_time() {
        return this.recency_time;
    }
    
    public void setRecency_time(Long recency_time) {
        this.recency_time = recency_time;
    }
    
    @Column(name="frequency_time")

    public Long getFrequency_time() {
        return this.frequency_time;
    }
    
    public void setFrequency_time(Long frequency_time) {
        this.frequency_time = frequency_time;
    }
    
    @Column(name="signin_time")
    public Long getSignin_time() {
		return signin_time;
	}

	public void setSignin_time(Long signin_time) {
		this.signin_time = signin_time;
	}

	@Override
	public Object getEntityId() {
		return this.grow_time_id;
	}
}