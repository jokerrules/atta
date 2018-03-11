package org.assassin.jr.attabot.config;

public class CurrencyFragment {
	private CurrencyFragmentType fragmentType;
	private String prefixId;
	private Integer fragmentSize;
	private Double avgPrice;
	private Double[] profits;
	private Double[] bidRates;
	private Double[] bidPrices;
	private Double[] bidRatesCond;
	private Double[] bidPricesCond;
	private Double[] bidPricesCondBelow;
	private Double[] bidRatesCondBelow;
	private Double minProfit;

	@Override
	public CurrencyFragment clone() {
		CurrencyFragment cf = new CurrencyFragment();
		cf.setPrefixId(prefixId);
		cf.setFragmentSize(fragmentSize);
		cf.setMinProfit(minProfit);
		cf.setProfits(profits);
		cf.setFragmentType(fragmentType);
		cf.setAvgPrice(avgPrice);
		cf.setBidRates(bidRates);
		cf.setBidPrices(bidPrices);
		return cf;
	}

	public String getPrefixId() {
		return prefixId;
	}

	public void setPrefixId(String prefixId) {
		this.prefixId = prefixId;
	}

	public Integer getFragmentSize() {
		return fragmentSize;
	}

	public void setFragmentSize(Integer fragmentSize) {
		this.fragmentSize = fragmentSize;
	}

	public Double getMinProfit() {
		return minProfit;
	}

	public void setMinProfit(Double minProfit) {
		this.minProfit = minProfit;
	}

	public Double[] getProfits() {
		return profits;
	}

	public Double getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(Double avgPrice) {
		this.avgPrice = avgPrice;
	}

	public void setProfits(Double[] profits) {
		this.profits = profits;
	}

	public CurrencyFragmentType getFragmentType() {
		return fragmentType;
	}

	public void setFragmentType(CurrencyFragmentType fragmentType) {
		this.fragmentType = fragmentType;
	}

	public Double[] getBidRates() {
		return bidRates;
	}

	public void setBidRates(Double[] bidRates) {
		this.bidRates = bidRates;
	}

	public Double[] getBidPrices() {
		return bidPrices;
	}

	public void setBidPrices(Double[] bidPrices) {
		this.bidPrices = bidPrices;
	}

	public Double[] getBidRatesCond() {
		return bidRatesCond;
	}

	public void setBidRatesCond(Double[] bidRatesCond) {
		this.bidRatesCond = bidRatesCond;
	}

	public Double[] getBidPricesCond() {
		return bidPricesCond;
	}

	public void setBidPricesCond(Double[] bidPricesCond) {
		this.bidPricesCond = bidPricesCond;
	}

	public Double[] getBidPricesCondBelow() {
		return bidPricesCondBelow;
	}

	public void setBidPricesCondBelow(Double[] bidPricesCondBelow) {
		this.bidPricesCondBelow = bidPricesCondBelow;
	}

	public Double[] getBidRatesCondBelow() {
		return bidRatesCondBelow;
	}

	public void setBidRatesCondBelow(Double[] bidRatesCondBelow) {
		this.bidRatesCondBelow = bidRatesCondBelow;
	}
}
