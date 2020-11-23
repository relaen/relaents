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
 * PlatDayliCac entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_plat_daily_cac"
    ,catalog="tiandian"
)

public class PlatDailyCac extends BasePojo implements java.io.Serializable {


    // Fields    

     private Long plat_daily_cac_id;
     private Integer gen_fen = 0;
     private Integer assign_fen = 0;
     private Integer unassign_fen = 0;
     private Integer add_rights = 0;
     private Integer sub_rights = 0;
     private Integer give_rights = 0;
     private Double real_rights = 0d;
     private Integer pre_add_rights = 0;
     private Double should_price = 0d;
     private Double high_price = 0d;
     private Double all_price = 0d;
     private Double real_price = 0d;
     private Double pre_price = 0d;
     private Double real_money = 0d;
     private Double total_real_money = 0d;
     private Double allowance = 0d;
     private String rcd_date;


    // Constructors

    /** default constructor */
    public PlatDailyCac() {
    }

	/** minimal constructor */
    public PlatDailyCac(Long plat_daily_cac_id) {
        this.plat_daily_cac_id = plat_daily_cac_id;
    }
   
    // Property accessors
    @Id 
    @TableGenerator(name = "PLATDAILYCAC_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_PLATDAILYCAC", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PLATDAILYCAC_GEN")
    @Column(name="plat_daily_cac_id", unique=true, nullable=false)

    public Long getPlat_daily_cac_id() {
        return this.plat_daily_cac_id;
    }
    
    public void setPlat_daily_cac_id(Long plat_daily_cac_id) {
        this.plat_daily_cac_id = plat_daily_cac_id;
    }
    
    @Column(name="gen_fen")

    public Integer getGen_fen() {
        return this.gen_fen;
    }
    
    public void setGen_fen(Integer gen_fen) {
        this.gen_fen = gen_fen;
    }
    
    @Column(name="assign_fen")

    public Integer getAssign_fen() {
        return this.assign_fen;
    }
    
    public void setAssign_fen(Integer assign_fen) {
        this.assign_fen = assign_fen;
    }
    
    @Column(name="unassign_fen")

    public Integer getUnassign_fen() {
        return this.unassign_fen;
    }
    
    public void setUnassign_fen(Integer unassign_fen) {
        this.unassign_fen = unassign_fen;
    }
    
    @Column(name="add_rights", precision=15, scale=4)

    public Integer getAdd_rights() {
        return this.add_rights;
    }
    
    public void setAdd_rights(Integer add_rights) {
        this.add_rights = add_rights;
    }
    
    @Column(name="sub_rights", precision=15, scale=4)

    public Integer getSub_rights() {
        return this.sub_rights;
    }
    
    public void setSub_rights(Integer sub_rights) {
        this.sub_rights = sub_rights;
    }
    
    @Column(name="give_rights", precision=15, scale=4)

    public Integer getGive_rights() {
        return this.give_rights;
    }
    
    public void setGive_rights(Integer give_rights) {
        this.give_rights = give_rights;
    }
    
    @Column(name="pre_add_rights", precision=15, scale=4)

    public Integer getPre_add_rights() {
        return this.pre_add_rights;
    }
    
    public void setPre_add_rights(Integer pre_add_rights) {
        this.pre_add_rights = pre_add_rights;
    }
    
    @Column(name="should_price", precision=13)

    public Double getShould_price() {
        return this.should_price;
    }
    
    public void setShould_price(Double should_price) {
        this.should_price = should_price;
    }
    
    @Column(name="high_price", precision=13)

    public Double getHigh_price() {
        return this.high_price;
    }
    
    public void setHigh_price(Double high_price) {
        this.high_price = high_price;
    }
    
    @Column(name="all_price", precision=13)

    public Double getAll_price() {
        return this.all_price;
    }
    
    public void setAll_price(Double all_price) {
        this.all_price = all_price;
    }
    
    @Column(name="real_price", precision=13)

    public Double getReal_price() {
        return this.real_price;
    }
    
    public void setReal_price(Double real_price) {
        this.real_price = real_price;
    }
    
    @Column(name="pre_price", precision=13)

    public Double getPre_price() {
        return this.pre_price;
    }
    
    public void setPre_price(Double pre_price) {
        this.pre_price = pre_price;
    }
    
    @Column(name="real_money", precision=13)

    public Double getReal_money() {
        return this.real_money;
    }
    
    public void setReal_money(Double real_money) {
        this.real_money = real_money;
    }
    
    @Column(name="total_real_money", precision=13)

    public Double getTotal_real_money() {
        return this.total_real_money;
    }
    
    public void setTotal_real_money(Double total_real_money) {
        this.total_real_money = total_real_money;
    }
    
    @Column(name="allowance", precision=13)

    public Double getAllowance() {
        return this.allowance;
    }
    
    public void setAllowance(Double allowance) {
        this.allowance = allowance;
    }
    
    @Column(name="rcd_date", length=8)

    public String getRcd_date() {
        return this.rcd_date;
    }
    
    public void setRcd_date(String rcd_date) {
        this.rcd_date = rcd_date;
    }
    
    
    @Column(name="real_rights")
    public Double getReal_rights() {
		return real_rights;
	}

	public void setReal_rights(Double real_rights) {
		this.real_rights = real_rights;
	}

	@Override
	public Object getEntityId() {
		return this.plat_daily_cac_id;
	}
}