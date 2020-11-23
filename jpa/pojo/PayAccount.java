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
 * PayAccount entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_pay_account"
    ,catalog="tiandian"
)

public class PayAccount extends BasePojo  implements java.io.Serializable {


    // Fields    

     private Long pay_account_id;
     private PayStyle payStyle;
     private Member member;
     private String account_no;
     private Integer enabled=1;


    // Constructors

    /** default constructor */
    public PayAccount() {
    }

	/** minimal constructor */
    public PayAccount(Long pay_account_id) {
        this.pay_account_id = pay_account_id;
    }
    
    /** full constructor */
    public PayAccount(Long pay_account_id, PayStyle payStyle, Member member, String account_no, Integer enabled) {
        this.pay_account_id = pay_account_id;
        this.payStyle = payStyle;
        this.member = member;
        this.account_no = account_no;
        this.enabled = enabled;
    }

   
    // Property accessors
    @Id 
    @TableGenerator(name = "PAYACCOUNT_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_PAYACCOUNT", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PAYACCOUNT_GEN")
	@Column(name="pay_account_id", unique=true, nullable=false)

    public Long getPay_account_id() {
        return this.pay_account_id;
    }
    
    public void setPay_account_id(Long pay_account_id) {
        this.pay_account_id = pay_account_id;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="pay_style_id")

    public PayStyle getPayStyle() {
        return this.payStyle;
    }
    
    public void setPayStyle(PayStyle payStyle) {
        this.payStyle = payStyle;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="member_id")

    public Member getMember() {
        return this.member;
    }
    
    public void setMember(Member member) {
        this.member = member;
    }
    
    @Column(name="account_no", length=50)

    public String getAccount_no() {
        return this.account_no;
    }
    
    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }
    
    @Column(name="enabled")

    public Integer getEnabled() {
        return this.enabled;
    }
    
    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
   
    @Override
	public Object getEntityId() {
		return this.pay_account_id;
	}
}