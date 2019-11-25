package chapter01_1.Proto04;
// ÑÝ³ö¼ÆËãÆ÷
public abstract class Calculator {
	public abstract int getAmount(Invoices perf);
	
	public int getVolumeCredits(Invoices perf) {
		return Math.max(perf.getAudience() - 30, 0);
	}
}
