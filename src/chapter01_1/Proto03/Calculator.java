package chapter01_1.Proto03;
// 演出计算器
public class Calculator {
	protected Performances performances;
	public Calculator(Performances performances) {
		this.performances = performances;
	}
	
	public int getAmount() {
		return 0;
		//throw new Error('subclass responsibility');
		/*
		int result = 0;
		switch (performances.getPlays().getType()) {
		case "tragedy":
			result = 40000;
			if (this.performances.getPerf().getAudience() > 30) {
				result += 1000 * (performances.getPerf().getAudience() - 30);
			}
			break;
		case "comedy":
			result = 30000;
			if (this.performances.getPerf().getAudience() > 20) {
				result += 10000 + 500 * (this.performances.getPerf().getAudience() - 20);
			}
			result += 300 * this.performances.getPerf().getAudience();
			break;
		default:
			throw new Error("unknown type");
		}
		return result;
		*/
	}
	
	public int getVolumeCredits() {
		//在计算积分时都会检查观众数是否达到30
		return Math.max(this.performances.getPerf().getAudience() - 30, 0);
		/*
		int result = 0;
		result += Math.max(this.performances.getPerf().getAudience() - 30, 0);
		//将此条件下移到子类
		if ("comedy".equals(this.performances.getPlays().getType()))  
				result += Math.floor(this.performances.getPerf().getAudience() / 5);
		return result;
		*/
	}
}
