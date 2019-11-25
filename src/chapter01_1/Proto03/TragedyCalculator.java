package chapter01_1.Proto03;

public class TragedyCalculator extends Calculator {
	public TragedyCalculator(Performances performances) {
		super(performances);
	}
	
	public int getAmount() {
		int result =  40000;
		if (this.performances.getPerf().getAudience() > 30) {
			result += 1000 * (performances.getPerf().getAudience() - 30);
		}
		return result;
	}
}
