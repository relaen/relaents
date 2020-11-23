package dao.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * TMoneyIntro entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_money_intro", catalog = "tiandian")

public class MoneyIntro extends BasePojo implements java.io.Serializable {

	// Fields
	private Long money_intro_id;
	private String title;
	private String content;
	private Short money_type;

	// Constructors

	/** default constructor */
	public MoneyIntro() {
	}

	/** minimal constructor */
	public MoneyIntro(Long moneyIntroId) {
		this.money_intro_id = moneyIntroId;
	}

	/** full constructor */
	public MoneyIntro(Long moneyIntroId, String title, String content, Short moneyType) {
		this.money_intro_id = moneyIntroId;
		this.title = title;
		this.content = content;
		this.money_type = moneyType;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "MONEYINTRO_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_MONEYINTRO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MONEYINTRO_GEN")
	@Column(name = "money_intro_id", unique = true, nullable = false)

	public Long getMoney_intro_id() {
		return this.money_intro_id;
	}

	public void setMoney_intro_id(Long moneyIntroId) {
		this.money_intro_id = moneyIntroId;
	}

	@Column(name = "title", length = 50)

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", length = 65535)

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "money_type")

	public Short getMoney_type() {
		return this.money_type;
	}

	public void setMoney_type(Short moneyType) {
		this.money_type = moneyType;
	}
	@Override
	public Object getEntityId() {
		return this.money_intro_id;
	}
}