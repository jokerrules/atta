package org.assassin.jr.attabot.service.predictor.bollingerband;

public class BollingerBands {
	private double middleBand;
	private double upperBand;
	private double lowerBand;
	private double rangePercent;

	public BollingerBands() {
		rangePercent = 1;
	}

	public BollingerBands(double lowerBand, double middleBand, double upperBand) {
		super();
		this.middleBand = middleBand;
		this.upperBand = upperBand;
		this.lowerBand = lowerBand;
	}

	public double getMiddleBand() {
		return middleBand;
	}

	public void setMiddleBand(double middleBand) {
		this.middleBand = middleBand;
	}

	public double getUpperBand() {
		return upperBand;
	}

	public void setUpperBand(double upperBand) {
		this.upperBand = upperBand;
	}

	public double getLowerBand() {
		return lowerBand;
	}

	public void setLowerBand(double lowerBand) {
		this.lowerBand = lowerBand;
	}

	public double getRangePercent() {
		return rangePercent;
	}

	public void setRangePercent(double rangePercent) {
		this.rangePercent = rangePercent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(lowerBand);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(middleBand);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(upperBand);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		BollingerBands other = (BollingerBands) obj;
		if (Double.doubleToLongBits(lowerBand) != Double.doubleToLongBits(other.lowerBand))
			return false;
		if (Double.doubleToLongBits(middleBand) != Double.doubleToLongBits(other.middleBand))
			return false;
		if (Double.doubleToLongBits(upperBand) != Double.doubleToLongBits(other.upperBand))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BollingerBands [middleBand=" + middleBand + ", upperBand=" + upperBand + ", lowerBand=" + lowerBand + "]";
	}
}