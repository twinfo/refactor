package chapter01_1.Proto04;

public class TragedyCalculator extends Calculator {
	public int getAmount(Invoices perf) {
		int result =  40000;
		if (perf.getAudience() > 30) {
			result += 1000 * (perf.getAudience() - 30);
		}
		return result;
	}
}
