package dao.pojo;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * PlatWallet entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_plat_wallet"
    ,catalog="tiandian"
)

public class PlatWallet extends BasePojo implements java.io.Serializable {


    // Fields    

     private Long plat_wallet_id;
     private Double assign_center=0d;
     private Double other_profit=0d;
     private Double yuan_fund=0d;
     private Double fen_payment=0d;
     private Double self_profit=0d;
     private Double yuan_payment=0d;
     private Double self_adjust=0d;
     private Double other_adjust=0d;
     private Double bussiness_cash=0d;


    // Constructors

    /** default constructor */
    public PlatWallet() {
    }

	/** minimal constructor */
    public PlatWallet(Long plat_wallet_id) {
        this.plat_wallet_id = plat_wallet_id;
    }
    
    // Property accessors
    @Id 
    @Column(name="plat_wallet_id", unique=true, nullable=false)

    public Long getPlat_wallet_id() {
        return this.plat_wallet_id;
    }
    
    public void setPlat_wallet_id(Long plat_wallet_id) {
        this.plat_wallet_id = plat_wallet_id;
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
   

    @Override
	public Object getEntityId() {
		return this.plat_wallet_id;
	}

}