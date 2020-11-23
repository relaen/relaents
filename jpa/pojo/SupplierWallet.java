package dao.pojo;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


/**
 * SupplierWallet entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_supplier_wallet"
    ,catalog="tiandian"
)

public class SupplierWallet extends BasePojo implements java.io.Serializable {


    // Fields    

     private Long supplier_id;
     private Supplier supplier;
     private Double cash;

    // Constructors

    /** default constructor */
    public SupplierWallet() {
    }

	/** minimal constructor */
    public SupplierWallet(Long supplier_id, Supplier supplier) {
        this.supplier_id = supplier_id;
        this.supplier = supplier;
    }
    
    // Property accessors
    @Id 
    @Column(name="supplier_id", unique=true, nullable=false)
    public Long getSupplier_id() {
        return this.supplier_id;
    }
    
    public void setSupplier_id(Long supplier_id) {
        this.supplier_id = supplier_id;
    }
	@OneToOne(fetch=FetchType.LAZY)@PrimaryKeyJoinColumn

    public Supplier getSupplier() {
        return this.supplier;
    }
    
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
    
    @Column(name="cash", precision=13)

    public Double getCash() {
        return this.cash;
    }
    
    public void setCash(Double cash) {
        this.cash = cash;
    }
    
    @Override
    public Object getEntityId() {
    	return this.supplier_id;
    }
}