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
 * DailyBook entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_daily_book"
    ,catalog="tiandian"
)

public class DailyBook extends BasePojo implements java.io.Serializable {


    // Fields    

     private String daily_book_id;
     private Double assign_center=0d;
     private Double other_profit=0d;
     private Double yuan_fund=0d;
     private Double self_profit=0d;
     private Double yuan_payment=0d;
     private Double fen_payment=0d;
     private Double self_adjust=0d;
     private Double other_adjust=0d;
     private Double bussiness_cash=0d;
     private Double daily_assign_center=0d;
     private Double daily_other_profit=0d;
     private Double daily_yuan_fund=0d;
     private Double daily_self_profit=0d;
     private Double daily_yuan_payment=0d;
     private Double daily_fen_payment=0d;
     private Double daily_self_adjust=0d;
     private Double daily_other_adjust=0d;
     private Double daily_bussiness_cash=0d;
     private String book_date;


    // Constructors

    /** default constructor */
    public DailyBook() {
    }

	/** minimal constructor */
    public DailyBook(String daily_book_id) {
        this.daily_book_id = daily_book_id;
    }
    
   
    // Property accessors
    @Id 
    @TableGenerator(name = "DAILYBOOK_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_DAILYBOOK", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "DAILYBOOK_GEN")
    @Column(name="daily_book_id", unique=true, nullable=false, length=10)

    public String getDaily_book_id() {
        return this.daily_book_id;
    }
    
    public void setDaily_book_id(String daily_book_id) {
        this.daily_book_id = daily_book_id;
    }
    
    @Column(name="assign_center", precision=13)

    public Double getAssign_center() {
        return this.assign_center;
    }
    
    public void setAssign_center(Double assign_center) {
        this.assign_center = assign_center;
    }
    
    @Column(name="other_profit", precision=13)

    public Double getOther_profit() {
        return this.other_profit;
    }
    
    public void setOther_profit(Double other_profit) {
        this.other_profit = other_profit;
    }
    
    @Column(name="yuan_fund", precision=13)

    public Double getYuan_fund() {
        return this.yuan_fund;
    }
    
    public void setYuan_fund(Double yuan_fund) {
        this.yuan_fund = yuan_fund;
    }
    
    @Column(name="fen_payment", precision=13)
    public Double getFen_payment() {
		return fen_payment;
	}

	public void setFen_payment(Double fen_payment) {
		this.fen_payment = fen_payment;
	}
    
    
    @Column(name="self_profit", precision=13)

    public Double getSelf_profit() {
        return this.self_profit;
    }
    
    public void setSelf_profit(Double self_profit) {
        this.self_profit = self_profit;
    }
    
    @Column(name="yuan_payment", precision=13)

    public Double getYuan_payment() {
        return this.yuan_payment;
    }
    
    public void setYuan_payment(Double yuan_payment) {
        this.yuan_payment = yuan_payment;
    }
    
    @Column(name="self_adjust", precision=13)

    public Double getSelf_adjust() {
        return this.self_adjust;
    }
    
    public void setSelf_adjust(Double self_adjust) {
        this.self_adjust = self_adjust;
    }
    
    @Column(name="other_adjust", precision=13)

    public Double getOther_adjust() {
        return this.other_adjust;
    }
    
    public void setOther_adjust(Double other_adjust) {
        this.other_adjust = other_adjust;
    }
    
    @Column(name="bussiness_cash", precision=13)

    public Double getBussiness_cash() {
        return this.bussiness_cash;
    }
    
    public void setBussiness_cash(Double bussiness_cash) {
        this.bussiness_cash = bussiness_cash;
    }
    
    
    @Column(name="daily_assign_center", precision=13)
	public Double getDaily_assign_center() {
		return daily_assign_center;
	}

	public void setDaily_assign_center(Double daily_assign_center) {
		this.daily_assign_center = daily_assign_center;
	}

	@Column(name="daily_other_profit", precision=13)
	public Double getDaily_other_profit() {
		return daily_other_profit;
	}

	public void setDaily_other_profit(Double daily_other_profit) {
		this.daily_other_profit = daily_other_profit;
	}

	@Column(name="daily_yuan_fund", precision=13)
	public Double getDaily_yuan_fund() {
		return daily_yuan_fund;
	}

	public void setDaily_yuan_fund(Double daily_yuan_fund) {
		this.daily_yuan_fund = daily_yuan_fund;
	}

	@Column(name="daily_self_profit", precision=13)
	public Double getDaily_self_profit() {
		return daily_self_profit;
	}

	public void setDaily_self_profit(Double daily_self_profit) {
		this.daily_self_profit = daily_self_profit;
	}

	@Column(name="daily_yuan_payment", precision=13)
	public Double getDaily_yuan_payment() {
		return daily_yuan_payment;
	}

	public void setDaily_yuan_payment(Double daily_yuan_payment) {
		this.daily_yuan_payment = daily_yuan_payment;
	}

	@Column(name="daily_fen_payment", precision=13)
	public Double getDaily_fen_payment() {
		return daily_fen_payment;
	}

	public void setDaily_fen_payment(Double daily_fen_payment) {
		this.daily_fen_payment = daily_fen_payment;
	}

	@Column(name="daily_self_adjust", precision=13)
	public Double getDaily_self_adjust() {
		return daily_self_adjust;
	}

	public void setDaily_self_adjust(Double daily_self_adjust) {
		this.daily_self_adjust = daily_self_adjust;
	}

	@Column(name="daily_other_adjust", precision=13)
	public Double getDaily_other_adjust() {
		return daily_other_adjust;
	}

	public void setDaily_other_adjust(Double daily_other_adjust) {
		this.daily_other_adjust = daily_other_adjust;
	}

	@Column(name="daily_bussiness_cash", precision=13)
	public Double getDaily_bussiness_cash() {
		return daily_bussiness_cash;
	}

	public void setDaily_bussiness_cash(Double daily_bussiness_cash) {
		this.daily_bussiness_cash = daily_bussiness_cash;
	}

	@Column(name="book_date", length=8)

    public String getBook_date() {
        return this.book_date;
    }
    
    public void setBook_date(String book_date) {
        this.book_date = book_date;
    }
   
    @Override
	public Object getEntityId() {
		return this.daily_book_id;
	}
}