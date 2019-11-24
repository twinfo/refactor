package chapter01_1.Proto03;

//演出数据实体类
public class Performances {
	private Invoices perf;
	private Plays plays;  
	private Integer amount;
	private Integer credits;

	public Performances(Plays plays, Invoices perf, Integer amount, Integer credits) {
		this.plays = plays;
		this.perf = perf;
		this.amount = amount;
		this.credits = credits;
	}

	public Plays getPlays() {
		return plays;
	}

	public void setPlays(Plays plays) {
		this.plays = plays;
	}

	public Invoices getPerf() {
		return perf;
	}

	public void setPerf(Invoices perf) {
		this.perf = perf;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getCredits() {
		return credits;
	}

	public void setCredits(Integer credits) {
		this.credits = credits;
	}

}
