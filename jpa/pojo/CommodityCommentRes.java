package dao.pojo;

import javax.persistence.TableGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
// default package
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CommodityCommentRes entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_commodity_comment_res", catalog = "tiandian")
public class CommodityCommentRes extends BasePojo implements java.io.Serializable {
	// Fields
	private Long commd_cmt_rescommd_cmt_res_id;
	private CommodityComment commodityComment;
	private ImageResource imageResource;

	// Constructors
	/** default constructor */
	public CommodityCommentRes() {
	}

	/** minimal constructor */
	public CommodityCommentRes(Long commd_cmt_rescommd_cmt_res_id) {
		this.commd_cmt_rescommd_cmt_res_id = commd_cmt_rescommd_cmt_res_id;
	}

	/** full constructor */
	public CommodityCommentRes(Long commd_cmt_rescommd_cmt_res_id, CommodityComment commodityComment,
			ImageResource imageResource) {
		this.commd_cmt_rescommd_cmt_res_id = commd_cmt_rescommd_cmt_res_id;
		this.commodityComment = commodityComment;
		this.imageResource = imageResource;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "COMMODITYCOMMENTRES_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COMMODITYCOMMENTRES", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMMODITYCOMMENTRES_GEN")
	@Column(name = "commd_cmt_rescommd_cmt_res_id", unique = true, nullable = false)
	public Long getCommd_cmt_rescommd_cmt_res_id() {
		return this.commd_cmt_rescommd_cmt_res_id;
	}

	public void setCommd_cmt_rescommd_cmt_res_id(Long commd_cmt_rescommd_cmt_res_id) {
		this.commd_cmt_rescommd_cmt_res_id = commd_cmt_rescommd_cmt_res_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commodity_comment_id")
	public CommodityComment getCommodityComment() {
		return this.commodityComment;
	}

	public void setCommodityComment(CommodityComment commodityComment) {
		this.commodityComment = commodityComment;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "image_resource_id")
	public ImageResource getImageResource() {
		return this.imageResource;
	}

	public void setImageResource(ImageResource imageResource) {
		this.imageResource = imageResource;
	}

	@Override
	public Object getEntityId() {
		return this.commd_cmt_rescommd_cmt_res_id;
	}
}