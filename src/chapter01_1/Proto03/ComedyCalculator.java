package chapter01_1.Proto03;

public class ComedyCalculator extends Calculator {
	public ComedyCalculator(Performances performances) {
		super(performances);
	}
	public int getAmount() {
		int result = 30000;
		if (this.performances.getPerf().getAudience() > 20) {
			result += 10000 + 500 * (this.performances.getPerf().getAudience() - 20);
		}
		result += 300 * this.performances.getPerf().getAudience();
		return result;
	}
	public int getVolumeCredits() {
		return (int) (super.getVolumeCredits() + Math.floor(this.performances.getPerf().getAudience() / 5));
	}
}
