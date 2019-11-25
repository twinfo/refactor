package chapter01_1.Proto04;

public class ComedyCalculator extends Calculator {
	public int getAmount(Invoices perf) {
		int result = 30000;
		if (perf.getAudience() > 20) {
			result += 10000 + 500 * (perf.getAudience() - 20);
		}
		result += 300 * perf.getAudience();
		return result;
	}
	public int getVolumeCredits(Invoices perf) {
		return (int) (super.getVolumeCredits(perf) + Math.floor(perf.getAudience() / 5));
	}
}
