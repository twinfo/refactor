package chapter01_1.Proto02;

import java.util.List;

public class ResultData {
	private String customer;
	private Integer totalAmount;
	private Integer volumeCredits;
	private List<Performances> performances;

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getVolumeCredits() {
		return volumeCredits;
	}

	public void setVolumeCredits(Integer volumeCredits) {
		this.volumeCredits = volumeCredits;
	}

	public List<Performances> getPerformances() {
		return performances;
	}

	public void setPerformances(List<Performances> performances) {
		this.performances = performances;
	}

}
