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
 * supplierRes entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_supplier_res", catalog = "tiandian")
public class SupplierRes extends BasePojo implements java.io.Serializable {
	// Fields
	private Long supplier_res_id;
	private Supplier supplier;
	private ImageResource imageResource;
	private Integer img_res_type;

	// Constructors
	/** default constructor */
	public SupplierRes() {
	}

	/** minimal constructor */
	public SupplierRes(Long supplier_res_id) {
		this.supplier_res_id = supplier_res_id;
	}

	/** full constructor */
	public SupplierRes(Long supplier_res_id, Supplier supplier, ImageResource imageResource, Integer img_res_type) {
		this.supplier_res_id = supplier_res_id;
		this.supplier = supplier;
		this.imageResource = imageResource;
		this.img_res_type = img_res_type;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "SUPPLIERRES_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_SUPPLIERRES", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SUPPLIERRES_GEN")
	@Column(name = "supplier_res_id", unique = true, nullable = false)
	public Long getsupplier_res_id() {
		return this.supplier_res_id;
	}

	public void setsupplier_res_id(Long supplier_res_id) {
		this.supplier_res_id = supplier_res_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplier_id")
	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "image_resource_id")
	public ImageResource getImageResource() {
		return this.imageResource;
	}

	public void setImageResource(ImageResource imageResource) {
		this.imageResource = imageResource;
	}

	@Column(name = "img_res_type")
	public Integer getImg_res_type() {
		return img_res_type;
	}

	public void setImg_res_type(Integer img_res_type) {
		this.img_res_type = img_res_type;
	}

	@Override
	public Object getEntityId() {
		return this.supplier_res_id;
	}

}