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
 * SupplierManager entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_supplier_manager"
    ,catalog="tiandian"
)

public class SupplierManager extends BasePojo implements java.io.Serializable {


    // Fields    

     private Long supplier_manager_id;
     private Supplier supplier;
     private Member member;


    // Constructors

    /** default constructor */
    public SupplierManager() {
    }

	/** minimal constructor */
    public SupplierManager(Long supplier_manager_id) {
        this.supplier_manager_id = supplier_manager_id;
    }
    
    /** full constructor */
    public SupplierManager(Long supplier_manager_id, Supplier supplier, Member member) {
        this.supplier_manager_id = supplier_manager_id;
        this.supplier = supplier;
        this.member = member;
    }

   
    // Property accessors
    @Id 
    @TableGenerator(name = "SUPPLIERMANAGER_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_SUPPLIERMANAGER", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SUPPLIERMANAGER_GEN")
	@Column(name="supplier_manager_id", unique=true, nullable=false)

    public Long getSupplier_manager_id() {
        return this.supplier_manager_id;
    }
    
    public void setSupplier_manager_id(Long supplier_manager_id) {
        this.supplier_manager_id = supplier_manager_id;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="supplier_id")

    public Supplier getSupplier() {
        return this.supplier;
    }
    
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="member_id")

    public Member getMember() {
        return this.member;
    }
    
    public void setMember(Member member) {
        this.member = member;
    }

    @Override
	public Object getEntityId() {
		return this.supplier_manager_id;
	}

}