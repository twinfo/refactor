package chapter01_1.Proto03;
// �ݳ�������
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
		//�ڼ������ʱ������������Ƿ�ﵽ30
		return Math.max(this.performances.getPerf().getAudience() - 30, 0);
		/*
		int result = 0;
		result += Math.max(this.performances.getPerf().getAudience() - 30, 0);
		//�����������Ƶ�����
		if ("comedy".equals(this.performances.getPlays().getType()))  
				result += Math.floor(this.performances.getPerf().getAudience() / 5);
		return result;
		*/
	}
}
