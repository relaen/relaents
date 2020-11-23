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
 * companyCollection entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_company_collection", catalog = "tiandian")
public class CompanyCollection extends BasePojo implements java.io.Serializable {
	// Fields
	private Long commodity_collection_id;
	private Company company;
	private Member member;
	private Long collect_time;

	// Constructors
	/** default constructor */
	public CompanyCollection() {
	}

	/** minimal constructor */
	public CompanyCollection(Long commodity_collection_id, Long collect_time) {
		this.commodity_collection_id = commodity_collection_id;
		this.collect_time = collect_time;
	}

	/** full constructor */
	public CompanyCollection(Long commodity_collection_id, Company company, Member member, Long collect_time) {
		this.commodity_collection_id = commodity_collection_id;
		this.company = company;
		this.member = member;
		this.collect_time = collect_time;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "COMPANYCOLLECTION_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COMPANYCOLLECTION", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMPANYCOLLECTION_GEN")
	@Column(name = "commodity_collection_id", unique = true, nullable = false)
	public Long getCommodity_collection_id() {
		return this.commodity_collection_id;
	}

	public void setCommodity_collection_id(Long commodity_collection_id) {
		this.commodity_collection_id = commodity_collection_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Column(name = "collect_time", nullable = false, length = 19)
	public Long getCollect_time() {
		return this.collect_time;
	}

	public void setCollect_time(Long collect_time) {
		this.collect_time = collect_time;
	}

	@Override
	public Object getEntityId() {
		return this.commodity_collection_id;
	}
}