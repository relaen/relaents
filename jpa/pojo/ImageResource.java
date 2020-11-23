package dao.pojo;

import javax.persistence.TableGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
// default package
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ImageResource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_image_resource", catalog = "tiandian")
public class ImageResource extends BasePojo implements java.io.Serializable {
	// Fields
	private Long image_resource_id;
	private String url;
	private Integer res_type;
	private Integer enabled;
	private String thump;
	private String remarks;
	private Set<AdvImg> advImgs = new HashSet<AdvImg>(0);
	private Set<ReturnImg> returnImgs = new HashSet<ReturnImg>(0);
	private Set<CommodityCommentRes> commodityCommentReses = new HashSet<CommodityCommentRes>(0);
	private Set<ActivityImg> activityImgs = new HashSet<ActivityImg>(0);
	private Set<SkuRes> skuReses = new HashSet<SkuRes>(0);
	private Set<MsgImg> msgImgs = new HashSet<MsgImg>(0);
	private Set<CommodityRes> commodityReses = new HashSet<CommodityRes>(0);
	private Set<PromotionImg> promotionImgs = new HashSet<PromotionImg>(0);
	private Set<AskResource> askResources = new HashSet<AskResource>(0);
	private Set<FeedbackRes> feedbackReses = new HashSet<FeedbackRes>(0);
	private Set<GivebiRes> givebiReses = new HashSet<GivebiRes>(0);
	private Set<CompanyImg> companyImgs = new HashSet<CompanyImg>(0);
	// Constructors
	/** default constructor */
	public ImageResource() {
	}

	/** minimal constructor */
	public ImageResource(Long image_resource_id) {
		this.image_resource_id = image_resource_id;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "IMAGERESOURCE_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_IMAGERESOURCE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "IMAGERESOURCE_GEN")
	@Column(name = "image_resource_id", unique = true, nullable = false)
	public Long getImage_resource_id() {
		return this.image_resource_id;
	}

	public void setImage_resource_id(Long image_resource_id) {
		this.image_resource_id = image_resource_id;
	}

	@Column(name = "url", length = 500)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "res_type")
	public Integer getRes_type() {
		return this.res_type;
	}

	public void setRes_type(Integer res_type) {
		this.res_type = res_type;
	}

	@Column(name = "enabled")
	public Integer getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	@Column(name = "thump", length = 500)
	public String getThump() {
		return this.thump;
	}

	public void setThump(String thump) {
		this.thump = thump;
	}

	@Column(name = "remarks", length = 500)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "imageResource")
	public Set<AdvImg> getAdvImgs() {
		return this.advImgs;
	}

	public void setAdvImgs(Set<AdvImg> advImgs) {
		this.advImgs = advImgs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "imageResource")
	public Set<ReturnImg> getReturnImgs() {
		return this.returnImgs;
	}

	public void setReturnImgs(Set<ReturnImg> returnImgs) {
		this.returnImgs = returnImgs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "imageResource")
	public Set<CommodityCommentRes> getCommodityCommentReses() {
		return this.commodityCommentReses;
	}

	public void setCommodityCommentReses(Set<CommodityCommentRes> commodityCommentReses) {
		this.commodityCommentReses = commodityCommentReses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "imageResource")
	public Set<ActivityImg> getActivityImgs() {
		return this.activityImgs;
	}

	public void setActivityImgs(Set<ActivityImg> activityImgs) {
		this.activityImgs = activityImgs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "imageResource")
	public Set<SkuRes> getSkuReses() {
		return this.skuReses;
	}

	public void setSkuReses(Set<SkuRes> skuReses) {
		this.skuReses = skuReses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "imageResource")
	public Set<MsgImg> getMsgImgs() {
		return this.msgImgs;
	}

	public void setMsgImgs(Set<MsgImg> msgImgs) {
		this.msgImgs = msgImgs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "imageResource")
	public Set<CommodityRes> getCommodityReses() {
		return this.commodityReses;
	}

	public void setCommodityReses(Set<CommodityRes> commodityReses) {
		this.commodityReses = commodityReses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "imageResource")
	public Set<PromotionImg> getPromotionImgs() {
		return this.promotionImgs;
	}

	public void setPromotionImgs(Set<PromotionImg> promotionImgs) {
		this.promotionImgs = promotionImgs;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "imageResource")
	public Set<AskResource> getAskResources() {
		return askResources;
	}

	public void setAskResources(Set<AskResource> askResources) {
		this.askResources = askResources;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "imageResource")
	public Set<FeedbackRes> getFeedbackReses() {
		return feedbackReses;
	}

	public void setFeedbackReses(Set<FeedbackRes> feedbackReses) {
		this.feedbackReses = feedbackReses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "imageResource")
	public Set<GivebiRes> getGivebiReses() {
		return givebiReses;
	}

	public void setGivebiReses(Set<GivebiRes> givebiReses) {
		this.givebiReses = givebiReses;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "imageResource")
	public Set<CompanyImg> getCompanyImgs() {
		return companyImgs;
	}

	public void setCompanyImgs(Set<CompanyImg> companyImgs) {
		this.companyImgs = companyImgs;
	}

	@Override
	public Object getEntityId() {
		return this.image_resource_id;
	}
}