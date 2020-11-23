package dao.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
// default package
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * Wallet entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_wallet", catalog = "tiandian")
public class Wallet extends BasePojo implements java.io.Serializable {
	// Fields
	private Long wallet_id;
	private Member member;
	private Double dian_yuan=0d;
	private Integer dian_bi=0;
	private Double dian_fen=0d;
	private Integer frozen_dian_fen=0;
	private Double profit=0d;
	private Double profit1 = 0d;	//绩效金额
	private Integer enabled=1;
	private String pay_pwd;
	private Integer saleman_bi=0;
	private Double exchange_last_fen=0d;
	private Integer unexchange_last_fen=0;
	private Integer right_num = 0;
	private Set<WalletFlow> walletFlows = new HashSet<WalletFlow>(0);
	
	// Property accessors
	@Id
	@TableGenerator(name = "WALLET_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_WALLET", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "WALLET_GEN")
	@Column(name = "wallet_id", unique = true, nullable = false)
	public Long getWallet_id() {
		return this.wallet_id;
	}

	public void setWallet_id(Long wallet_id) {
		this.wallet_id = wallet_id;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Column(name = "dian_yuan", precision = 13)
	public Double getDian_yuan() {
		return this.dian_yuan;
	}

	public void setDian_yuan(Double dian_yuan) {
		this.dian_yuan = dian_yuan;
	}

	@Column(name = "dian_bi")
	public Integer getDian_bi() {
		return this.dian_bi;
	}

	public void setDian_bi(Integer dian_bi) {
		this.dian_bi = dian_bi;
	}

	@Column(name = "dian_fen")
	public Double getDian_fen() {
		return dian_fen;
	}
	
	public void setDian_fen(Double dian_fen) {
		this.dian_fen = dian_fen;
	}

	@Column(name = "frozen_dian_fen")
	public Integer getFrozen_dian_fen() {
		return frozen_dian_fen;
	}
	
	public void setFrozen_dian_fen(Integer frozen_dian_fen) {
		this.frozen_dian_fen = frozen_dian_fen;
	}

	@Column(name = "profit", precision = 13)
	public Double getProfit() {
		return this.profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	@Column(name = "enabled")
	public Integer getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	
	@Column(name = "pay_pwd")
	public String getPay_pwd() {
		return pay_pwd;
	}

	public void setPay_pwd(String pay_pwd) {
		this.pay_pwd = pay_pwd;
	}
	
	@Column(name = "saleman_bi")
	public Integer getSaleman_bi() {
		return saleman_bi;
	}

	public void setSaleman_bi(Integer saleman_bi) {
		this.saleman_bi = saleman_bi;
	}
	
	@Column(name = "profit1")
	public Double getProfit1() {
		return profit1;
	}

	public void setProfit1(Double profit1) {
		this.profit1 = profit1;
	}

	@Column(name = "exchange_last_fen")
	public Double getExchange_last_fen() {
		return exchange_last_fen;
	}

	public void setExchange_last_fen(Double exchange_last_fen) {
		this.exchange_last_fen = exchange_last_fen;
	}
	@Column(name = "unexchange_last_fen")
	public Integer getUnexchange_last_fen() {
		return unexchange_last_fen;
	}
	
	public void setUnexchange_last_fen(Integer unexchange_last_fen) {
		this.unexchange_last_fen = unexchange_last_fen;
	}
	
	
	@Column(name = "right_num")
	public Integer getRight_num() {
		return right_num;
	}

	public void setRight_num(Integer right_num) {
		this.right_num = right_num;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "wallet")
	public Set<WalletFlow> getWalletFlows() {
		return walletFlows;
	}

	public void setWalletFlows(Set<WalletFlow> walletFlows) {
		this.walletFlows = walletFlows;
	}
	
	

	@Override
	public Object getEntityId() {
		return this.wallet_id;
	}
}