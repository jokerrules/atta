package org.assassin.jr.attabot.config;

import java.util.Map;

import org.assassin.jr.attabot.utility.AttaConstant;

public class CurrencySetting {
	private String id;
	private boolean isActive;
	private String exchangeMarket;
	private String market;
	private double budget;
	private double debutBid;
	private double profitRate;
	private double reBidRate;
	private Double bidPriceCond;
	private Integer quantityRound;
	private Integer priceRound;
	private Double tradingFee;
	private Long updateTime;
	private Map<String, String> playOptions;
	private CurrencyFragment fragment;

	public CurrencySetting(String id, String market, double budget, double profitRate, double reBidRate, boolean isActive, double debutBid) {
		this.market = market;
		this.budget = budget;
		this.profitRate = profitRate;
		this.reBidRate = reBidRate;
		this.id = id;
		this.isActive = isActive;
		this.debutBid = debutBid;
	}

	public CurrencySetting(String market, double budget, double profitRate, double reBidRate, String id, boolean isActive, double debutBid, Map<String, String> playOptions) {
		super();
		this.market = market;
		this.budget = budget;
		this.profitRate = profitRate;
		this.reBidRate = reBidRate;
		this.id = id;
		this.isActive = isActive;
		this.debutBid = debutBid;
		this.playOptions = playOptions;
	}

	public CurrencySetting(String exchangeMarket, String market, double budget, double profitRate, double reBidRate, String id, boolean isActive, double debutBid, Map<String, String> playOptions) {
		super();
		this.exchangeMarket = exchangeMarket;
		this.market = market;
		this.budget = budget;
		this.profitRate = profitRate;
		this.reBidRate = reBidRate;
		this.id = id;
		this.isActive = isActive;
		this.debutBid = debutBid;
		this.playOptions = playOptions;
	}

	public CurrencySetting(String exchangeMarket, String market, double budget, double profitRate, double reBidRate, String id, boolean isActive, double debutBid, Integer quantityRound, Integer priceRound, Double tradingFee, Map<String, String> playOptions,
			Long updateTime, String prefixId, Integer fragment) {
		super();
		this.exchangeMarket = exchangeMarket;
		this.market = market;
		this.budget = budget;
		this.profitRate = profitRate;
		this.reBidRate = reBidRate;
		this.id = id;
		this.isActive = isActive;
		this.debutBid = debutBid;
		this.quantityRound = quantityRound;
		this.priceRound = priceRound;
		this.tradingFee = tradingFee;
		this.playOptions = playOptions;
		this.updateTime = updateTime;
	}

	public CurrencySetting() {
	}

	@Override
	public CurrencySetting clone() {
		CurrencySetting newSetting = new CurrencySetting();
		newSetting.setExchangeMarket(exchangeMarket);
		newSetting.setMarket(market);
		newSetting.setBudget(budget);
		newSetting.setProfitRate(profitRate);
		newSetting.setReBidRate(reBidRate);
		newSetting.setId(id);
		newSetting.setActive(isActive);
		newSetting.setDebutBid(debutBid);
		newSetting.setQuantityRound(quantityRound);
		newSetting.setPriceRound(priceRound);
		newSetting.setTradingFee(tradingFee);
		newSetting.setPlayOptions(playOptions);
		newSetting.setUpdateTime(updateTime);
		newSetting.setFragment(fragment.clone());
		return newSetting;
	}

	public ExchangeMarket getExchangeMarket() {
		return ExchangeMarket.valueOf(this.exchangeMarket);
	}

	public void setExchangeMarket(String exchangeName) {
		this.exchangeMarket = exchangeName;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public double getProfitRate() {
		return profitRate;
	}

	public void setProfitRate(double profitRate) {
		this.profitRate = profitRate;
	}

	public double getReBidRate() {
		return reBidRate;
	}

	public void setReBidRate(double reBidRate) {
		this.reBidRate = reBidRate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isActive() {
		return isActive;
	}

	public double getDebutBid() {
		return debutBid;
	}

	public void setDebutBid(double debutBid) {
		this.debutBid = debutBid;
	}

	public Integer getQuantityRound() {
		return quantityRound;
	}

	public void setQuantityRound(Integer quantityRound) {
		this.quantityRound = quantityRound;
	}

	public Integer getPriceRound() {
		return priceRound;
	}

	public void setPriceRound(Integer priceRound) {
		this.priceRound = priceRound;
	}

	public Double getTradingFee() {
		return tradingFee;
	}

	public void setTradingFee(Double tradingFee) {
		this.tradingFee = tradingFee;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public void setPlayOptions(Map<String, String> playOptions) {
		this.playOptions = playOptions;
	}

	public Map<String, String> getPlayOptions() {
		return playOptions;
	}

	public String getPlayTypeName() {
		if (playOptions == null || !playOptions.containsKey(AttaConstant.ATTA_CONFIG_FIELD_PLAY_TYPE_NAME)) {
			return AttaConstant.ATTA_CONFIG_FIELD_PLAY_TYPE_OVERLAP;
		}

		return playOptions.get(AttaConstant.ATTA_CONFIG_FIELD_PLAY_TYPE_NAME);
	}

	public PredictType getPredictType() {
		if (playOptions == null || playOptions.size() == 0) {
			return PredictType.OVERLAP;
		}

		return PredictType.valueOf(playOptions.get(AttaConstant.ATTA_CONFIG_FIELD_PLAY_TYPE_NAME));
	}

	public void setPlayType(Map<String, String> playType) {
		this.playOptions = playType;
	}

	public CurrencyFragment getFragment() {
		return fragment;
	}

	public void setFragment(CurrencyFragment fragment) {
		this.fragment = fragment;
	}

	public Double getBidPriceCond() {
		return bidPriceCond;
	}

	public void setBidPriceCond(Double bidPriceCond) {
		this.bidPriceCond = bidPriceCond;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CurrencySetting other = (CurrencySetting) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CurrencySetting [market=" + market + ", input=" + budget + ", profit=" + profitRate + ", reinvested=" + reBidRate + ", id=" + id + ", isActive=" + isActive + ", bidPrice=" + debutBid + "]";
	}
}