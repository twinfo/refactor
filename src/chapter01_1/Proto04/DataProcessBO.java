package chapter01_1.Proto04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataProcessBO {
	private Map<String, Plays> playsMap = new HashMap<>();
	private Map<String, Object> invoicesMap = new HashMap<>();
	
	//单例模式
	private static DataProcessBO dataBO = new DataProcessBO();
	
	public static DataProcessBO getInstance() {
		return dataBO;
	}
	
	private DataProcessBO() {
			initData();
	}
	
	private void initData() {
		// 构造剧目数据
		playsMap.put("hamlet", new Plays("tragedy", "Hamlet"));
		playsMap.put("as-like", new Plays("comedy", "As You Like It"));
		playsMap.put("othello", new Plays("tragedy", "Othello"));

		// 构造账单数据
		invoicesMap.put("customer", "BigCo");
		List<Invoices> invoicesList = Arrays.asList(new Invoices("hamlet", 55), new Invoices("as-like", 35),
				new Invoices("othello", 40));
		invoicesMap.put("performances", invoicesList);
	}
	
	public ResultData createResultData() {
        ResultData result = new ResultData();
        int totalAmount = 0;
        int volumeCredits = 0;
        result.setCustomer(invoicesMap.get("customer").toString());
        List<Performances> performancesList = createPerformancesData();
        for (Performances performances : performancesList) {
            totalAmount += performances.getAmount();
            volumeCredits += performances.getCredits();
        }
        result.setTotalAmount(totalAmount);
        result.setVolumeCredits(volumeCredits);
        result.setPerformances(performancesList);
        return result;    
    }

	//创建数据结构
	private List<Performances> createPerformancesData() {
		List<Performances> performancesList = new ArrayList<>();
		for (Invoices perf : (List<Invoices>) invoicesMap.get("performances")) {
			Calculator calculator = selectType(playsMap.get(perf.getPlayID()).getType());
			performancesList.add(new Performances(
					playsMap.get(perf.getPlayID()), 
					perf, 
					calculator.getAmount(perf), 
					calculator.getVolumeCredits(perf)));
		}
		return performancesList;
	}
	/*
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
		
	}*/
/*
	private int amountFor(Performances performances) {
		return selectType(performances).getAmount();
	}
	*/
	
	private Calculator selectType(String type) {
        switch (type) {
            case "tragedy":
                return new TragedyCalculator();
            case "comedy":
                return new ComedyCalculator();
            default:
                throw new Error("unknown type");
    }
 }
	
}
