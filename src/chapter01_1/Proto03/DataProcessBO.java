package chapter01_1.Proto03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataProcessBO {
	private Map<String, Plays> playsMap = new HashMap<>();
	private Map<String, Object> invoicesMap = new HashMap<>();
	
	//����ģʽ
	private static DataProcessBO dataBO = new DataProcessBO();
	
	public static DataProcessBO getInstance() {
		return dataBO;
	}
	
	private DataProcessBO() {
			initData();
	}
	
	private void initData() {
		// �����Ŀ����
		playsMap.put("hamlet", new Plays("tragedy", "Hamlet"));
		playsMap.put("as-like", new Plays("comedy", "As You Like It"));
		playsMap.put("othello", new Plays("tragedy", "Othello"));

		// �����˵�����
		invoicesMap.put("customer", "BigCo");
		List<Invoices> invoicesList = Arrays.asList(new Invoices("hamlet", 55), new Invoices("as-like", 35),
				new Invoices("othello", 40));
		invoicesMap.put("performances", invoicesList);
	}
	
	public ResultData createResultData() {
        ResultData result = new ResultData();
        result.setCustomer(invoicesMap.get("customer").toString());
        List<Performances> performancesList = createPerformancesData();
        result.setPerformances(performancesList);
        result.setTotalAmount(totalAmount(result));
        result.setVolumeCredits(totalVolumeCredits(result));
        return result;
    }

	//�������ݽṹ
	private List<Performances> createPerformancesData() {
		List<Performances> performancesList = new ArrayList<>();
		for (Invoices perf : (List<Invoices>) invoicesMap.get("performances")) {
			Performances performances = new Performances(playsMap.get(perf.getPlayID()), perf, 0, 0);
			performances.setAmount(amountFor(performances));
			performancesList.add(performances);
		}
		return performancesList;
	}

	private int totalAmount(ResultData data) {
		int result = 0;
		for (Performances performances : data.getPerformances()) {
			result += amountFor(performances);
		}
		return result;
	}

	private int totalVolumeCredits(ResultData data) {
		int result = 0;
		for (Performances performances : data.getPerformances()) {
			result += volumeCreditsFor(performances);
		}
		return result;
	}

	private int volumeCreditsFor(Performances performances) {
		return selectType(performances).getVolumeCredits();
		/*
		int result = 0;
		result += Math.max(performances.getPerf().getAudience() - 30, 0);
		if ("comedy".equals(performances.getPlays().getType()))
			result += Math.floor(performances.getPerf().getAudience() / 5);
		return result;
		*/
	}

	private int amountFor(Performances performances) {
		return selectType(performances).getAmount();
		/*
		int result = 0;
		switch (performances.getPlays().getType()) {
		case "tragedy":
			result = 40000;
			if (performances.getPerf().getAudience() > 30) {
				result += 1000 * (performances.getPerf().getAudience() - 30);
			}
			break;
		case "comedy":
			result = 30000;
			if (performances.getPerf().getAudience() > 20) {
				result += 10000 + 500 * (performances.getPerf().getAudience() - 20);
			}
			result += 300 * performances.getPerf().getAudience();
			break;
		default:
			throw new Error("unknown type");
		}
		return result;
		*/
	}
	
	public Calculator selectType(Performances performances) {
        switch (performances.getPlays().getType()) {
            case "tragedy":
                return new TragedyCalculator(performances);
            case "comedy":
                return new ComedyCalculator(performances);
            default:
                throw new Error("unknown type");
        }
    }
	
}